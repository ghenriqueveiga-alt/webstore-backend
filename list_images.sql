SELECT id, caminho FROM arquivo WHERE caminho LIKE '%_imagem%' AND (caminho LIKE '%.jpg' OR caminho LIKE '%.png' OR caminho LIKE '%.jpeg') ORDER BY caminho LIMIT 15;
