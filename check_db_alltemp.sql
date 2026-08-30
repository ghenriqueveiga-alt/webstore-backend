SELECT p.nome, e.temporada, COUNT(*) as cnt FROM episodio e JOIN programa p ON e.programa_id=p.id WHERE p.nome LIKE '%Dragon Ball%' GROUP BY p.nome, e.temporada ORDER BY p.nome, e.temporada;
