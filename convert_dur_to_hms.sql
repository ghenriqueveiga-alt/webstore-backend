UPDATE arquivo SET duracao = SEC_TO_TIME(CAST(duracao AS UNSIGNED)) WHERE duracao REGEXP '^[0-9]+(\.[0-9]+)?$';
