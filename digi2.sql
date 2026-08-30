SELECT e.numero, e.temporada, e.parte, e.titulo
FROM episodio e
JOIN programa p ON e.programa_id = p.id
WHERE p.nome = 'Digimon - Adventure'
ORDER BY e.temporada, e.parte, e.numero;
