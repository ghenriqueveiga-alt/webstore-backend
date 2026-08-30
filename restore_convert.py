import subprocess, os, shutil, unicodedata

FFMPEG = r"C:\Users\Henrique\AppData\Local\Microsoft\WinGet\Packages\Gyan.FFmpeg_Microsoft.Winget.Source_8wekyb3d8bbwe\ffmpeg-9.0-full_build\bin\ffmpeg.exe"
MYSQL  = r"C:\Program Files\MySQL\MySQL Workbench 8.0 CE\mysql.exe"
REC    = r"F:\Rec"
BASE   = r"F:"

def norm(s):
    return unicodedata.normalize('NFKD', s).encode('ascii','ignore').decode('ascii').lower()

def db_paths():
    r = subprocess.run([MYSQL,"-u","root","-proot","-P","3307","--default-character-set=utf8mb4","mysql-db","-N","-e",
        "SELECT caminho FROM arquivo WHERE tipo='video/mp4';"],
        capture_output=True, text=True, encoding="utf-8")
    out = {}
    for l in r.stdout.strip().split("\n"):
        l = l.strip()
        if not l: continue
        win = l.replace("\\\\", "\\")
        base = os.path.splitext(os.path.basename(win))[0]
        out[norm(base)] = os.path.join(BASE, win)
    return out

def convert(src):
    dst = src[:-4] + ".mp4"
    r = subprocess.run([FFMPEG,"-i",src,"-c:v","libx264","-preset","fast","-crf","23",
                        "-c:a","aac","-b:a","128k","-y",dst], capture_output=True, text=True)
    if r.returncode == 0 and os.path.exists(dst) and os.path.getsize(dst) > 0:
        os.remove(src)
        return True
    if os.path.exists(dst):
        os.remove(dst)
    return False

def main():
    dp = db_paths()
    avis = [f for f in os.listdir(REC) if f.lower().endswith(".avi")]
    print(f"DB: {len(dp)} | AVI in Rec: {len(avis)}")
    ok = 0
    skip = 0
    fail = []
    for i, f in enumerate(avis):
        base = os.path.splitext(f)[0]
        key = norm(base)
        if key not in dp:
            print(f"[{i+1}/{len(avis)}] {f} NO MATCH")
            fail.append(f)
            continue
        dest_dir = os.path.dirname(dp[key])
        os.makedirs(dest_dir, exist_ok=True)
        src = os.path.join(REC, f)
        mp4_check = os.path.join(dest_dir, os.path.splitext(f)[0] + ".mp4")
        if os.path.exists(mp4_check):
            os.remove(src)
            skip += 1
            print(f"[{i+1}/{len(avis)}] {f} mp4 exists -> deleted avi")
            continue
        dest_avi = os.path.join(dest_dir, f)
        shutil.move(src, dest_avi)
        print(f"[{i+1}/{len(avis)}] {f}", end=" ", flush=True)
        if convert(dest_avi):
            ok += 1
            print("OK")
        else:
            fail.append(f)
            print("FAIL")
    print(f"\nConverted: {ok} | Skipped: {skip} | Failed: {len(fail)}")
    if fail:
        for f in fail:
            print(f"  {f}")

if __name__ == "__main__":
    main()
