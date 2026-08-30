SELECT e.id, e.titulo, e.numero, e.temporada, p.nome as programa, a.tamanho as tamanho_mb 
FROM episodio e 
JOIN programa p ON e.programa_id = p.id 
JOIN arquivo a ON e.arquivo_id = a.id 
ORDER BY p.nome, e.temporada, e.numero 
LIMIT 15;
