import subprocess, os

MYSQL = r"C:\Program Files\MySQL\MySQL Workbench 8.0 CE\mysql.exe"
BASE = r"F:"

r = subprocess.run([MYSQL, "-u", "root", "-proot", "-P", "3307", "--default-character-set=utf8mb4", "mysql-db", "-N", "-e",
    "SELECT id, caminho FROM arquivo WHERE tipo='video/mp4';"],
    capture_output=True, text=True, encoding="utf-8")

missing_ids = []
for line in r.stdout.strip().split("\n"):
    line = line.strip()
    if not line:
        continue
    parts = line.split("\t")
    if len(parts) != 2:
        continue
    aid, caminho = parts
    win = caminho.replace("\\\\", "\\")
    full = os.path.join(BASE, win)
    mp4 = full
    if not os.path.exists(mp4):
        missing_ids.append(aid)

print(f"Orphaned arquivo entries: {len(missing_ids)}")
if missing_ids:
    ids = ",".join(missing_ids)
    r2 = subprocess.run([MYSQL, "-u", "root", "-proot", "-P", "3307", "--default-character-set=utf8mb4", "mysql-db", "-e",
        f"UPDATE episodio SET arquivo_id = NULL WHERE arquivo_id IN ({ids});"],
        capture_output=True, text=True, encoding="utf-8")
    if r2.returncode == 0:
        print(f"Nulled arquivo_id in episodio")
    else:
        print(f"Update error: {r2.stderr}")
    r3 = subprocess.run([MYSQL, "-u", "root", "-proot", "-P", "3307", "--default-character-set=utf8mb4", "mysql-db", "-e",
        f"DELETE FROM arquivo WHERE id IN ({ids});"],
        capture_output=True, text=True, encoding="utf-8")
    if r3.returncode == 0:
        print(f"Deleted {len(missing_ids)} arquivo entries")
    else:
        print(f"Delete error: {r3.stderr}")
