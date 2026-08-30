import subprocess
import re

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

def get_episodes():
    sql = """SELECT e.id, a.caminho 
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
        if len(parts) == 2:
            episodes.append({'id': int(parts[0]), 'caminho': parts[1]})
    return episodes

def extract_temporada(caminho):
    match = re.search(r'(\d+)\s*[ªº]\s*Temporada', caminho, re.IGNORECASE)
    if match:
        return int(match.group(1))
    return 0

def extract_parte(caminho):
    match = re.search(r'Parte\s+(\d+)', caminho, re.IGNORECASE)
    if match:
        return int(match.group(1))
    return 0

def batch_update_temporada(updates):
    if not updates:
        return
    cases = []
    for eid, val in updates:
        cases.append(f"WHEN id = {eid} THEN {val}")
    ids = ','.join(str(eid) for eid, _ in updates)
    sql = f"UPDATE episodio SET temporada = CASE {' '.join(cases)} ELSE temporada END WHERE id IN ({ids});"
    run_sql(sql)

def batch_update_parte(updates):
    if not updates:
        return
    cases = []
    for eid, val in updates:
        cases.append(f"WHEN id = {eid} THEN {val}")
    ids = ','.join(str(eid) for eid, _ in updates)
    sql = f"UPDATE episodio SET parte = CASE {' '.join(cases)} ELSE parte END WHERE id IN ({ids});"
    run_sql(sql)

if __name__ == "__main__":
    print("Buscando episódios...")
    episodes = get_episodes()
    print(f"Total: {len(episodes)}")
    
    updates_temp = []
    updates_parte = []
    
    for ep in episodes:
        temporada = extract_temporada(ep['caminho'])
        parte = extract_parte(ep['caminho'])
        
        updates_temp.append((ep['id'], temporada))
        updates_parte.append((ep['id'], parte))
        
        if len(updates_temp) >= 500:
            batch_update_temporada(updates_temp)
            batch_update_parte(updates_parte)
            updates_temp = []
            updates_parte = []
    
    if updates_temp:
        batch_update_temporada(updates_temp)
        batch_update_parte(updates_parte)
    
    print("Concluído!")
    
    # Verify
    result = run_sql("SELECT DISTINCT temporada FROM episodio ORDER BY temporada;")
    print(f"\nValores de temporada: {result}")
    result = run_sql("SELECT DISTINCT parte FROM episodio ORDER BY parte;")
    print(f"Valores de parte: {result}")
