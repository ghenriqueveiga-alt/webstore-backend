import subprocess
import uuid
import re

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

def get_video_files():
    sql = """SELECT id, caminho FROM arquivo 
             WHERE caminho LIKE '%.mp4' OR caminho LIKE '%.mkv' OR caminho LIKE '%.avi' 
             OR caminho LIKE '%.mov' OR caminho LIKE '%.wmv' ORDER BY caminho;"""
    output = run_sql(sql)
    files = []
    for line in output.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('id') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) == 2:
            files.append({'id': int(parts[0]), 'caminho': parts[1]})
    return files

def insert_programs(program_names):
    values = []
    for name in program_names:
        uid = str(uuid.uuid4())
        nome = name.replace("'", "\\'")
        values.append(f"('{uid}', 'Active', '{nome}', 'Anime', 'Normal')")
    
    batch_size = 50
    for i in range(0, len(values), batch_size):
        batch = values[i:i+batch_size]
        sql = f"INSERT INTO programa (uuid, status_desc, nome, tipo_desc, tipo_exibicao_desc) VALUES {', '.join(batch)};"
        run_sql(sql)
    
    print(f"Inseridos {len(values)} programas")

def get_program_map():
    sql = "SELECT id, nome FROM programa;"
    output = run_sql(sql)
    program_map = {}
    for line in output.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('id') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) == 2:
            program_map[parts[1]] = int(parts[0])
    return program_map

def parse_episode_info(caminho):
    parts = caminho.split('\\')
    if len(parts) < 3:
        return None
    
    program_name = parts[0]
    season_folder = parts[1]
    filename = parts[-1]
    
    # Extract season number
    season_match = re.search(r'(\d+)', season_folder)
    season = int(season_match.group(1)) if season_match else 1
    
    # Extract episode number from filename
    episode_match = re.search(r'(\d+)', filename)
    episode = int(episode_match.group(1)) if episode_match else 1
    
    # Extract title (remove extension and episode number prefix)
    title = re.sub(r'\.\w+$', '', filename)
    title = re.sub(r'^\d+[\s_-]*', '', title)
    
    return {
        'program_name': program_name,
        'season': season,
        'episode': episode,
        'title': title
    }

def insert_episodes(files, program_map):
    values = []
    for f in files:
        info = parse_episode_info(f['caminho'])
        if not info:
            continue
        
        program_id = program_map.get(info['program_name'])
        if not program_id:
            print(f"Programa não encontrado: {info['program_name']}")
            continue
        
        uid = str(uuid.uuid4())
        title = info['title'].replace("'", "\\'")
        values.append(f"('{uid}', 'Active', {f['id']}, '{title}', {info['episode']}, {info['season']}, {program_id}, 0, 1, 0)")
    
    batch_size = 500
    for i in range(0, len(values), batch_size):
        batch = values[i:i+batch_size]
        sql = f"""INSERT INTO episodio (uuid, status_desc, arquivo_id, titulo, numero, temporada, programa_id, processado, parte, ordem) 
                  VALUES {', '.join(batch)};"""
        run_sql(sql)
    
    print(f"Inseridos {len(values)} episódios")

if __name__ == "__main__":
    print("=== Populando tabela programa ===")
    
    # Get unique program names
    files = get_video_files()
    program_names = sorted(set(f['caminho'].split('\\')[0] for f in files))
    
    # Check if programs already exist
    existing = run_sql("SELECT COUNT(*) FROM programa;")
    count = int(existing.strip().split('\n')[-1].strip())
    
    if count == 0:
        insert_programs(program_names)
    else:
        print(f"Tabela programa já tem {count} registros")
    
    # Get program mapping
    program_map = get_program_map()
    print(f"Mapeamento: {len(program_map)} programas")
    
    print("\n=== Populando tabela episodio ===")
    insert_episodes(files, program_map)
    
    # Verify
    total = run_sql("SELECT COUNT(*) FROM episodio;")
    print(f"\nTotal de episódios: {total.strip().split(chr(10))[-1].strip()}")
