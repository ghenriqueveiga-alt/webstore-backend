import subprocess

# Read the SQL file and batch UPDATEs
with open('restore_programa_id.sql', 'r', encoding='utf-8') as f:
    lines = [l.strip() for l in f.readlines() if l.strip()]

# Parse (episodio_id, programa_id) from UPDATE statements
updates = []
for line in lines:
    # UPDATE episodio SET programa_id = 296 WHERE id = 7216;
    parts = line.split()
    pid = int(parts[5])
    eid = int(parts[9].rstrip(';'))
    updates.append((eid, pid))

print(f"Total updates: {len(updates)}")

# Batch into single SQL with multiple WHEN clauses
batch_size = 200
for i in range(0, len(updates), batch_size):
    batch = updates[i:i+batch_size]
    cases = []
    ids = []
    for eid, pid in batch:
        cases.append(f"WHEN id = {eid} THEN {pid}")
        ids.append(str(eid))
    sql = f"UPDATE episodio SET programa_id = CASE {' '.join(cases)} END WHERE id IN ({','.join(ids)});"
    
    result = subprocess.run(
        ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db',
         '--default-character-set=utf8mb4'],
        input=sql.encode('utf-8'),
        capture_output=True, timeout=60
    )
    if result.returncode != 0:
        print(f"Error at batch {i}: {result.stderr.decode(errors='replace')[-200:]}")
    else:
        print(f"Applied {min(i+batch_size, len(updates))}/{len(updates)}")

print("Done")
