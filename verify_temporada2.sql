SELECT e.id, e.titulo, e.numero, e.temporada, e.parte, p.nome as programa, a.caminho
FROM episodio e
JOIN programa p ON e.programa_id = p.id
JOIN arquivo a ON e.arquivo_id = a.id
WHERE p.nome LIKE '%Naruto%' AND a.caminho LIKE '%2ª%'
ORDER BY e.temporada, e.numero
LIMIT 15;
