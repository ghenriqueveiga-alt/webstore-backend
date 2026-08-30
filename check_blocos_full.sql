SELECT b.id, b.dia_semana_desc, b.horario, b.tipo_bloco_desc, b.faixa_horario_desc, p.nome, p.id as programa_id, b.grade_id
FROM bloco b
JOIN programa p ON b.programa_id = p.id
WHERE b.grade_id = 1
ORDER BY CASE b.dia_semana_desc
    WHEN 'Segunda-feira' THEN 1
    WHEN 'Terça-feira' THEN 2
    WHEN 'Quarta-feira' THEN 3
    WHEN 'Quinta-feira' THEN 4
    WHEN 'Sexta-feira' THEN 5
    WHEN 'Sábado' THEN 6
    WHEN 'Domingo' THEN 7
END, b.horario;
