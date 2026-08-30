import subprocess
import os
import shutil
import unicodedata

FFMPEG = r"C:\Users\Henrique\AppData\Local\Microsoft\WinGet\Packages\Gyan.FFmpeg_Microsoft.Winget.Source_8wekyb3d8bbwe\ffmpeg-9.0-full_build\bin\ffmpeg.exe"
MYSQL = r"C:\Program Files\MySQL\MySQL Workbench 8.0 CE\mysql.exe"
REC_DIR = r"F:\Rec"
BASE = r"F:"

def normalize(s):
    return unicodedata.normalize('NFKD', s).encode('ascii', 'ignore').decode('ascii').lower().strip()

def get_db_paths():
    cmd = [MYSQL, "-u", "root", "-proot", "-P", "3307", "--default-character-set=utf8mb4", "mysql-db", "-N", "-e",
           "SELECT caminho FROM arquivo WHERE caminho LIKE 'Pica Pau%%' AND tipo='video/mp4' UNION ALL "
           "SELECT caminho FROM arquivo WHERE caminho LIKE 'Tom e Jerry%%' AND tipo='video/mp4' UNION ALL "
           "SELECT caminho FROM arquivo WHERE caminho LIKE 'Sakura%%' AND tipo='video/mp4' UNION ALL "
           "SELECT caminho FROM arquivo WHERE caminho LIKE 'Papa L%%guas%%' AND tipo='video/mp4';"]
    result = subprocess.run(cmd, capture_output=True, text=True, encoding="utf-8")
    paths = {}
    for line in result.stdout.strip().split("\n"):
        line = line.strip()
        if not line:
            continue
        # MySQL outputs \\\\ for single \, convert to \
        win = line.replace("\\\\", "\\")
        # win is relative like "Pica Pau\filename.mp4"
        fname = os.path.splitext(os.path.basename(win))[0]
        paths[normalize(fname)] = os.path.join(BASE, win)
    return paths

def convert(avi_path):
    mp4_path = avi_path[:-4] + '.mp4'
    cmd = [FFMPEG, '-i', avi_path, '-c:v', 'libx264', '-preset', 'fast', '-crf', '23',
           '-c:a', 'aac', '-b:a', '128k', '-y', mp4_path]
    result = subprocess.run(cmd, capture_output=True, text=True)
    return result.returncode == 0 and os.path.exists(mp4_path) and os.path.getsize(mp4_path) > 0

def main():
    db_paths = get_db_paths()
    print(f"DB entries loaded: {len(db_paths)}")

    avi_files = [f for f in os.listdir(REC_DIR) if f.lower().endswith('.avi')]
    print(f"Recovered .avi files: {len(avi_files)}\n")

    converted = 0
    moved = 0
    failed = []

    for i, fname in enumerate(avi_files):
        base = os.path.splitext(fname)[0]
        key = normalize(base)

        if key in db_paths:
            dest_dir = os.path.dirname(db_paths[key])
            dest_avi = os.path.join(dest_dir, fname)
            src = os.path.join(REC_DIR, fname)

            os.makedirs(dest_dir, exist_ok=True)
            shutil.move(src, dest_avi)
            moved += 1

            # Skip conversion if .mp4 already exists
            dest_mp4 = dest_avi[:-4] + '.mp4'
            if os.path.exists(dest_mp4):
                os.remove(dest_avi)
                converted += 1
                print(f"[{i+1}/{len(avi_files)}] {fname} (mp4 exists, deleted avi)")
                continue

            print(f"[{i+1}/{len(avi_files)}] {fname}", end=" ", flush=True)
            if convert(dest_avi):
                os.remove(dest_avi)
                converted += 1
                print("OK")
            else:
                failed.append(fname)
                print("CONVERT FAILED")
        else:
            failed.append(fname)
            print(f"[{i+1}/{len(avi_files)}] {fname} NO DB MATCH")

    print(f"\nMoved: {moved}, Converted: {converted}, Failed: {len(failed)}")
    if failed:
        print("Failed:")
        for f in failed:
            print(f"  {f}")

if __name__ == '__main__':
    main()
