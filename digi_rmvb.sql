SELECT a.id, a.caminho
FROM arquivo a
WHERE a.caminho LIKE '%Digimon - Adventure%1ª Temporada%'
AND a.caminho LIKE '%.rmvb'
ORDER BY a.caminho
LIMIT 10;
