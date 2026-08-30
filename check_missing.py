import subprocess
import os

MYSQL = r"C:\Program Files\MySQL\MySQL Workbench 8.0 CE\mysql.exe"
BASE = r"F:"

def get_db_paths():
    cmd = [MYSQL, "-u", "root", "-proot", "-P", "3307", "--default-character-set=utf8mb4", "mysql-db", "-N", "-e",
           "SELECT caminho FROM arquivo WHERE tipo = 'video/mp4';"]
    result = subprocess.run(cmd, capture_output=True, text=True, encoding="utf-8")
    paths = []
    for line in result.stdout.strip().split("\n"):
        line = line.strip()
        if line:
            paths.append(line)
    return paths

def db_to_win(db_path):
    return "F:\\" + db_path.replace("\\\\", "\\")

def main():
    paths = get_db_paths()
    print(f"DB video/mp4 entries: {len(paths)}")

    missing = []
    found = 0
    for p in paths:
        win = db_to_win(p)
        if os.path.exists(win):
            found += 1
        else:
            missing.append(p)

    print(f"Found on disk: {found}")
    print(f"Missing: {len(missing)}")

    if missing:
        # Count by show
        from collections import Counter
        shows = Counter()
        for m in missing:
            show = m.split("\\")[0] if "\\" in m else "(root)"
            shows[show] += 1
        print("\nMissing by show:")
        for show, count in shows.most_common(30):
            print(f"  {show}: {count}")

        with open(r"C:\Users\Henrique\AppData\Local\Temp\opencode\missing_mp4.txt", "w", encoding="utf-8") as f:
            for m in missing:
                f.write(m + "\n")

if __name__ == "__main__":
    main()
