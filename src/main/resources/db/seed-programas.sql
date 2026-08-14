-- Seed dos animes (programa) do diretório F:
-- lancamento/encerramento/em_producao extraidos do info.json
USE mysql-db;

INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('5c9a82dc-5bdb-490d-9fdd-c6d2b33836ce', 'Active', 'Another - Legendado', 0, '2012-01-09T00:00', '2012-03-26T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('ac860b2c-902b-427f-81f2-4a74124301d2', 'Active', 'As Aventuras de Jackie Chan', 5, '2000-10-09T00:00', '2005-07-08T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('47e87ca1-624d-4ece-a154-d6f8b38bcaf9', 'Active', 'As Meninas Super Poderosas - Incompleto', 0, 'NULL', 'NULL', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('d4d97356-2124-4714-b593-ddbf51e3a60b', 'Active', 'Attack on Titans - Legendado', 8, '2013-04-07T00:00', '2023-11-05T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('1325eef1-3a9b-4c9b-9f5e-26bf563e78df', 'Active', 'Avatar', 2, '2005-02-21T00:00', '2015-06-13T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('e9af2140-33de-4455-80b8-c24c46fd56bd', 'Active', 'Baki', 2, '2018-07-25T00:00', '2023-08-24T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('36dd346c-3dd0-439b-948c-21b01f9b4ec8', 'Active', 'Berserk - Legendado', 0, '1997-11-08T00:00', '1998-04-01T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('64e7de0b-3acc-429b-9249-3010e6bafabe', 'Active', 'Bleach', 16, '2004-11-05T00:00', '2012-03-27T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('87699d29-b768-405f-8b8c-f66bbe21d18e', 'Active', 'Buko no Hero', 9, '2016-04-03T00:00', 'NULL', true, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('dd7bb816-dc3f-4d73-87b9-23304e33ab5e', 'Active', 'Chainsaw Man', 2, '2022-11-12T00:00', 'NULL', true, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('c2500e4c-7591-42e3-bb03-6f810b649653', 'Active', 'Code Geass - Legendado', 2, '2006-11-06T00:00', '2008-10-28T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('a029ba0d-ecb5-42b8-b1ec-6b5e2cee8e9c', 'Active', 'Coragem - O Cão Covarde - Incompleto', 4, 'NULL', 'NULL', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('b06bc923-c34c-495c-9dfe-584bc7091f51', 'Active', 'Cowboy Bebop', 0, '1998-04-02T00:00', '1999-04-23T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('cd33967c-4fb3-46ed-82dc-ced343870f92', 'Active', 'Dan da dan', 2, '2024-10-02T00:00', '2024-12-18T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('98ffe76c-3110-40ad-98c8-0af0f0fb7067', 'Active', 'Death Note', 0, '2006-10-04T00:00', '2007-06-27T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('44c59927-e8d5-4e3a-9058-e49e6e71ed38', 'Active', 'Demon Slayer - Legendado', 5, '2019-04-06T00:00', 'NULL', true, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('d6b9ade2-c1f7-409f-8072-be6865cf224c', 'Active', 'Digimon - Incompleto', 7, '1999-03-04T00:00', '2023-03-26T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('fd2649e5-41f7-435a-8265-b9b0ffe00ae4', 'Active', 'Dr. Stone', 3, '2019-07-05T00:00', '2023-12-21T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('e0f0cb58-4c43-4877-9659-6a8f32750d68', 'Active', 'Dragon Ball', 6, '1986-02-26T00:00', 'NULL', true, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('a3bdaa2d-bf32-44b8-a269-4cd4cee9c453', 'Active', 'Full Metal Alchemist - Brotherhood', 0, '2009-04-05T00:00', '2010-07-04T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('1e462987-43c3-41b5-a5ef-68272393b7c9', 'Active', 'Gantz', 2, '2004-04-12T00:00', '2004-11-18T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('459a05ec-141a-4f9e-8403-dbb9cff1cf96', 'Active', 'Goblin Slayer', 2, '2018-10-05T00:00', '2023-12-21T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('ea396e30-e1db-44d7-ba42-c7e4e6b9df4c', 'Active', 'Hanna-Barbera - Incompleto', 0, 'NULL', 'NULL', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('22e2f35f-fdd8-4242-8987-c8fb19c9f219', 'Active', 'Hellsing', 2, '2001-10-10T00:00', '2012-12-26T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('d91b2931-f2a1-4d98-9c02-a4dbc81a46c7', 'Active', 'Hunter x Hunter', 0, '2011-10-02T00:00', '2014-09-24T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('9918418e-e573-427d-8fb9-463bd0ea7aef', 'Active', 'Inuyasha', 0, '1996-11-13T00:00', '2008-06-18T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('b7d24980-f485-472c-87bc-888a41cb56c1', 'Active', 'Johnny Bravo - Incompleto', 4, '1995-03-26T00:00', '2004-02-14T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('82f39289-b07e-4ac3-a53c-8556793fdaf7', 'Active', 'JoJo''s Bizarre Adventure', 5, '2012-12-07T00:00', '2022-12-01T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('74ee6d11-139b-4348-8884-5b94c2c1bf34', 'Active', 'Jujutsu Kaisen', 2, '2020-10-03T00:00', '2023-12-28T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('00565736-64af-42d4-b2f0-92d512a9c699', 'Active', 'Konosuba', 2, '2016-01-13T00:00', '2017-03-15T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('17953a02-395f-4efa-bd92-ba2a273e5334', 'Active', 'Looney Tones - Incompleto', 0, 'NULL', 'NULL', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('0d9eb636-b5b9-45e2-8c2b-765d604b77fb', 'Active', 'Medabots', 2, '1999-07-02T00:00', '2001-03-30T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('a9366fa3-0f23-4103-8f90-89828084caf2', 'Active', 'Mob Psycho 100', 3, '2018-10-27T00:00', '2022-12-22T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('989d2291-57f7-46cd-a46b-96cafde78048', 'Active', 'Nanatsu no Taizai', 4, '2014-10-05T00:00', '2021-06-23T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('f8a3bab0-3fbc-4b45-b560-5d9161c69f07', 'Active', 'Naruto', 9, '2002-10-03T00:00', '2007-02-08T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('1e4f70f9-6fd0-4ec7-8126-a582b0d75d29', 'Active', 'Neon Genesis Evangelion', 0, '1995-10-04T00:00', '1996-03-27T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('ca8f92b5-9bf4-41af-b07c-b196875847a6', 'Active', 'One Piece - Incompleto', 0, 'NULL', 'NULL', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('43c0bb21-f58a-4a5b-b0db-7a185d0dbf9e', 'Active', 'One Punch Man', 3, '2015-10-04T00:00', '2019-07-02T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('0e924d00-aa7f-4a6e-b857-aa13309ded4d', 'Active', 'Os Cavaleiros do Zodíaco', 7, '1986-10-11T00:00', '2011-07-20T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('4592f543-4cf8-4b7d-ae82-ebe3f62506d9', 'Active', 'Overlord', 4, '2015-07-07T00:00', '2022-09-27T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('e1e7fd3f-b472-4829-bf3b-8b36f4fdd9bf', 'Active', 'Papa Léguas - Incompleto', 2, 'NULL', 'NULL', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('58311380-f66f-4ee2-a7db-d17fed63a7d0', 'Active', 'Parasyte', 0, '2014-10-08T00:00', '2015-03-25T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('c2fb6387-5753-49ff-ac32-0946b554fd6a', 'Active', 'Pica Pau - Incompleto', 0, 'NULL', 'NULL', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('3e9d53e2-78bc-48be-8c2b-aedd1e932d82', 'Active', 'Pokémon - Incompleto', 5, 'NULL', 'NULL', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('7a970ee6-2b44-4a99-bdcc-7d3a04ae061e', 'Active', 'Re Zero', 2, '2016-04-04T00:00', '2021-03-24T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('4030f4a5-48fd-42cb-a1eb-3b7d6d2fc628', 'Active', 'Record of Ragnarok - Incompleto', 3, '2021-06-17T00:00', 'NULL', true, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('80fc16fc-f74e-4a3f-8710-982ae6577fa7', 'Active', 'Sailor Moon', 5, '1996-04-29T00:00', '2002-04-23T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('3566a57f-eaf6-49af-8eef-631df9aeac19', 'Active', 'Sakura Card Captor', 3, '1998-04-07T00:00', '2000-03-21T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('2f3de563-ebdf-4e1d-8b4c-11ce2414c731', 'Active', 'Samurai Jack', 5, '2001-08-10T00:00', '2017-05-20T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('ae001d90-8541-4dbb-a8bc-f067f402a40a', 'Active', 'Samurai Warriors', 0, '1988-04-30T00:00', '1989-03-04T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('fd0e3461-a5c7-467f-b585-145b86a56cd0', 'Active', 'Samurai X', 3, '1996-01-10T00:00', '1998-09-08T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('19a435a7-5b13-405c-aab4-6e529cb2aea4', 'Active', 'Serial Experiments Lain', 0, '1998-07-06T00:00', '1998-09-28T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('9f5023f3-d015-4694-a60a-54a2520aa0c7', 'Active', 'Shurato', 0, '1989-04-06T00:00', '1990-01-25T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('d43c09de-8601-408a-b662-cf621bfdfe4c', 'Active', 'Spy X Family', 2, '2022-04-08T00:00', '2023-12-22T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('3e79b2b2-87c4-4a31-9204-aac2cc5d4e9b', 'Active', 'Steins;Gate', 2, '2011-04-06T00:00', '2018-09-27T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('db4e16d0-e5ba-4f3e-a7c0-56256f5473ed', 'Active', 'Street Fighter II - Victory', 0, '1995-04-10T00:00', '1995-11-20T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('017abc22-f955-4332-abbf-9ccb04a48b70', 'Active', 'Sword Art Online', 3, '2012-07-07T00:00', '2020-09-19T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('62ef6ce3-86e0-4afe-8f81-e3db662c4104', 'Active', 'The Promised Neverland - Legendado', 2, '2019-04-13T00:00', '2022-02-28T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('8fe21077-70da-4279-852d-cbd60ff65fbf', 'Active', 'Tiny Toons - Incompleto', 4, 'NULL', 'NULL', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('14617096-ddc7-4fb4-8251-6437371b6c45', 'Active', 'Tokyo Ghoul', 4, '2020-11-23T00:00', '2018-12-24T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('a648c7c2-f290-4787-8324-a3343ef901de', 'Active', 'Tokyo Revengers', 3, '2021-04-11T00:00', '2023-12-27T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('7485ad94-89bc-4ccd-aed0-634d84133ec3', 'Active', 'Tom e Jerry - Incompleto', 0, 'NULL', 'NULL', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('ac9d3cf7-a1a4-4bb1-aa7e-3f5bb0b6a2e7', 'Active', 'Trigun', 0, '1998-04-01T00:00', '1998-09-30T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('3577d5cd-c861-4e84-8293-3b77a2918cbd', 'Active', 'Undead Unluck - Legendado', 0, 'NULL', 'NULL', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('de3efd5e-c547-431e-a1d8-97c943c63517', 'Active', 'Violet Evergarden - Legendado', 0, '2018-01-11T00:00', '2018-04-05T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('a0c79682-5d7e-4d6b-a7d0-4fb27f14e14c', 'Active', 'Yu Yu Hakusho', 4, '1992-10-10T00:00', '1994-12-17T00:00', false, 'AN');
INSERT INTO programa (uuid, status_desc, nome, temporadas, lancamento, encerramento, em_producao, tipo_code)
VALUES ('42e79c20-04e6-48d1-9317-199190e1357a', 'Active', 'Yu-Gi-Oh! - Incompleto', 5, '2000-04-18T00:00', '2004-09-29T00:00', false, 'AN');
