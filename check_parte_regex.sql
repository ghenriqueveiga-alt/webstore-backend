SELECT e.id, e.titulo, e.parte, e.numero, e.temporada, a.caminho
FROM episodio e
JOIN arquivo a ON e.arquivo_id = a.id
WHERE (a.caminho REGEXP 'Parte [0-9]' OR a.caminho REGEXP 'parte [0-9]' OR a.caminho REGEXP '[0-9].parte' OR a.caminho REGEXP 'Primeira Parte|Segunda Parte|Terceira Parte')
AND a.caminho LIKE '%.mp4'
AND e.parte = 0
ORDER BY a.caminho;
