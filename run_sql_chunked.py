import subprocess
import sys

filename = sys.argv[1] if len(sys.argv) > 1 else 'restore_programa_id.sql'
chunk_size = 500

with open(filename, 'r', encoding='utf-8') as f:
    lines = [l for l in f.readlines() if l.strip()]

total = len(lines)
print(f"Total statements: {total}")

for i in range(0, total, chunk_size):
    chunk = lines[i:i+chunk_size]
    sql = '\n'.join(chunk)
    result = subprocess.run(
        ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db',
         '--default-character-set=utf8mb4'],
        input=sql.encode('utf-8'),
        capture_output=True, timeout=120
    )
    if result.returncode != 0:
        print(f"Error at chunk {i}: {result.stderr.decode(errors='replace')[-200:]}")
        sys.exit(1)
    print(f"Applied {min(i+chunk_size, total)}/{total}")

print("Done")
