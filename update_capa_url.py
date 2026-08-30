import subprocess
import os

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

def get_episodes_with_arquivo():
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

def get_image_files():
    sql = """SELECT id, caminho FROM arquivo 
             WHERE (caminho LIKE '%_imagem.jpg' OR caminho LIKE '%_imagem.png' OR caminho LIKE '%_imagem.jpeg')
             ORDER BY caminho;"""
    output = run_sql(sql)
    images = {}
    for line in output.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('id') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) == 2:
            # Remove "_imagem.ext" and get base name for matching
            path = parts[1]
            # Remove extension
            base = os.path.splitext(path)[0]
            # Remove "_imagem" suffix
            base = base.replace('_imagem', '')
            images[base] = int(parts[0])
    return images

def batch_update(updates):
    if not updates:
        return
    cases = []
    for eid, img_id in updates:
        cases.append(f"WHEN id = {eid} THEN {img_id}")
    ids = ','.join(str(eid) for eid, _ in updates)
    sql = f"UPDATE episodio SET capa_url = CAST({{img_id}} AS CHAR) WHERE id IN ({ids});"
    # Use individual approach for safety
    for eid, img_id in updates:
        sql = f"UPDATE episodio SET capa_url = '{img_id}' WHERE id = {eid};"
        run_sql(sql)

def batch_update_v2(updates):
    if not updates:
        return
    cases = []
    for eid, img_id in updates:
        cases.append(f"WHEN id = {eid} THEN '{img_id}'")
    ids = ','.join(str(eid) for eid, _ in updates)
    sql = f"UPDATE episodio SET capa_url = CASE {' '.join(cases)} ELSE capa_url END WHERE id IN ({ids});"
    run_sql(sql)

if __name__ == "__main__":
    print("Buscando episódios...")
    episodes = get_episodes_with_arquivo()
    print(f"Total de episódios: {len(episodes)}")
    
    print("Buscando arquivos de imagem...")
    images = get_image_files()
    print(f"Total de imagens: {len(images)}")
    
    # Debug
    print("\nDebug: Comparando caminhos...")
    matched = 0
    updates = []
    
    for ep in episodes:
        # Get base path (without extension)
        base = os.path.splitext(ep['caminho'])[0]
        if base in images:
            matched += 1
            updates.append((ep['id'], images[base]))
        
        if len(updates) >= 500:
            batch_update_v2(updates)
            updates = []
    
    if updates:
        batch_update_v2(updates)
    
    print(f"Episódios com capa encontrados: {matched}")
    print(f"Imagens mapeadas: {len(images)}")
    
    # Verify
    total = run_sql("SELECT COUNT(*) FROM episodio WHERE capa_url IS NOT NULL AND capa_url != '';")
    print(f"Episódios com capa_url preenchida: {total.strip().split(chr(10))[-1].strip()}")
