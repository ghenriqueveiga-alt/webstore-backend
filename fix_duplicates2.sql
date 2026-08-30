CREATE TEMPORARY TABLE tmp_dupes AS 
SELECT MIN(id) as keep_id FROM programa WHERE nome IN ('Dragon Quest', 'Vinland Saga') GROUP BY nome;

DELETE FROM programa WHERE nome IN ('Dragon Quest', 'Vinland Saga') AND id NOT IN (SELECT keep_id FROM tmp_dupes);

DROP TEMPORARY TABLE tmp_dupes;
