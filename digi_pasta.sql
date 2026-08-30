SELECT e.numero, e.temporada, SUBSTRING_INDEX(SUBSTRING_INDEX(a.caminho, '\\', -2), '\\', 1) as pasta
FROM episodio e
JOIN programa p ON e.programa_id = p.id
JOIN arquivo a ON e.arquivo_id = a.id
WHERE p.nome = 'Digimon - Adventure' AND e.numero <= 10
ORDER BY e.temporada, e.numero;
