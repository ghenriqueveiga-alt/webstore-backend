import subprocess
import re

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

def get_all_episodes():
    sql = """SELECT e.id, a.caminho FROM episodio e
             JOIN arquivo a ON e.arquivo_id = a.id
             WHERE a.caminho LIKE '%.mp4'
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

def extract_parte_from_folder(caminho):
    match = re.search(r'\\Parte\s+(\d+)\\', caminho)
    if match:
        return int(match.group(1))
    return 0

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
    print("Buscando episodios...")
    episodes = get_all_episodes()
    print(f"Total: {len(episodes)}")
    
    updates = []
    for ep in episodes:
        parte = extract_parte_from_folder(ep['caminho'])
        updates.append((ep['id'], parte))
        
        if len(updates) >= 500:
            batch_update(updates)
            updates = []
    
    if updates:
        batch_update(updates)
    
    print("Concluido!")
    
    result = run_sql("SELECT DISTINCT parte FROM episodio ORDER BY parte;")
    print(f"Valores de parte: {result}")
