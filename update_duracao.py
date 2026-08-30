import subprocess
import re

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

def get_all_files():
    sql = "SELECT id, caminho FROM arquivo WHERE caminho LIKE '%.mp4' OR caminho LIKE '%.mkv' OR caminho LIKE '%.avi' OR caminho LIKE '%.mov' OR caminho LIKE '%.wmv' ORDER BY id;"
    output = run_sql(sql)
    files = []
    for line in output.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('id') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) == 2:
            files.append({'id': parts[0], 'caminho': parts[1]})
    return files

def get_duration(filepath):
    try:
        cmd = ['ffprobe', '-v', 'quiet', '-print_format', 'json', '-show_format', filepath]
        result = subprocess.run(cmd, capture_output=True, timeout=30)
        if result.returncode == 0:
            output = result.stdout.decode()
            match = re.search(r'"duration"\s*:\s*"(\d+\.?\d*)"', output)
            if match:
                seconds = float(match.group(1))
                hours = int(seconds // 3600)
                minutes = int((seconds % 3600) // 60)
                secs = int(seconds % 60)
                return f"{hours:02d}:{minutes:02d}:{secs:02d}"
    except Exception:
        pass
    return None

def batch_update(updates):
    if not updates:
        return
    cases = []
    for uid, duration in updates:
        cases.append(f"WHEN id = {uid} THEN '{duration}'")
    ids = ','.join(str(uid) for uid, _ in updates)
    sql = f"UPDATE arquivo SET duracao = CASE {' '.join(cases)} ELSE duracao END WHERE id IN ({ids});"
    run_sql(sql)

if __name__ == "__main__":
    print("Buscando arquivos de vídeo do banco...")
    files = get_all_files()
    print(f"Total de arquivos de vídeo: {len(files)}")
    
    if files:
        print(f"Exemplo de caminho: {files[0]['caminho']}")
        print(f"Testando ffprobe no primeiro arquivo...")
        test = get_duration(files[0]['caminho'])
        print(f"Resultado: {test}")
    
    updated = 0
    errors = 0
    batch = []
    
    for i, f in enumerate(files):
        duration = get_duration(f['caminho'])
        if duration:
            batch.append((f['id'], duration))
            updated += 1
        else:
            errors += 1
        
        if len(batch) >= 100:
            batch_update(batch)
            batch = []
        
        if (i + 1) % 100 == 0:
            print(f"Progresso: {i + 1}/{len(files)} | Atualizados: {updated} | Erros: {errors}")
    
    if batch:
        batch_update(batch)
    
    print(f"\nConcluído! Atualizados: {updated} | Erros: {errors}")
