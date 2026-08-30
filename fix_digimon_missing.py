import subprocess
import re

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

def get_programa_id(nome):
    output = run_sql(f"SELECT id FROM programa WHERE nome = '{nome}';")
    for line in output.strip().split('\n'):
        line = line.strip()
        if line and line.isdigit():
            return int(line)
    return None

def get_existing_episodio_ids(programa_id):
    output = run_sql(f"SELECT arquivo_id FROM episodio WHERE programa_id = {programa_id};")
    ids = set()
    for line in output.strip().split('\n'):
        line = line.strip()
        if line and line.isdigit():
            ids.add(int(line))
    return ids

def get_missing_arquivos(programa_id):
    output = run_sql(f"""SELECT a.id, a.caminho FROM arquivo a
        WHERE a.caminho LIKE '%Digimon - Adventure%1ª Temporada%'
        AND a.caminho LIKE '%.rmvb'
        ORDER BY a.caminho;""")
    arquivos = []
    for line in output.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('id') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) == 2:
            arquivos.append({'id': int(parts[0]), 'caminho': parts[1]})
    return arquivos

def extract_numero(caminho):
    filename = caminho.split('\\\\')[-1]
    match = re.match(r'^(\d+)_', filename)
    if match:
        return int(match.group(1))
    return None

def extract_titulo(caminho):
    filename = caminho.split('\\\\')[-1]
    match = re.match(r'^\d+_(.+)\.\w+$', filename)
    if match:
        return match.group(1).replace('_', ' ')
    return None

if __name__ == "__main__":
    programa_id = get_programa_id("Digimon - Adventure")
    print(f"Programa ID: {programa_id}")

    existing = get_existing_episodio_ids(programa_id)
    print(f"Existing arquivo_ids in episodio: {len(existing)}")

    arquivos = get_missing_arquivos(programa_id)
    print(f"Missing .rmvb arquivos: {len(arquivos)}")

    uuid_sql_prefix = "SELECT UUID();"
    
    missing = [a for a in arquivos if a['id'] not in existing]
    print(f"Need to insert: {len(missing)}")
    
    for a in missing:
        numero = extract_numero(a['caminho'])
        titulo = extract_titulo(a['caminho'])
        if numero is None or titulo is None:
            print(f"  SKIP: id={a['id']} numero={numero} titulo={titulo}")
            continue
        
        uuid_out = run_sql(uuid_sql_prefix).strip()
        ep_uuid = uuid_out.split('\n')[0].strip() if uuid_out else ''
        
        sql = f"""INSERT INTO episodio (uuid, status_desc, arquivo_id, titulo, numero, temporada, parte, programa_id, processado)
            VALUES ('{ep_uuid}', 'Active', {a['id']}, '{titulo.replace(chr(39), chr(39)+chr(39))}', {numero}, 1, 0, {programa_id}, 0);"""
        result = run_sql(sql)
        if 'Error' in result or 'error' in result.lower():
            print(f"  ERROR: ep {numero} - {result[:100]}")
        else:
            print(f"  OK: ep {numero} - {titulo}")
    
    print("Done!")
