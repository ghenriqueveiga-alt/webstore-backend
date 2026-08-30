SELECT e.id, e.capa_url, a.caminho 
FROM episodio e 
JOIN arquivo a ON CAST(e.capa_url AS UNSIGNED) = a.id 
LIMIT 5;
