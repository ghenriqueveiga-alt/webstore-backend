SELECT COUNT(*) as total, p.nome
FROM episodio e
JOIN programa p ON e.programa_id = p.id
WHERE p.nome IN ('Samurai Warriors', 'Digimon - Adventure')
GROUP BY p.nome;
