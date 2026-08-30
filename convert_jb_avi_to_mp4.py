import subprocess
import os
import sys
import time

FFMPEG = r"C:\Users\Henrique\AppData\Local\Microsoft\WinGet\Packages\Gyan.FFmpeg_Microsoft.Winget.Source_8wekyb3d8bbwe\ffmpeg-9.0-full_build\bin\ffmpeg.exe"
BASE_DIR = r"F:\Johnny Bravo"
DB_SQL_FILE = r"C:\Users\Henrique\AppData\Local\Temp\opencode\update_jb_to_mp4.sql"

def find_avi_files():
    files = []
    for root, dirs, filenames in os.walk(BASE_DIR):
        for f in filenames:
            if f.lower().endswith('.avi'):
                files.append(os.path.join(root, f))
    files.sort()
    return files

def convert(avi_path):
    mp4_path = avi_path[:-4] + '.mp4'
    if os.path.exists(mp4_path):
        print(f"  SKIP (already exists): {os.path.basename(mp4_path)}")
        return mp4_path

    cmd = [
        FFMPEG, '-i', avi_path,
        '-c:v', 'libx264', '-preset', 'fast', '-crf', '23',
        '-c:a', 'aac', '-b:a', '128k',
        '-y', mp4_path
    ]
    result = subprocess.run(cmd, capture_output=True, text=True)
    if result.returncode != 0:
        print(f"  ERROR: {result.stderr[-200:]}")
        return None
    return mp4_path

def build_update_sql(conversions):
    lines = []
    for avi_path, mp4_path in conversions:
        db_path = avi_path.replace('F:\\', '').replace('\\', '\\\\')
        new_db_path = mp4_path.replace('F:\\', '').replace('\\', '\\\\')
        lines.append(
            f"UPDATE arquivo SET caminho = '{new_db_path}', tipo = 'video/mp4' WHERE caminho = '{db_path}';"
        )
    return '\n'.join(lines)

def main():
    avi_files = find_avi_files()
    print(f"Found {len(avi_files)} AVI files to convert\n")

    conversions = []
    failed = []

    for i, avi_path in enumerate(avi_files):
        rel = os.path.relpath(avi_path, BASE_DIR)
        print(f"[{i+1}/{len(avi_files)}] {rel}")

        mp4_path = convert(avi_path)
        if mp4_path:
            conversions.append((avi_path, mp4_path))
            print(f"  OK -> {os.path.basename(mp4_path)}")
        else:
            failed.append(avi_path)
            print(f"  FAILED")

    print(f"\nDone: {len(conversions)} converted, {len(failed)} failed")

    if conversions:
        sql = build_update_sql(conversions)
        with open(DB_SQL_FILE, 'w', encoding='utf-8') as f:
            f.write(sql)
        print(f"SQL update written to: {DB_SQL_FILE}")

if __name__ == '__main__':
    main()
