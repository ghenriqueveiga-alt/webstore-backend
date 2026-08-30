import subprocess
import uuid

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=300)
    return result.stdout.decode(errors='replace')

SCHEDULE = [
    ("00:00", "Re:Zero"),
    ("00:30", "Parasyte"),
    ("01:00", "Vinland Saga"),
    ("01:30", "Hellsing"),
    ("02:00", "Berserk"),
    ("02:30", "Stains;Gate0"),
    ("03:00", "Serial Experiments Lain"),
    ("03:30", "Trigun"),
    ("04:00", "Cowboy Bebop"),
    ("04:30", "Dragon Quest"),
    ("05:00", "Hanna-Barbera"),
    ("05:30", "Tom e Jerry"),
    ("06:00", "Tom e Jerry"),
    ("06:30", "Pica Pau"),
    ("07:00", "Looney Tones"),
    ("07:30", "Tiny Toons"),
    ("08:00", "Papa L\u00e9guas"),
    ("08:30", "Coragem"),
    ("09:00", "As Meninas Super Poderosas"),
    ("09:30", "Johnny Bravo"),
    ("10:00", "Samurai Jack"),
    ("10:30", "As Aventuras de Jackie Chan"),
    ("11:00", "Avatar - Aang"),
    ("11:30", "Medabots"),
    ("12:00", "Pok\u00e9mon"),
    ("12:30", "Digimon - Adventure"),
    ("13:00", "Yu-Gi-Oh!"),
    ("13:30", "Naruto"),
    ("14:00", "Bleach"),
    ("14:30", "One Piece - Incompleto"),
    ("15:00", "Nanatsu no Taizai"),
    ("15:30", "Full Metal Alchemist - Brotherhood"),
    ("16:00", "Sailor Moon"),
    ("16:30", "Sakura Card Captor"),
    ("17:00", "Inuyasha"),
    ("17:30", "Samurai X"),
    ("18:00", "Dragon Ball"),
    ("18:30", "Shurato"),
    ("19:00", "Os Cavaleiros do Zod\u00edaco - Doze Casas"),
    ("19:30", "Yu Yu Hakusho"),
    ("20:00", "Samurai Warriors"),
    ("20:30", "Neon Genesis Evangelion"),
    ("21:00", "Baki - Hanma"),
    ("21:30", "One Punch Man"),
    ("22:00", "Hunter x Hunter"),
    ("22:30", "Sword Art Online"),
    ("23:00", "Overlord"),
    ("23:30", "Death Note"),
]

DIAS = [
    "Segunda-feira",
    "Ter\u00e7a-feira",
    "Quarta-feira",
    "Quinta-feira",
    "Sexta-feira",
    "S\u00e1bado",
    "Domingo",
]

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

def create_grade():
    uid = str(uuid.uuid4())
    sql = f"INSERT INTO grade (uuid, nome, descricao, grade_ativa, status_desc, periodo_inicio, periodo_fim) VALUES ('{uid}', 'Grade Principal', 'Grade de exibi\u00e7\u00e3o semanal', b'1', 'Active', '2026-01-01', '2026-12-31');"
    run_sql(sql)
    result = run_sql("SELECT LAST_INSERT_ID();")
    grade_id = int(result.strip().split('\n')[-1].strip())
    print(f"Grade criada com ID: {grade_id}")
    return grade_id

def get_faixa_horario(horario):
    h = int(horario.split(':')[0])
    if 0 <= h < 6:
        return "Madrugada"
    elif 6 <= h < 12:
        return "Manh\u00e3"
    elif 12 <= h < 18:
        return "Tarde"
    elif 18 <= h < 21:
        return "Noite"
    else:
        return "Prime Time"

def create_blocos(grade_id, program_map):
    values = []
    missing = []
    
    for dia in DIAS:
        for horario, programa_nome in SCHEDULE:
            program_id = program_map.get(programa_nome)
            if not program_id:
                missing.append(programa_nome)
                continue
            
            uid = str(uuid.uuid4())
            faixa = get_faixa_horario(horario)
            values.append(f"('{uid}', '{dia}', '{faixa}', '{horario}', 'Active', 'In\u00e9dito', {grade_id}, {program_id})")
    
    if missing:
        print(f"Programas n\u00e3o encontrados: {set(missing)}")
    
    batch_size = 50
    for i in range(0, len(values), batch_size):
        batch = values[i:i+batch_size]
        sql = f"""INSERT INTO bloco (uuid, dia_semana_desc, faixa_horario_desc, horario, status_desc, tipo_bloco_desc, grade_id, programa_id) 
                  VALUES {', '.join(batch)};"""
        run_sql(sql)
    
    print(f"Total de blocos criados: {len(values)}")

if __name__ == "__main__":
    print("Buscando programas...")
    program_map = get_program_map()
    print(f"Programas mapeados: {len(program_map)}")
    
    print("\nCriando grade...")
    grade_id = create_grade()
    
    print("\nCriando blocos...")
    create_blocos(grade_id, program_map)
    
    # Verify
    total = run_sql("SELECT COUNT(*) FROM bloco;")
    print(f"\nTotal de blocos na tabela: {total.strip().split(chr(10))[-1].strip()}")
