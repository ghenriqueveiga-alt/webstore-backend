SELECT id, nome, tamanho, ROUND(tamanho / 1048576, 2) AS tamanho_mb FROM arquivo LIMIT 10;
