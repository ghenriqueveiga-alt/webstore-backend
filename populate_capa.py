import os
import subprocess

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

def scan_images(base_path):
    """Scan directory for image files recursively."""
    image_extensions = {'.jpg', '.jpeg', '.png', '.gif', '.bmp', '.webp'}
    images = {}
    
    for root, dirs, files in os.walk(base_path):
        for file in files:
            ext = os.path.splitext(file)[1].lower()
            if ext in image_extensions:
                # Get base name without _imagem extension
                base_name = os.path.splitext(file)[0]
                if base_name.endswith('_imagem'):
                    clean_name = base_name[:-7]  # Remove '_imagem'
                    full_path = os.path.join(root, file)
                    images[clean_name] = full_path
    
    return images

def main():
    print("Scanning F:\\ for images...")
    images = scan_images("F:\\")
    print(f"Found {len(images)} images")
    
    print("Getting episodes from database...")
    episodes = get_episodes_from_db()
    print(f"Found {len(episodes)} episodes")
    
    updates = []
    matched = 0
    not_matched = 0
    
    for ep_id, caminho in episodes:
        # Convert database path to file system path
        fs_path = caminho.replace('\\\\', '\\')
        
        # Get the base name of the episode file (without extension)
        ep_filename = os.path.splitext(os.path.basename(fs_path))[0]
        
        # Check if we have a matching image
        if ep_filename in images:
            image_path = images[ep_filename]
            # Escape for MySQL hex literal
            path_bytes = image_path.encode('utf-8')
            hex_str = path_bytes.hex()
            updates.append(f"UPDATE episodio SET capa_url = 0x{hex_str} WHERE id = {ep_id};")
            matched += 1
        else:
            not_matched += 1
    
    print(f"Matched: {matched}")
    print(f"Not matched: {not_matched}")
    
    # Save to SQL file
    if updates:
        with open('populate_capa.sql', 'w', encoding='ascii') as f:
            f.write('\n'.join(updates))
        print(f"SQL file generated with {len(updates)} updates")

if __name__ == '__main__':
    main()
