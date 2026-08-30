CREATE TABLE bloco_new LIKE bloco;
ALTER TABLE bloco_new DROP INDEX UK3kfigl2h1p4ckewnpajmmjrek;
INSERT INTO bloco_new SELECT * FROM bloco;
DROP TABLE bloco;
ALTER TABLE bloco_new RENAME TO bloco;
