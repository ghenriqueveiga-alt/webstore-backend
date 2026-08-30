import subprocess
import re

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

def get_episodes():
    sql = """SELECT e.id, e.numero, e.temporada, e.programa_id, a.caminho 
             FROM episodio e 
             JOIN arquivo a ON e.arquivo_id = a.id 
             ORDER BY e.id;"""
    output = run_sql(sql)
    episodes = []
    for line in output.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('id') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) == 5:
            episodes.append({
                'id': int(parts[0]),
                'numero': int(parts[1]),
                'temporada': int(parts[2]),
                'programa_id': int(parts[3]),
                'caminho': parts[4]
            })
    return episodes

def extract_parte(caminho):
    match = re.search(r'Parte\s+(\d+)', caminho, re.IGNORECASE)
    if match:
        return int(match.group(1))
    return 1

def batch_update(updates):
    if not updates:
        return
    cases = []
    for eid, parte in updates:
        cases.append(f"WHEN id = {eid} THEN {parte}")
    ids = ','.join(str(eid) for eid, _ in updates)
    sql = f"UPDATE episodio SET parte = CASE {' '.join(cases)} ELSE parte END WHERE id IN ({ids});"
    run_sql(sql)

if __name__ == "__main__":
    print("Buscando episódios...")
    episodes = get_episodes()
    print(f"Total: {len(episodes)}")
    
    updates = []
    for ep in episodes:
        parte = extract_parte(ep['caminho'])
        updates.append((ep['id'], parte))
        
        if len(updates) >= 500:
            batch_update(updates)
            updates = []
    
    if updates:
        batch_update(updates)
    
    print("Concluído!")
    
    # Verify
    result = run_sql("SELECT DISTINCT parte FROM episodio ORDER BY parte;")
    print(f"\nValores de parte: {result}")
