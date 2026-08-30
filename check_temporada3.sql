SELECT DISTINCT 
    SUBSTRING_INDEX(SUBSTRING_INDEX(caminho, '\\', 1), '\\', -1) as programa,
    SUBSTRING_INDEX(SUBSTRING_INDEX(caminho, '\\', 2), '\\', -1) as pasta_temporada
FROM arquivo 
WHERE caminho LIKE '%.mp4' 
HAVING pasta_temporada LIKE '%Temporada%' OR pasta_temporada LIKE '%Season%' OR pasta_temporada LIKE '%season%'
ORDER BY programa, pasta_temporada
LIMIT 50;
