SELECT b.dia_semana_desc, b.horario, b.tipo_bloco_desc, p.nome
FROM bloco b
JOIN programa p ON b.programa_id = p.id
WHERE b.grade_id = 1
ORDER BY b.dia_semana_desc, b.horario;
