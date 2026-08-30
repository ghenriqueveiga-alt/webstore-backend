-- Set status_desc to 'Active' for all programs
UPDATE programa SET status_desc = 'Active' WHERE status_desc IS NULL OR status_desc != 'Active';

-- Classify cartoons (all others remain as Anime)
UPDATE programa SET tipo_desc = 'Cartoon' WHERE id IN (
  204,  -- As Meninas Super Poderosas
  215,  -- Coragem - O Cao Covarde
  237,  -- Hanna-Barbera
  241,  -- Johnny Bravo
  245,  -- Looney Tones
  262,  -- Papa Leguas
  264,  -- Pica Pau
  282,  -- Tiny Toons
  285   -- Tom e Jerry
);

-- All remaining are Anime
UPDATE programa SET tipo_desc = 'Anime' WHERE tipo_desc IS NULL;

-- Populate temporadas from episodio distinct season count
UPDATE programa p
SET p.temporadas = (
  SELECT COALESCE(COUNT(DISTINCT e.temporada), 1)
  FROM episodio e
  WHERE e.programa_id = p.id
    AND e.temporada IS NOT NULL
    AND e.temporada > 0
);

-- Programs with 0 seasons from episodio get 1 as minimum
UPDATE programa SET temporadas = 1 WHERE temporadas IS NULL OR temporadas = 0;
