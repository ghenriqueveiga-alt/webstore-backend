SELECT p.id, p.nome, COUNT(e.id) as eps FROM programa p LEFT JOIN episodio e ON e.programa_id=p.id WHERE p.nome LIKE '%Dragon Ball%' GROUP BY p.id, p.nome;
