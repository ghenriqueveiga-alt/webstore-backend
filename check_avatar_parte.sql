SELECT e.id, e.titulo, e.numero, e.temporada, e.parte, a.caminho
FROM episodio e
JOIN programa p ON e.programa_id = p.id
JOIN arquivo a ON e.arquivo_id = a.id
WHERE p.nome LIKE '%Avatar%' AND a.caminho LIKE '%Parte%'
ORDER BY e.temporada, e.parte, e.numero;
