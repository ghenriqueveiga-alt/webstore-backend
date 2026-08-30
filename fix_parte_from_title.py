import subprocess
import re

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

def get_episodes_with_parte_in_name():
    sql = """SELECT e.id, a.caminho FROM episodio e
             JOIN arquivo a ON e.arquivo_id = a.id
             WHERE e.parte = 0
             AND (a.caminho LIKE '%Parte 1%' OR a.caminho LIKE '%Parte 2%' OR a.caminho LIKE '%Parte 3%'
                  OR a.caminho LIKE '%Parte 4%' OR a.caminho LIKE '%Parte 5%'
                  OR a.caminho LIKE '%parte 1%' OR a.caminho LIKE '%parte 2%' OR a.caminho LIKE '%parte 3%'
                  OR a.caminho LIKE '%1ª parte%' OR a.caminho LIKE '%2ª parte%'
                  OR a.caminho LIKE '%3ª parte%' OR a.caminho LIKE '%4ª parte%'
                  OR a.caminho LIKE '%Primeira Parte%' OR a.caminho LIKE '%Segunda Parte%'
                  OR a.caminho LIKE '%Terceira Parte%' OR a.caminho LIKE '%Quarta Parte%')
             AND a.caminho LIKE '%.mp4'
             ORDER BY e.id;"""
    output = run_sql(sql)
    episodes = []
    for line in output.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('id') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) == 2:
            episodes.append({'id': int(parts[0]), 'caminho': parts[1]})
    return episodes

def extract_parte_from_name(caminho):
    filename = caminho.split('\\\\')[-1]
    
    match = re.search(r'(\d+)[ªº]\s*parte', filename, re.IGNORECASE)
    if match:
        return int(match.group(1))
    
    match = re.search(r'Parte\s+(\d+)', filename, re.IGNORECASE)
    if match:
        return int(match.group(1))
    
    if re.search(r'Primeira\s+Parte', filename, re.IGNORECASE):
        return 1
    if re.search(r'Segunda\s+Parte', filename, re.IGNORECASE):
        return 2
    if re.search(r'Terceira\s+Parte', filename, re.IGNORECASE):
        return 3
    if re.search(r'Quarta\s+Parte', filename, re.IGNORECASE):
        return 4
    
    return 0

if __name__ == "__main__":
    print("Buscando episodios com 'parte' no nome...")
    episodes = get_episodes_with_parte_in_name()
    print(f"Total: {len(episodes)}")
    
    updates = []
    for ep in episodes:
        parte = extract_parte_from_name(ep['caminho'])
        if parte > 0:
            updates.append((ep['id'], parte))
            print(f"  ID={ep['id']} -> parte={parte} | {ep['caminho'].split(chr(92))[-1]}")
    
    print(f"\nAtualizando {len(updates)} episodios...")
    for ep_id, parte in updates:
        sql = f"UPDATE episodio SET parte = {parte} WHERE id = {ep_id};"
        run_sql(sql)
    
    print("Concluido!")
