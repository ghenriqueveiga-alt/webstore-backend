DELETE FROM bloco WHERE programa_id NOT IN (SELECT id FROM programa);
