SELECT COUNT(*) as total, e.temporada
FROM episodio e
JOIN programa p ON e.programa_id = p.id
WHERE p.nome = 'Digimon - Adventure'
GROUP BY e.temporada;
