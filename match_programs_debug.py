import subprocess

def get_programs_from_db():
    """Get all programs from the database."""
    result = subprocess.run(
        ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db',
         '--default-character-set=utf8mb4', '-N', '-e',
         "SELECT CONCAT(id, '|', nome) FROM programa;"],
        capture_output=True, text=True, encoding='utf-8'
    )
    programs = {}
    for line in result.stdout.strip().split('\n'):
        if '|' in line:
            parts = line.split('|', 1)
            if len(parts) == 2:
                try:
                    prog_id = int(parts[0])
                    nome = parts[1]
                    programs[nome] = prog_id
                except ValueError:
                    pass
    return programs

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

def extract_program_name(caminho):
    """Extract the program name from the file path."""
    # Path format: "Program Name\\Season\\Episode.mp4" or "Program Name\\Episode.mp4"
    # The program name is everything before the first double backslash
    parts = caminho.split('\\\\')
    if parts:
        return parts[0]
    return None

def main():
    print("Getting programs from database...")
    programs = get_programs_from_db()
    print(f"Found {len(programs)} programs")
    
    # Debug: Check if "Buko no Hero" is in the programs
    print(f"\nDebug: 'Buko no Hero' in programs: {'Buko no Hero' in programs}")
    print(f"Debug: Program keys (first 10): {list(programs.keys())[:10]}")
    
    print("\nGetting episodes from database...")
    episodes = get_episodes_from_db()
    print(f"Found {len(episodes)} episodes")
    
    # Debug: Check some episodes
    print("\nDebug: First 5 episodes:")
    for ep_id, caminho in episodes[:5]:
        prog_name = extract_program_name(caminho)
        print(f"  ID: {ep_id}, Extracted: [{prog_name}], In programs: {prog_name in programs if prog_name else False}")
    
    updates = []
    matched = 0
    not_matched = 0
    not_matched_list = []
    
    for ep_id, caminho in episodes:
        prog_name = extract_program_name(caminho)
        
        if prog_name and prog_name in programs:
            prog_id = programs[prog_name]
            updates.append(f"UPDATE episodio SET programa_id = {prog_id} WHERE id = {ep_id};")
            matched += 1
        else:
            not_matched += 1
            if not_matched <= 10:
                not_matched_list.append((ep_id, prog_name, caminho))
    
    print(f"\nMatched: {matched}")
    print(f"Not matched: {not_matched}")
    
    # Show some not matched examples
    if not_matched_list:
        print("\nNot matched examples:")
        for ep_id, prog_name, caminho in not_matched_list:
            print(f"  ID: {ep_id}, Program: [{prog_name}], Path: {caminho[:60]}...")
    
    # Save to SQL file
    if updates:
        with open('update_programa_id.sql', 'w', encoding='ascii') as f:
            f.write('\n'.join(updates))
        print(f"\nSQL file generated with {len(updates)} updates")

if __name__ == '__main__':
    main()
