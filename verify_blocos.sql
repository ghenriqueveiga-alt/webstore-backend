SELECT b.horario, b.dia_semana_desc, b.faixa_horario_desc, b.tipo_bloco_desc, p.nome
FROM bloco b
JOIN programa p ON b.programa_id = p.id
WHERE b.dia_semana_desc = 'Segunda-feira'
ORDER BY b.horario
LIMIT 20;
