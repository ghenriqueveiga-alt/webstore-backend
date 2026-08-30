SELECT e.numero, e.temporada, e.parte
FROM episodio e
JOIN programa p ON e.programa_id = p.id
WHERE p.nome = 'Digimon - Adventure' AND e.numero <= 10
ORDER BY e.temporada, e.parte, e.numero;
