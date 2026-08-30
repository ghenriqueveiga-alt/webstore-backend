import subprocess
import re

def run_sql(sql):
    cmd = ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db', '--default-character-set=utf8mb4']
    r = subprocess.run(cmd, input=sql.encode('utf-8'), capture_output=True, timeout=30)
    return r.stdout.decode(errors='replace'), r.stderr.decode(errors='replace')

def get_arquivos():
    sql = "SELECT a.id, a.caminho FROM arquivo a JOIN episodio e ON e.arquivo_id=a.id WHERE a.duracao IS NULL ORDER BY a.id;"
    out, err = run_sql(sql)
    rows=[]
    for line in out.strip().split('\n'):
        line=line.strip()
        if not line or line.startswith('id') or line.startswith('---'):
            continue
        parts=line.split('\t')
        if len(parts)==2:
            rows.append((int(parts[0]), parts[1]))
    return rows

def probe_duration(caminho_rel):
    full = "F:\\\\" + caminho_rel.replace("\\", "\\")
    # caminho_rel already has single backslash, need to join with F:
    # Use os.path style
    import os
    full = os.path.join("F:", caminho_rel.replace("/", os.sep))
    # Actually on Windows, caminho is like "Dragon Ball\\Parte 1\\01_..."
    # Need F:\ + caminho
    full = "F:\\" + caminho_rel
    # ffprobe expects Windows path
    try:
        r = subprocess.run(['ffprobe','-v','error','-show_entries','format=duration','-of','default=noprint_wrappers=1:nokey=1', full], capture_output=True, text=True, timeout=10)
        if r.returncode==0 and r.stdout.strip():
            dur = float(r.stdout.strip())
            return int(round(dur))
    except Exception as e:
        pass
    return None

def batch_update(updates):
    if not updates:
        return
    cases=[]
    ids=[]
    for aid, sec in updates:
        cases.append(f"WHEN id={aid} THEN '{sec}'")
        ids.append(str(aid))
    sql = f"UPDATE arquivo SET duracao = CASE {' '.join(cases)} ELSE duracao END WHERE id IN ({','.join(ids)});"
    run_sql(sql)

if __name__=="__main__":
    arquivos=get_arquivos()
    print(f"Total arquivos sem duracao: {len(arquivos)}")
    batch=[]
    processed=0
    failed=0
    for aid, caminho in arquivos:
        sec=probe_duration(caminho)
        if sec is not None:
            batch.append((aid, sec))
            processed+=1
        else:
            failed+=1
            # try alternative: leave null
        if len(batch)>=100:
            batch_update(batch)
            print(f"Updated {processed} ok, {failed} fail - batch 100")
            batch=[]
    if batch:
        batch_update(batch)
        print(f"Final batch: {len(batch)}")
    print(f"Done: {processed} sucessos, {failed} falhas")
    out,_=run_sql("SELECT COUNT(*) FROM arquivo WHERE duracao IS NOT NULL;")
    print(out)
