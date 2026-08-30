SELECT e.id, e.titulo, e.parte, a.caminho
FROM episodio e
JOIN arquivo a ON e.arquivo_id = a.id
WHERE a.caminho LIKE '%parte%' OR a.caminho LIKE '%Parte%'
ORDER BY e.id
LIMIT 30;
