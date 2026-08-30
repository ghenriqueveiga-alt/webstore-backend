SELECT b.id, b.dia_semana_desc, b.horario, p.nome, p.id as pid FROM bloco b JOIN programa p ON b.programa_id=p.id WHERE p.nome LIKE '%Dragon Ball%' LIMIT 5;
