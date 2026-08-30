SELECT p.nome, COUNT(*) as total, MIN(e.temporada) as min_temp, MAX(e.temporada) as max_temp
FROM episodio e
JOIN programa p ON e.programa_id = p.id
GROUP BY p.nome
HAVING max_temp > 1
ORDER BY p.nome;
