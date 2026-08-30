SELECT e.id, e.numero, e.temporada, a.caminho
FROM episodio e
JOIN programa p ON e.programa_id = p.id
JOIN arquivo a ON e.arquivo_id = a.id
WHERE p.nome = 'Digimon - Adventure' AND e.numero <= 5
ORDER BY e.numero, e.temporada;
