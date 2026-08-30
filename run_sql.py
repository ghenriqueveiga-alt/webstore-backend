import subprocess
import sys

filename = sys.argv[1] if len(sys.argv) > 1 else 'populate_bloco.sql'

with open(filename, 'r', encoding='utf-8') as f:
    sql = f.read()

result = subprocess.run(
    ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4'],
    input=sql.encode('utf-8'),
    capture_output=True, timeout=300
)
print("Return code:", result.returncode)
if result.stdout:
    print("STDOUT:", result.stdout.decode(errors='replace')[-500:])
if result.stderr:
    print("STDERR:", result.stderr.decode(errors='replace')[-500:])
