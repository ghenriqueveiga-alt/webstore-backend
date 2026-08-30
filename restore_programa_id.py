import subprocess

# Manual mapping: folder_name -> program nome in DB
FOLDER_MAP = {
    'As Meninas Super Poderosas - Incompleto': 'As Meninas Super Poderosas',
    'Avatar': 'Avatar - Aang',
    'Baki': 'Baki - Hanma',
    'Coragem - O Cão Covarde - Incompleto': 'Coragem - O Cão Covarde',
    'Digimon - Incompleto': 'Digimon - Adventure',
    'Hanna-Barbera - Incompleto': 'Hanna-Barbera',
    'Johnny Bravo - Incompleto': 'Johnny Bravo',
    'Looney Tones - Incompleto': 'Looney Tones',
    'Medabots': 'Medabots - Medabots',
    'Os Cavaleiros do Zodíaco': 'Os Cavaleiros do Zodíaco - Asgard',
    'Papa Léguas - Incompleto': 'Papa Léguas',
    'Pica Pau - Incompleto': 'Pica Pau',
    'Pokémon - Incompleto': 'Pokémon',
    'Record of Ragnarok - Incompleto': 'Record of Ragnarok',
    'Tiny Toons - Incompleto': 'Tiny Toons',
    'Tom e Jerry - Incompleto': 'Tom e Jerry',
    'Yu-Gi-Oh! - Incompleto': 'Yu-Gi-Oh!',
}

# Get program id->nome
result = subprocess.run(
    ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db',
     '--default-character-set=utf8mb4', '-e', "SELECT id, nome FROM programa;"],
    capture_output=True, timeout=30
)
lines = result.stdout.decode(errors='replace').strip().split('\n')
progs = {}
for line in lines[1:]:
    parts = line.split('\t')
    if len(parts) >= 2:
        progs[parts[1].strip()] = int(parts[0])

# Get unmatched episodes
result = subprocess.run(
    ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db',
     '--default-character-set=utf8mb4', '-e',
     "SELECT e.id, a.caminho FROM episodio e JOIN arquivo a ON e.arquivo_id = a.id WHERE e.programa_id IS NULL;"],
    capture_output=True, timeout=60
)
lines = result.stdout.decode(errors='replace').strip().split('\n')
eps = []
for line in lines[1:]:
    parts = line.split('\t', 1)
    if len(parts) >= 2:
        eps.append((int(parts[0]), parts[1].strip()))

print(f"Episodes to match: {len(eps)}")

updates = []
still_unmatched = set()
for eid, caminho in eps:
    first_sep = caminho.find('\\')
    if first_sep < 0:
        first_sep = caminho.find('/')
    folder_name = caminho[:first_sep] if first_sep >= 0 else caminho

    # Try direct match first
    if folder_name in progs:
        updates.append((eid, progs[folder_name]))
    # Try manual mapping
    elif folder_name in FOLDER_MAP:
        target = FOLDER_MAP[folder_name]
        if target in progs:
            updates.append((eid, progs[target]))
        else:
            still_unmatched.add(f"{folder_name} -> {target} (not found)")
    else:
        still_unmatched.add(folder_name)

print(f"Matched: {len(updates)}")
if still_unmatched:
    print(f"Still unmatched: {len(still_unmatched)}")
    for s in sorted(still_unmatched):
        print(f"  - {s}")

# Write SQL
sqls = []
for eid, pid in updates:
    sqls.append(f"UPDATE episodio SET programa_id = {pid} WHERE id = {eid};")

with open('restore_programa_id.sql', 'w', encoding='utf-8') as f:
    f.write('\n'.join(sqls))

print(f"Generated {len(sqls)} UPDATE statements")
