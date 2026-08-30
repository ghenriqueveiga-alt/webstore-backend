SELECT COUNT(*), temporada FROM episodio e JOIN programa p ON e.programa_id=p.id WHERE p.nome='Dragon Ball' GROUP BY temporada;
