#!/usr/bin/env python3

import subprocess
import uuid

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

def main():
    # First, let's find all episodes longer than 30 minutes
    sql = """SELECT e.numero, e.ordem, p.nome as programa_nome, 
                  a.duracao, COUNT(*) as count 
           FROM episodio e 
           JOIN programa p ON p.id = e.programa_id 
           JOIN arquivo a ON a.id = e.arquivo_id 
           WHERE e.ordem IS NOT NULL 
           AND a.duracao IS NOT NULL 
           AND CAST(a.duracao AS UNSIGNED) > 1800
           GROUP BY e.numero, e.ordem, p.nome, a.duracao 
           ORDER BY p.nome, e.ordem;"""
    
    out = run_sql(sql)
    long_episodes = []
    
    for line in out.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('numero') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) >= 4:
            numero = int(parts[0])
            ordem = int(parts[1]) if parts[1] else 0
            programa_nome = parts[2]
            duracao = parts[3]
            
            # Parse duration (format: HH:MM:SS)
            h, m, s = map(int, duracao.split(':'))
            total_seconds = h * 3600 + m * 60 + s
            
            long_episodes.append({
                'numero': numero,
                'ordem': ordem,
                'programa_nome': programa_nome,
                'duracao': duracao,
                'total_seconds': total_seconds
            })
    
    print(f"Found {len(long_episodes)} episodes longer than 30 minutes")
    for ep in long_episodes:
        print(f"  {ep['programa_nome']} Ep {ep['numero']}:{ep['ordem']} - {ep['duracao']}")
    
    # Get all episodes for reference
    sql = """SELECT e.numero, e.ordem, p.nome as programa_nome, a.duracao 
             FROM episodio e 
             JOIN programa p ON p.id = e.programa_id 
             JOIN arquivo a ON a.id = e.arquivo_id 
             WHERE e.ordem IS NOT NULL 
             AND a.duracao IS NOT NULL 
             ORDER BY p.nome, e.ordem;"""
    
    out = run_sql(sql)
    all_episodes = []
    
    for line in out.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('numero') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) >= 4:
            numero = int(parts[0])
            ordem = int(parts[1]) if parts[1] else 0
            programa_nome = parts[2]
            duracao = parts[3]
            
            h, m, s = map(int, duracao.split(':'))
            total_seconds = h * 3600 + m * 60 + s
            
            all_episodes.append({
                'numero': numero,
                'ordem': ordem,
                'programa_nome': programa_nome,
                'duracao': duracao,
                'total_seconds': total_seconds
            })
    
    # Group by programa_nome and ordem to find the current episode structure
    ep_structure = {}
    for ep in all_episodes:
        key = f"{ep['programa_nome']}_{ep['ordem']}"
        ep_structure[key] = ep
    
    print(f"\nTotal episodes: {len(all_episodes)}")
    
    # Get existing blocos for the same grade
    sql = "SELECT b.id, b.programa_id, e.numero, e.ordem, p.nome as programa_nome, a.duracao, b.horario 
            FROM bloco b 
            JOIN episodio e ON b.programa_id = e.programa_id 
            JOIN programa p ON b.programa_id = p.id 
            JOIN arquivo a ON e.arquivo_id = a.id 
            WHERE e.ordem IS NOT NULL 
            AND a.duracao IS NOT NULL 
            AND b.grade_id = 1 
            ORDER BY p.nome, e.ordem;"
    
    out = run_sql(sql)
    existing_blocos = []
    
    for line in out.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('id') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) >= 7:
            bloco_id = parts[0]
            programa_id = parts[1]
            numero = int(parts[2])
            ordem = int(parts[3]) if parts[3] else 0
            programa_nome = parts[4]
            duracao = parts[5]
            horario = parts[6]
            
            h, m, s = map(int, duracao.split(':'))
            total_seconds = h * 3600 + m * 60 + s
            
            existing_blocos.append({
                'id': bloco_id,
                'programa_id': programa_id,
                'numero': numero,
                'ordem': ordem,
                'programa_nome': programa_nome,
                'duracao': duracao,
                'total_seconds': total_seconds,
                'horario': horario
            })
    
    print(f"\nExisting blocos for grade 1: {len(existing_blocos)}")
    
    # Create new bloco assignments based on the requirement
    # For each episode longer than 30 minutes, push to the right
    # Ep 1->Ep 1, Ep 2->Ep 1, Ep 3->Ep 2, etc.
    # Add 1 slot above and 1 slot below
    
    new_blocos = []
    
    # Sort existing blocos to process them in order
    existing_blocos.sort(key=lambda x: (x['programa_nome'], x['ordem']))
    
    # We'll create a new sequence where long episodes are replaced
    # with the pattern: [space] + [adjusted episodes] + [space]
    
    processed_episodios = set()
    
    for bloco in existing_blocos:
        ep_key = f"{bloco['programa_nome']}_{bloco['ordem']}"
        
        if bloco['total_seconds'] > 1800:  # Long episode
            # This episode needs to be replaced
            # Find the next non-long episode in sequence to replace it with
            current_ordem = bloco['ordem']
            next_ep_key = f"{bloco['programa_nome']}_{current_ordem + 1}"
            
            # Look for the next episode in the same program that is not long
            replacement_ep = None
            search_ordem = current_ordem + 1
            
            while not replacement_ep and search_ordem <= 20:  # Limit search
                test_key = f"{bloco['programa_nome']}_{search_ordem}"
                if test_key in ep_structure:
                    test_ep = ep_structure[test_key]
                    if test_ep['total_seconds'] <= 1800:  # Not long
                        replacement_ep = test_ep
                        break
                search_ordem += 1
            
            if replacement_ep:
                # Replace this bloco with the non-long episode
                new_blocos.append({
                    'id': bloco['id'],
                    'programa_id': bloco['programa_id'],  # Keep original for now
                    'numero': replacement_ep['numero'],
                    'ordem': replacement_ep['ordem'],
                    'programa_nome': replacement_ep['programa_nome'],
                    'duracao': replacement_ep['duracao'],
                    'total_seconds': replacement_ep['total_seconds'],
                    'horario': bloco['horario']  # Keep same horario for now
                })
                processed_episodios.add(ep_key)
                
                # Insert the long episode with adjusted spacing
                # We need to insert 1 slot above first
                # For now, let's just replace the bloco with the non-long episode
                # and handle the long episode separately
        else:
            # Regular episode, keep as is
            new_blocos.append({
                'id': bloco['id'],
                'programa_id': bloco['programa_id'],
                'numero': bloco['numero'],
                'ordem': bloco['ordem'],
                'programa_nome': bloco['programa_nome'],
                'duracao': bloco['duracao'],
                'total_seconds': bloco['total_seconds'],
                'horario': bloco['horario']
            })
            processed_episodios.add(ep_key)
    
    print(f"\nAfter replacement: {len(new_blocos)} blocos")
    
    # Calculate the schedule adjustments
    print("\nSchedule adjustments:")
    for ep in long_episodes:
        current_ep_key = f"{ep['programa_nome']}_{ep['ordem']}"
        if current_ep_key in processed_episodios:
            print(f"  {ep['programa_nome']} Ep {ep['numero']}:{ep['ordem']} -> Replaced")
        else:
            print(f"  {ep['programa_nome']} Ep {ep['numero']}:{ep['ordem']} -> Kept (no replacement found)")
    
    print(f"\nTotal processed episodes: {len(processed_episodios)}")
    print("Done!")

if __name__ == "__main__":
    main()