import os
import uuid
import mimetypes

DRIVE = "F:\\"
OUTPUT_FILE = "populate_arquivo.sql"
BATCH_SIZE = 500

def get_mime_type(filepath):
    mime, _ = mimetypes.guess_type(filepath)
    return mime if mime else 'application/octet-stream'

def scan_files(drive):
    files = []
    for root, dirs, filenames in os.walk(drive):
        for fname in filenames:
            filepath = os.path.join(root, fname)
            try:
                size = os.path.getsize(filepath)
                caminho_db = filepath.replace(drive, "").replace("\\", "\\\\")
                files.append({
                    "nome": fname,
                    "tipo": get_mime_type(filepath),
                    "tamanho": size,
                    "caminho": caminho_db,
                    "uuid": str(uuid.uuid4()),
                    "status_desc": "Active"
                })
            except (OSError, PermissionError):
                continue
    return files

def generate_sql(files):
    lines = ["INSERT INTO arquivo (uuid, status_desc, nome, tipo, tamanho, caminho, duracao) VALUES"]
    values = []
    for f in files:
        nome = f["nome"].replace("'", "\\'")
        tipo = f["tipo"].replace("'", "\\'")
        caminho = f["caminho"].replace("'", "\\'")
        values.append(
            f"('{f['uuid']}', '{f['status_desc']}', '{nome}', '{tipo}', {f['tamanho']}, '{caminho}', NULL)"
        )

    batches = []
    for i in range(0, len(values), BATCH_SIZE):
        batch = values[i:i+BATCH_SIZE]
        batch_sql = lines[0] + "\n" + ",\n".join(batch) + ";"
        batches.append(batch_sql)

    return "\n\n".join(batches)

if __name__ == "__main__":
    print(f"Escaneando arquivos em {DRIVE}...")
    files = scan_files(DRIVE)
    print(f"Encontrados {len(files)} arquivos")

    print("Gerando SQL...")
    sql = generate_sql(files)

    with open(OUTPUT_FILE, "w", encoding="utf-8") as f:
        f.write(sql)

    print(f"SQL salvo em {OUTPUT_FILE}")
    print(f"Total de linhas SQL geradas: {sql.count('INSERT')}")
