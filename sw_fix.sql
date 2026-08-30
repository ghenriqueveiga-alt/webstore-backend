SELECT e.numero, e.temporada, a.caminho
FROM episodio e
JOIN programa p ON e.programa_id = p.id
JOIN arquivo a ON e.arquivo_id = a.id
WHERE p.nome = 'Samurai Warriors' AND e.numero IN (3, 4);
