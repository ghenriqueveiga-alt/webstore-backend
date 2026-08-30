SELECT a.id, a.caminho
FROM arquivo a
WHERE a.caminho LIKE '%Digimon - Adventure%1ª Temporada%01_%'
AND a.caminho LIKE '%.rmvb';
