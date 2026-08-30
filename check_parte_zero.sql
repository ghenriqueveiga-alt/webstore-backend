SELECT e.id, e.titulo, e.parte, e.numero, e.temporada, a.caminho
FROM episodio e
JOIN arquivo a ON e.arquivo_id = a.id
WHERE (a.caminho LIKE '%parte%' OR a.caminho LIKE '%Parte%')
AND a.caminho LIKE '%.mp4'
AND e.parte = 0
ORDER BY a.caminho
LIMIT 30;
