import os
import subprocess
import re

def get_episodes_from_db():
    """Get all episodes with their IDs and file paths from the database."""
    result = subprocess.run(
        ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db',
         '--default-character-set=utf8mb4', '-N', '-e',
         "SELECT CONCAT(e.id, '|', a.caminho) FROM episodio e JOIN arquivo a ON a.id = e.arquivo_id;"],
        capture_output=True, text=True, encoding='utf-8'
    )
    episodes = []
    for line in result.stdout.strip().split('\n'):
        if '|' in line:
            parts = line.split('|', 1)
            if len(parts) == 2:
                try:
                    ep_id = int(parts[0])
                    caminho = parts[1]
                    episodes.append((ep_id, caminho))
                except ValueError:
                    pass
    return episodes

def check_image_exists(path):
    """Check if a file exists using os.path."""
    return os.path.isfile(path)

def escape_sql_string(s):
    """Escape a string for MySQL single-quoted string."""
    return s.replace("\\", "\\\\").replace("'", "\\'")

def main():
    print("Getting episodes from database...")
    episodes = get_episodes_from_db()
    print(f"Found {len(episodes)} episodes")
    
    updates = []
    matched = 0
    not_matched = 0
    
    for ep_id, caminho in episodes:
        # Convert database path (double backslash) to file system path (single backslash)
        fs_path = caminho.replace('\\\\', '\\')
        
        # Replace .mp4 with _imagem.jpg
        image_path = fs_path.replace('.mp4', '_imagem.jpg')
        full_image_path = f"F:\\{image_path}"
        
        if check_image_exists(full_image_path):
            # Escape for SQL using hex literal to avoid all escaping issues
            path_bytes = full_image_path.encode('utf-8')
            hex_str = path_bytes.hex()
            updates.append(f"UPDATE episodio SET capa_url = 0x{hex_str} WHERE id = {ep_id};")
            matched += 1
        else:
            not_matched += 1
    
    print(f"Matched: {matched}")
    print(f"Not matched: {not_matched}")
    
    # Save to SQL file
    if updates:
        with open('fix_capa_hex.sql', 'w', encoding='ascii') as f:
            f.write('\n'.join(updates))
        print(f"SQL file generated with {len(updates)} updates")

if __name__ == '__main__':
    main()
