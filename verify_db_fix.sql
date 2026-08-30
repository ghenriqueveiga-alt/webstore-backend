SELECT p.nome, e.temporada, COUNT(*) FROM episodio e JOIN programa p ON e.programa_id=p.id WHERE p.nome LIKE '%Dragon Ball%' GROUP BY p.nome, e.temporada;
