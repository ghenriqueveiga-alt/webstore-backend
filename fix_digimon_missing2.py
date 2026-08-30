import subprocess
import re

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    stdout = result.stdout.decode(errors='replace')
    stderr = result.stderr.decode(errors='replace')
    return stdout, stderr

def get_programa_id(nome):
    out, err = run_sql(f"SELECT id FROM programa WHERE nome = '{nome}';")
    for line in out.strip().split('\n'):
        line = line.strip()
        if line and line.isdigit():
            return int(line)
    return None

def get_existing_arquivo_ids(programa_id):
    out, err = run_sql(f"SELECT arquivo_id FROM episodio WHERE programa_id = {programa_id};")
    ids = set()
    for line in out.strip().split('\n'):
        line = line.strip()
        if line and line.isdigit():
            ids.add(int(line))
    return ids

def get_missing_arquivos():
    out, err = run_sql("""SELECT a.id, a.caminho FROM arquivo a
        WHERE a.caminho LIKE '%Digimon - Adventure%1ª Temporada%'
        AND a.caminho LIKE '%.rmvb'
        ORDER BY a.caminho;""")
    arquivos = []
    for line in out.strip().split('\n'):
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

    existing = get_existing_arquivo_ids(programa_id)
    print(f"Existing arquivo_ids: {len(existing)}")

    arquivos = get_missing_arquivos()
    missing = [a for a in arquivos if a['id'] not in existing]
    print(f"Missing: {len(missing)}")

    for a in missing:
        numero = extract_numero(a['caminho'])
        titulo = extract_titulo(a['caminho'])
        if numero is None or titulo is None:
            print(f"  SKIP: id={a['id']}")
            continue

        titulo_safe = titulo.replace("'", "''")
        sql = f"""INSERT INTO episodio (uuid, status_desc, arquivo_id, titulo, numero, temporada, parte, programa_id, processado, ordem)
            VALUES (UUID(), 'Active', {a['id']}, '{titulo_safe}', {numero}, 1, 0, {programa_id}, 0, 0);"""
        out, err = run_sql(sql)
        if 'Error' in err or 'Duplicate' in err:
            print(f"  ERROR ep {numero}: {err[:100]}")
        else:
            print(f"  OK ep {numero} - {titulo}")

    print("Done!")
