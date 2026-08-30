SELECT e.numero, e.temporada, e.parte, e.titulo FROM episodio e JOIN programa p ON e.programa_id=p.id WHERE p.nome='Dragon Ball' ORDER BY e.numero LIMIT 5;
