SELECT e.numero, e.temporada, e.parte
FROM episodio e
JOIN programa p ON e.programa_id = p.id
WHERE p.nome = 'Digimon - Adventure' AND e.temporada = 1
ORDER BY e.numero, e.parte
LIMIT 40;
