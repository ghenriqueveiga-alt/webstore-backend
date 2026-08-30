import os
import subprocess

def get_episodes_from_db():
    """Get all episodes with their IDs and file paths from the database."""
    result = subprocess.run(
        ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db',
         '--default-character-set=utf8mb4', '-N', '-e',
         "SELECT CONCAT(e.id, '|', a.caminho) FROM episodio e JOIN arquivo a ON a.id = e.arquivo_id WHERE e.capa_url IS NULL OR e.capa_url = '';"],
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

def generate_variations(name):
    """Generate multiple variations of a name for matching."""
    variations = [name]
    
    # Add version with leading zero if it's a number
    import re
    match = re.match(r'^(\d+)(.*)', name)
    if match:
        num = match.group(1)
        rest = match.group(2)
        # Add leading zero
        if len(num) == 1:
            variations.append(f"0{num}{rest}")
        # Remove leading zero
        if num.startswith('0') and len(num) > 1:
            variations.append(f"{num[1:]}{rest}")
    
    return variations

def main():
    print("Scanning F:\\ for images...")
    images = scan_images("F:\\")
    print(f"Found {len(images)} images")
    
    print("Getting episodes without capa_url from database...")
    episodes = get_episodes_from_db()
    print(f"Found {len(episodes)} episodes without capa_url")
    
    updates = []
    matched = 0
    not_matched = 0
    not_matched_list = []
    
    for ep_id, caminho in episodes:
        # Convert database path to file system path
        fs_path = caminho.replace('\\\\', '\\')
        
        # Get the base name of the episode file (without extension)
        ep_filename = os.path.splitext(os.path.basename(fs_path))[0]
        
        # Try to find matching image with variations
        found = False
        for variation in generate_variations(ep_filename):
            if variation in images:
                image_path = images[variation]
                # Escape for MySQL hex literal
                path_bytes = image_path.encode('utf-8')
                hex_str = path_bytes.hex()
                updates.append(f"UPDATE episodio SET capa_url = 0x{hex_str} WHERE id = {ep_id};")
                matched += 1
                found = True
                break
        
        if not found:
            not_matched += 1
            not_matched_list.append((ep_id, ep_filename, caminho))
    
    print(f"Matched: {matched}")
    print(f"Not matched: {not_matched}")
    
    # Show some not matched examples
    if not_matched_list:
        print("\nNot matched examples:")
        for ep_id, filename, caminho in not_matched_list[:10]:
            print(f"  ID: {ep_id}, Filename: {filename}")
    
    # Save to SQL file
    if updates:
        with open('fix_remaining_capa.sql', 'w', encoding='ascii') as f:
            f.write('\n'.join(updates))
        print(f"\nSQL file generated with {len(updates)} updates")

if __name__ == '__main__':
    main()
