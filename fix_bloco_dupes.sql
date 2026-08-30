CREATE TEMPORARY TABLE tmp_keep AS 
SELECT MIN(id) as keep_id FROM bloco GROUP BY dia_semana_desc, horario, programa_id;

DELETE FROM bloco WHERE id NOT IN (SELECT keep_id FROM tmp_keep);

DROP TEMPORARY TABLE tmp_keep;
