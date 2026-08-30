import subprocess
import json

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

# Get all unique program names
sql = "SELECT DISTINCT SUBSTRING_INDEX(caminho, '\\\\', 1) as nome FROM arquivo WHERE caminho LIKE '%.mp4' OR caminho LIKE '%.mkv' OR caminho LIKE '%.avi' OR caminho LIKE '%.mov' OR caminho LIKE '%.wmv' ORDER BY nome;"
output = run_sql(sql)

programs = []
for line in output.strip().split('\n'):
    line = line.strip()
    if not line or line.startswith('nome') or line.startswith('---'):
        continue
    programs.append(line)

print(f"Total de programas encontrados: {len(programs)}")
for p in programs:
    print(f"  - {p}")
