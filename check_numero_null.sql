SELECT e.id, e.titulo, e.numero, a.caminho
FROM episodio e
JOIN arquivo a ON e.arquivo_id = a.id
WHERE e.numero IS NULL OR e.numero = ''
LIMIT 10;
