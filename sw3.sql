SELECT e.numero, e.temporada, e.parte
FROM episodio e
JOIN programa p ON e.programa_id = p.id
WHERE p.nome = 'Samurai Warriors'
ORDER BY e.numero, e.parte;
