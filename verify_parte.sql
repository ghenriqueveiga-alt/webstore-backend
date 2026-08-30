SELECT e.id, e.titulo, e.numero, e.temporada, e.parte, p.nome as programa
FROM episodio e
JOIN programa p ON e.programa_id = p.id
WHERE p.nome LIKE '%Attack%'
ORDER BY e.temporada, e.parte, e.numero
LIMIT 15;
