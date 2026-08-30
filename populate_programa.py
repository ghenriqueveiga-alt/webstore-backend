import os
import subprocess

# Scan F:\ for top-level folders
folders = []
for entry in os.scandir('F:\\'):
    if entry.is_dir() and entry.name not in ('$RECYCLE.BIN', 'System Volume Information'):
        folders.append(entry.name)

folders.sort()

# Build INSERT statements
sqls = []
for name in folders:
    # Escape single quotes for SQL
    safe_name = name.replace("'", "''")
    sqls.append(
        f"INSERT INTO programa (nome, status_desc, tipo_desc, uuid, temporadas) "
        f"VALUES ('{safe_name}', 'Active', 'Anime', UUID(), 1);"
    )

with open('populate_programa.sql', 'w', encoding='utf-8') as f:
    f.write('\n'.join(sqls))

print(f"Generated {len(sqls)} INSERT statements for programa")
for s in sqls:
    print(s[:80])
