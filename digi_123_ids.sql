SELECT e.id, e.numero, e.temporada, e.arquivo_id
FROM episodio e
JOIN programa p ON e.programa_id = p.id
WHERE p.nome = 'Digimon - Adventure' AND e.numero IN (1, 2, 3)
ORDER BY e.id;
