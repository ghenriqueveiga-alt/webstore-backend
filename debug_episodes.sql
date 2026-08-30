SELECT e.id, e.arquivo_id, a.caminho as video_caminho
FROM episodio e 
JOIN arquivo a ON e.arquivo_id = a.id 
WHERE a.caminho LIKE '%Another%'
ORDER BY e.numero 
LIMIT 5;
