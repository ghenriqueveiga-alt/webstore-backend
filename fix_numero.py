import re
import subprocess

def run_sql(sql):
    result = subprocess.run(
        ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4', '-e', sql],
        capture_output=True, text=True, encoding='utf-8', errors='replace'
    )
    return result.stdout

# Get all episodes with NULL numero
output = run_sql("SELECT id, programa_id, titulo, temporada FROM episodio WHERE numero IS NULL ORDER BY programa_id, id;")
lines = output.strip().split('\n')

# Parse results
episodes = []
for line in lines[1:]:  # skip header
    parts = line.split('\t')
    if len(parts) >= 3:
        ep_id = int(parts[0])
        prog_id = int(parts[1])
        titulo = parts[2]
        temporada = parts[3] if len(parts) > 3 and parts[3] != 'NULL' else '1'
        episodes.append((ep_id, prog_id, titulo, temporada))

print(f"Total episodes with NULL numero: {len(episodes)}")

def extract_number(titulo):
    """Try to extract episode number from title"""
    t = titulo.strip()

    # Pattern: "Gantz.01.BD1080p..." or "Ep.01.Tom&Jerry..."
    m = re.search(r'(?:^|[.\s])(\d{2,4})[.\s]', t)
    if m:
        return int(m.group(1))

    # Pattern: "one-piece-dublado-ep-1" or "pokemon-dublado-ep-1"
    m = re.search(r'ep-(\d+)', t, re.IGNORECASE)
    if m:
        return int(m.group(1))

    # Pattern: "[Erai-raws] Undead Unluck - 01 [...]"
    m = re.search(r'- (\d{2,4})\[', t)
    if m:
        return int(m.group(1))

    # Pattern: "S1 E01 O Homem..."
    m = re.search(r'S\d+\s+E(\d+)', t)
    if m:
        return int(m.group(1))

    # Pattern: "EP001-..." or "EP01-..."
    m = re.match(r'EP(\d+)', t, re.IGNORECASE)
    if m:
        return int(m.group(1))

    # Pattern: "Ep.01-..." 
    m = re.search(r'Ep\.(\d+)', t, re.IGNORECASE)
    if m:
        return int(m.group(1))

    # Pattern: "Ep01-..."
    m = re.search(r'Ep(\d+)', t, re.IGNORECASE)
    if m:
        return int(m.group(1))

    return None

# Group by programa_id to assign sequential numbers
from collections import defaultdict
prog_episodes = defaultdict(list)
for ep_id, prog_id, titulo, temporada in episodes:
    num = extract_number(titulo)
    prog_episodes[prog_id].append((ep_id, titulo, num))

sqls = []
for prog_id, eps in prog_episodes.items():
    # Sort by extracted number, None goes to end
    eps_with_num = [(eid, t, n) for eid, t, n in eps if n is not None]
    eps_without_num = [(eid, t, n) for eid, t, n in eps if n is None]

    # Assign sequential numbers to those without
    max_num = max((n for _, _, n in eps_with_num), default=0)
    for i, (eid, t, n) in enumerate(eps_without_num):
        max_num += 1
        sqls.append(f"UPDATE episodio SET numero = {max_num} WHERE id = {eid};")

    # Update those with extracted numbers
    for eid, t, n in eps_with_num:
        sqls.append(f"UPDATE episodio SET numero = {n} WHERE id = {eid};")

# Write SQL
with open('fix_numero.sql', 'w', encoding='utf-8') as f:
    f.write('\n'.join(sqls))

print(f"Generated {len(sqls)} UPDATE statements")
print(f"  Programs with extracted numbers: {sum(1 for p in prog_episodes if any(n is not None for _, _, n in prog_episodes[p]))}")
print(f"  Programs with sequential numbers: {sum(1 for p in prog_episodes if any(n is None for _, _, n in prog_episodes[p]))}")
