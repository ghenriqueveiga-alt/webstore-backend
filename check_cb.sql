SELECT temporada, COUNT(*) FROM episodio e JOIN programa p ON e.programa_id=p.id WHERE p.nome='Cowboy Bebop' GROUP BY temporada;
