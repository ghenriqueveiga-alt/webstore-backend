SELECT p.nome, COUNT(e.id) as eps
FROM programa p
JOIN episodio e ON e.programa_id = p.id
JOIN bloco b ON b.programa_id = p.id
GROUP BY p.nome
ORDER BY eps DESC;
