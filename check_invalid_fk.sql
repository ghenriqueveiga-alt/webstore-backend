SELECT b.id, b.programa_id FROM bloco b LEFT JOIN programa p ON b.programa_id = p.id WHERE p.id IS NULL;
