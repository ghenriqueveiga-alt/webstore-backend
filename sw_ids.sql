SELECT e.id, e.numero, e.temporada
FROM episodio e
JOIN programa p ON e.programa_id = p.id
WHERE p.nome = 'Samurai Warriors' AND e.numero IN (3, 4);
