SELECT a.id, a.caminho
FROM arquivo a
WHERE (a.caminho LIKE '%Parte 1%' OR a.caminho LIKE '%Parte 2%' OR a.caminho LIKE '%Parte 3%')
AND a.caminho NOT LIKE '%\\Parte 1\\%' AND a.caminho NOT LIKE '%\\Parte 2\\%' AND a.caminho NOT LIKE '%\\Parte 3\\%'
AND a.caminho LIKE '%.mp4'
ORDER BY a.caminho
LIMIT 30;
