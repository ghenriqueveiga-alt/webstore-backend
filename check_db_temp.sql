SELECT e.numero, e.temporada, e.parte FROM episodio e JOIN programa p ON e.programa_id=p.id WHERE p.nome='Dragon Ball' GROUP BY e.temporada, e.parte, e.numero ORDER BY e.temporada, e.numero LIMIT 20;
