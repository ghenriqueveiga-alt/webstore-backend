import uuid

manha_programs = [
    220, 221, 222, 223, 224, 225, 226, 227,
    265, 268, 269, 246,
    204, 215, 282, 245, 285, 264, 241, 237, 262, 290,
]

tarde_programs = [
    229, 230, 231, 232, 233, 279,
    250, 252, 211, 253, 212, 219, 243, 234, 217, 213,
    280, 284, 275, 228, 249,
    254, 255, 256, 257, 258, 259, 260, 289, 271,
]

noite_programs = [
    218, 205, 210, 214, 216, 239, 242, 251, 261, 266,
    277, 283, 286, 208, 248, 236, 263, 235, 238, 244,
    287, 288, 281, 240, 272, 274,
]

madrugada_programs = [
    206, 270, 203, 202, 267, 273, 278,
]

manha_horarios = ['06:00', '06:30', '07:00', '07:30', '08:00', '08:30',
                  '09:00', '09:30', '10:00', '10:30', '11:00', '11:30']
tarde_horarios = ['12:00', '12:30', '13:00', '13:30', '14:00', '14:30',
                  '15:00', '15:30', '16:00', '16:30', '17:00', '17:30']
noite_horarios = ['18:00', '18:30', '19:00', '19:30', '20:00', '20:30',
                  '21:00', '21:30', '22:00', '22:30', '23:00', '23:30']
madrugada_horarios = ['00:00', '00:30', '01:00', '01:30', '02:00', '02:30',
                      '03:00', '03:30', '04:00', '04:30', '05:00', '05:30']

dias = ['Segunda-feira', 'Terca-feira', 'Quarta-feira', 'Quinta-feira',
        'Sexta-feira', 'Sabado', 'Domingo']

faixa_map = {}
for h in manha_horarios: faixa_map[h] = 'Manha'
for h in tarde_horarios: faixa_map[h] = 'Tarde'
for h in noite_horarios: faixa_map[h] = 'Noite'
for h in madrugada_horarios: faixa_map[h] = 'Madrugada'


def make_bloco(grade_id, programa_id, horario, dia, faixa):
    u = str(uuid.uuid4())
    return (
        f"INSERT INTO bloco (horario, status_desc, uuid, grade_id, programa_id, "
        f"dia_semana_desc, faixa_horario_desc, tipo_bloco_desc) "
        f"VALUES ('{horario}', 'Active', '{u}', {grade_id}, {programa_id}, "
        f"'{dia}', '{faixa}', 'Reprise');"
    )


def build_schedule(grade_id, programs, horarios):
    sqls = []
    n_prog = len(programs)
    n_slots = len(horarios)
    offset = 0
    for dia in dias:
        used = set()
        for horario in horarios:
            prog = programs[offset % n_prog]
            attempts = 0
            while prog in used and attempts < n_prog:
                offset += 1
                prog = programs[offset % n_prog]
                attempts += 1
            if prog not in used:
                used.add(prog)
                faixa = faixa_map[horario]
                sqls.append(make_bloco(grade_id, prog, horario, dia, faixa))
            offset += 1
    return sqls


sqls = []
sqls += build_schedule(1, manha_programs, manha_horarios)
g1 = len(sqls)

sqls += build_schedule(2, tarde_programs, tarde_horarios)
g2 = len(sqls) - g1

sqls += build_schedule(3, noite_programs, noite_horarios)
g3 = len(sqls) - g1 - g2

sqls += build_schedule(4, madrugada_programs, madrugada_horarios)
g4 = len(sqls) - g1 - g2 - g3

with open('populate_bloco.sql', 'w', encoding='utf-8') as f:
    f.write('\n'.join(sqls))

print(f"Generated {len(sqls)} bloco INSERT statements")
print(f"  Grade 1 (Manha): {g1} blocos ({g1 // 7} per day)")
print(f"  Grade 2 (Tarde): {g2} blocos ({g2 // 7} per day)")
print(f"  Grade 3 (Noite): {g3} blocos ({g3 // 7} per day)")
print(f"  Grade 4 (Madrugada): {g4} blocos ({g4 // 7} per day)")

# Verify no duplicates per day per grade
from collections import Counter
for gid, name, horarios in [(1, 'Manha', manha_horarios), (2, 'Tarde', tarde_horarios),
                             (3, 'Noite', noite_horarios), (4, 'Madrugada', madrugada_horarios)]:
    start = {1: 0, 2: g1, 3: g1+g2, 4: g1+g2+g3}[gid]
    end = start + {1: g1, 2: g2, 3: g3, 4: g4}[gid]
    grade_sqls = sqls[start:end]
    for dia in dias:
        dia_sqls = [s for s in grade_sqls if f"'{dia}'" in s]
        progs = [s.split(', ')[4] for s in dia_sqls]
        dupes = [p for p, c in Counter(progs).items() if c > 1]
        if dupes:
            print(f"  WARNING: {name} {dia} has duplicate programs: {dupes}")
