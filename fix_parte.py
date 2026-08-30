import subprocess
import re

# Get all episodes with paths
result = subprocess.run(
    ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db',
     '--default-character-set=utf8mb4', '-e',
     "SELECT e.id, a.caminho FROM episodio e JOIN arquivo a ON e.arquivo_id = a.id "
     "WHERE e.status_desc = 'Active' AND e.numero IS NOT NULL AND e.temporada = 1;"],
    capture_output=True, timeout=60
)
lines = result.stdout.decode(errors='replace').strip().split('\n')

updates = []
for line in lines[1:]:
    parts = line.split('\t', 1)
    if len(parts) < 2:
        continue
    eid = int(parts[0])
    caminho = parts[1].strip()

    segments = caminho.split('\\\\')
    parte = 0

    # Look for "Parte N" in any segment
    for seg in segments:
        m = re.match(r'Parte\s+(\d+)', seg, re.IGNORECASE)
        if m:
            parte = int(m.group(1))
            break

    updates.append((eid, parte))

from collections import Counter
parte_counts = Counter(p for _, p in updates)
for parte, cnt in sorted(parte_counts.items()):
    print(f"  Parte {parte}: {cnt} episodes")

print(f"Total: {len(updates)}")

# Batched SQL
batch_size = 200
for i in range(0, len(updates), batch_size):
    batch = updates[i:i+batch_size]
    cases = []
    ids = []
    for eid, parte in batch:
        cases.append(f"WHEN id = {eid} THEN {parte}")
        ids.append(str(eid))
    sql = f"UPDATE episodio SET parte = CASE {' '.join(cases)} END WHERE id IN ({','.join(ids)});"

    result = subprocess.run(
        ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db',
         '--default-character-set=utf8mb4'],
        input=sql.encode('utf-8'),
        capture_output=True, timeout=60
    )
    if result.returncode != 0:
        print(f"Error at batch {i}: {result.stderr.decode(errors='replace')[-200:]}")

print("Done")
