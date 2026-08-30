import subprocess
import math

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    result = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=60)
    stderr = result.stderr.decode(errors='replace')
    if 'Error' in stderr:
        print(f"SQL ERROR: {stderr[:200]}")
    return result.stdout.decode(errors='replace')

def get_max_episodes():
    out = run_sql("""SELECT MAX(cnt) FROM (
        SELECT COUNT(e.id) as cnt FROM programa p
        JOIN episodio e ON e.programa_id = p.id
        JOIN bloco b ON b.programa_id = p.id
        GROUP BY p.id
    ) sub;""")
    for line in out.strip().split('\n'):
        line = line.strip()
        if line and line.isdigit():
            return int(line)
    return 0

def get_blocos_for_grade():
    out = run_sql("""SELECT b.dia_semana_desc, b.faixa_horario_desc, b.horario, b.tipo_bloco_desc, b.programa_id
        FROM bloco b WHERE b.grade_id = 1;""")
    blocos = []
    for line in out.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('dia') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) == 5:
            blocos.append(tuple(parts))
    return blocos

def create_all_grades_and_blocos(pages, blocos):
    grade_values = []
    for page in range(2, pages + 1):
        start_ep = (page - 1) * 7 + 1
        end_ep = page * 7
        nome = f"Semana {page}: Ep {start_ep:02d}-{end_ep:02d}"
        grade_values.append(f"(UUID(), 'Active', '{nome}')")
    
    all_grades_sql = f"INSERT INTO grade (uuid, status_desc, nome) VALUES {','.join(grade_values)};"
    run_sql(all_grades_sql)
    
    out = run_sql("SELECT id, nome FROM grade WHERE nome LIKE 'Semana%' ORDER BY id;")
    grade_ids = []
    for line in out.strip().split('\n'):
        line = line.strip()
        if not line or line.startswith('id') or line.startswith('---'):
            continue
        parts = line.split('\t')
        if len(parts) == 2:
            grade_ids.append(int(parts[0]))
    
    print(f"Created {len(grade_ids)} grades")
    
    bloco_values = []
    for gid in grade_ids:
        for b in blocos:
            bloco_values.append(
                f"(UUID(), 'Active', '{b[0]}', '{b[1]}', '{b[2]}', '{b[3]}', {gid}, {b[4]})"
            )
    
    batch_size = 500
    total = len(bloco_values)
    for i in range(0, total, batch_size):
        batch = bloco_values[i:i+batch_size]
        sql = f"""INSERT INTO bloco (uuid, status_desc, dia_semana_desc, faixa_horario_desc, horario, tipo_bloco_desc, grade_id, programa_id)
            VALUES {','.join(batch)};"""
        run_sql(sql)
        print(f"  Inserted {min(i+batch_size, total)}/{total} blocos")

if __name__ == "__main__":
    max_eps = get_max_episodes()
    pages = math.ceil(max_eps / 7)
    print(f"Max episodes: {max_eps}, Pages: {pages}")
    
    blocos = get_blocos_for_grade()
    print(f"Blocos per grade: {len(blocos)}")
    
    create_all_grades_and_blocos(pages, blocos)
    print("Done!")
