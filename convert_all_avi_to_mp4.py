import subprocess
import os

FFMPEG = r"C:\Users\Henrique\AppData\Local\Microsoft\WinGet\Packages\Gyan.FFmpeg_Microsoft.Winget.Source_8wekyb3d8bbwe\ffmpeg-9.0-full_build\bin\ffmpeg.exe"
BASE_DIR = r"F:"

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
    cmd = [
        FFMPEG, '-i', avi_path,
        '-c:v', 'libx264', '-preset', 'fast', '-crf', '23',
        '-c:a', 'aac', '-b:a', '128k',
        '-y', mp4_path
    ]
    result = subprocess.run(cmd, capture_output=True, text=True)
    if result.returncode == 0 and os.path.exists(mp4_path) and os.path.getsize(mp4_path) > 0:
        try:
            os.remove(avi_path)
        except PermissionError:
            print("(converted, cannot delete)", end=" ")
        return True
    else:
        if os.path.exists(mp4_path):
            os.remove(mp4_path)
        return False

def main():
    avi_files = find_avi_files()
    print(f"Found {len(avi_files)} AVI files\n")

    converted = 0
    failed = []

    for i, avi_path in enumerate(avi_files):
        rel = os.path.relpath(avi_path, BASE_DIR)
        print(f"[{i+1}/{len(avi_files)}] {rel}", end=" ", flush=True)

        if convert(avi_path):
            converted += 1
            print("OK")
        else:
            failed.append(rel)
            print("FAILED")

    print(f"\nConverted+Deleted: {converted}, Failed: {len(failed)}")
    if failed:
        print("Failed files:")
        for f in failed:
            print(f"  {f}")

if __name__ == '__main__':
    main()
