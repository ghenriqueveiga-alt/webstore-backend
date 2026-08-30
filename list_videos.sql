SELECT id, caminho FROM arquivo WHERE caminho LIKE '%.mp4' OR caminho LIKE '%.mkv' OR caminho LIKE '%.avi' OR caminho LIKE '%.mov' OR caminho LIKE '%.wmv' ORDER BY caminho LIMIT 20;
