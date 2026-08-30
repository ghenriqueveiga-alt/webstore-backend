SELECT DISTINCT 
    SUBSTRING_INDEX(SUBSTRING_INDEX(caminho, '\\', 1), '\\', -1) as programa,
    CASE 
        WHEN caminho LIKE '%\\Parte 1\\%' THEN 1
        WHEN caminho LIKE '%\\Parte 2\\%' THEN 2
        WHEN caminho LIKE '%\\Parte 3\\%' THEN 3
        WHEN caminho LIKE '%\\Parte 4\\%' THEN 4
        WHEN caminho LIKE '%\\Parte 5\\%' THEN 5
        ELSE NULL
    END as parte
FROM arquivo 
WHERE caminho LIKE '%.mp4' AND caminho LIKE '%Parte%'
ORDER BY programa, parte;
