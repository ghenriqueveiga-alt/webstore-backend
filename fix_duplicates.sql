DELETE FROM programa WHERE nome IN ('Dragon Quest', 'Vinland Saga') AND id NOT IN (
    SELECT MIN(id) FROM programa WHERE nome IN ('Dragon Quest', 'Vinland Saga') GROUP BY nome
);
