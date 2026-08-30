import subprocess

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

def get_episodes_with_capa():
    sql = """SELECT e.id, e.capa_url, a.caminho 
             FROM episodio e 
             JOIN arquivo a ON CAST(e.capa_url AS UNSIGNED) = a.id;"""
    output = run_sql(sql)
    episodes = []
    for line in output.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('id') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) == 3:
            episodes.append({'id': int(parts[0]), 'caminho': parts[2]})
    return episodes

def batch_update(updates):
    if not updates:
        return
    cases = []
    for eid, caminho in updates:
        caminho_escaped = caminho.replace("'", "\\'")
        cases.append(f"WHEN id = {eid} THEN '{caminho_escaped}'")
    ids = ','.join(str(eid) for eid, _ in updates)
    sql = f"UPDATE episodio SET capa_url = CASE {' '.join(cases)} ELSE capa_url END WHERE id IN ({ids});"
    run_sql(sql)

if __name__ == "__main__":
    print("Buscando episódios com capa...")
    episodes = get_episodes_with_capa()
    print(f"Total: {len(episodes)}")
    
    updates = []
    for ep in episodes:
        updates.append((ep['id'], ep['caminho']))
        if len(updates) >= 500:
            batch_update(updates)
            updates = []
    
    if updates:
        batch_update(updates)
    
    print("Corrigido!")
    
    # Verify
    result = run_sql("SELECT id, capa_url FROM episodio WHERE capa_url IS NOT NULL LIMIT 5;")
    print(f"\nExemplos:\n{result}")
