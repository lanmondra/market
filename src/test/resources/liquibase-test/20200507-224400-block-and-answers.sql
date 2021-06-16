--liquibase formatted sql



-- changeset albert:20200507-224400-1
-- Insert test blocks and answers
INSERT INTO `block` (`id`, `uuid`, `name`, `description`, `order_num`, `report_template_id`, `poll_id`, `last_update`, `deleted`, `last_action_user`)
VALUES
	(1,'1111','Bloc numero 1','La bonica descripció del bloc num 1',2, NULL,1,NULL,NULL,NULL),
	(2,'2222','Bloc numero 2','La bonica descripció del bloc num 2',1, NULL,1,NULL,NULL,NULL),
	(3,'3333','Bloc numero 3','La bonica descripció del bloc num 3',1,NULL,2,NULL,NULL,NULL),
	(4,'4444','Bloc numero 4','Bloc del report template 1         ',1,1,NULL,NULL,NULL,NULL),
	(5,'5555','Bloc numero 5','Bloc del report template 2',1,2,NULL,NULL,NULL,NULL),
	(6,'6666','Bloc numero 6','Block del report template 3',1,3,NULL,NULL,NULL,NULL);


-- Eliminar las preguntas creadas de produccion con las respuestas para bloques predefinidos (cuando han aparecido estas,
-- los test ya para las question ya estaban creados e interesa mantener el id)
DELETE FROM `question`;
ALTER TABLE `question` AUTO_INCREMENT = 1;


INSERT INTO `question` (`id`, `uuid`, `value`, `has_answer`, `mandatory`, `order_num`, `answer_type_id`,last_update, `deleted`, `last_action_user`, block_id, block_template_id, automatic_answer_name)
VALUES
	(1, 'AAA', 'Pregunta 1 del bloc 1',1,1,1,1,NULL,NULL,NULL, 1, NULL, NULL),
	(2, 'BBB', 'Pregunta 2 del bloc 1',1,1,2,2,NULL,NULL,NULL, 1, NULL, NULL),
	(3, 'CCC', 'Pregunta 3 del bloc 1',1,1,3,3,NULL,NULL,NULL, 1, NULL, NULL),
	(4, 'DDD', 'Pregunta 4 del bloc 1',1,1,4,4,NULL,NULL,NULL, 1, NULL, NULL),
	(5, 'EEE', 'Pregunta 5 del bloc 1',1,1,5,5,NULL,NULL,NULL, 1, NULL, NULL),
	(6, 'FFF', 'Pregunta 6 del bloc 1',1,1,6,6,NULL,NULL,NULL, 1, NULL, NULL),
	(7, 'GGG', 'Pregunta 1 del bloc 2',1,1,1,1,NULL,NULL,NULL, 2, NULL, NULL),
	(8, 'HHH', 'Pregunta 2 del bloc 2',1,1,2,2,NULL,NULL,NULL, 2, NULL, NULL),
	(9, 'III', 'Pregunta 3 del bloc 2',1,1,3,3,NULL,NULL,NULL, 2, NULL, NULL),
	(10, 'JJJ', 'Pregunta 4 del bloc 2',1,1,4,4,NULL,NULL,NULL, 2, NULL, NULL),
	(11, 'KKK', 'Pregunta 5 del bloc 2',1,1,5,5,NULL,NULL,NULL, 2, NULL, NULL),
	(12, 'LLL', 'Pregunta 6 del bloc 2',1,1,6,6,NULL,NULL,NULL, 2, NULL, NULL),
	(13, 'MMM', 'Pregunta 1 del bloc 3',1,1,1,1,NULL,NULL,NULL, 3, NULL, NULL),
	(14, 'NNN', 'Pregunta 2 del bloc 3',1,1,2,2,NULL,NULL,NULL, 3, NULL, NULL),
	(15, 'OOO', 'Pregunta 3 del bloc 3',1,1,3,3,NULL,NULL,NULL, 3, NULL, NULL),
	(16, 'PPP', 'Pregunta 4 del bloc 3',1,1,4,4,NULL,NULL,NULL, 3, NULL, NULL),
	(17, 'QQQ', 'Pregunta 5 del bloc 3',1,1,5,5,NULL,NULL,NULL, 3, NULL, NULL),
	(18, 'RRR', 'Pregunta 6 del bloc 3',1,1,6,6,NULL,NULL,NULL, 3, NULL, NULL),
	(19, 'SSS', 'Pregunta 1 del bloc 4',1,1,1,1,NULL,NULL,NULL, 4, NULL, NULL),
	(20, 'XXX', 'Pregunta 2 del bloc 4',1,1,2,2,NULL,NULL,NULL, 4, NULL, NULL),
	(21, 'YYY', 'Pregunta 3 del bloc 4',1,1,3,3,NULL,NULL,NULL, 4, NULL, NULL),
	(22, 'ZZZ', 'Pregunta 4 del bloc 4',1,1,4,4,NULL,NULL,NULL, 4, NULL, NULL),
	(23, '1AAA', 'Pregunta 5 del bloc 4',1,1,5,5,NULL,NULL,NULL, 4, NULL, NULL),
	(24, '1BBB', 'Pregunta 6 del bloc 4',1,1,6,6,NULL,NULL,NULL, 4, NULL, NULL),
	(25, '1CCC', 'Pregunta 1 del bloc 5',1,1,1,1,NULL,NULL,NULL, 5, NULL, NULL),
	(26, '1DDD', 'Pregunta 2 del bloc 5',1,1,2,2,NULL,NULL,NULL, 5, NULL, NULL),
	(27, '1EEE', 'Pregunta 3 del bloc 5',1,1,3,3,NULL,NULL,NULL, 5, NULL, NULL),
	(28, '1FFF', 'Pregunta 4 del bloc 5',1,1,4,4,NULL,NULL,NULL, 5, NULL, NULL),
	(29, '1GGG', 'Pregunta 5 del bloc 5',1,1,5,5,NULL,NULL,NULL, 5, NULL, NULL),
	(30, '1HHH', 'Pregunta 6 del bloc 5',1,1,6,6,NULL,NULL,NULL, 5, NULL, NULL),
	(31, '1III', 'Pregunta 1 del bloc 6',1,1,1,1,NULL,NULL,NULL, 6, NULL, NULL),
	(32, '1JJJ', 'Pregunta 2 del bloc 6',1,1,2,2,NULL,NULL,NULL, 6, NULL, NULL),
	(33, '1KKK', 'Pregunta 3 del bloc 6',1,1,3,3,NULL,NULL,NULL, 6, NULL, NULL),
	(34, '1LLL', 'Pregunta 4 del bloc 6',1,1,4,4,NULL,NULL,NULL, 6, NULL, NULL),
	(35, '1MMM', 'Pregunta 5 del bloc 6',1,1,5,5,NULL,NULL,NULL, 6, NULL, NULL),
	(36, '1NNN', 'Pregunta 6 del bloc 6',1,1,6,6,NULL,NULL,NULL, 6, NULL, NULL);


-- Inserta las preguntas predefinidas
INSERT INTO `question`
(`uuid`, `value`,               `has_answer`, `mandatory`, `order_num`, `answer_type_id`,last_update,        `deleted`, `last_action_user`, block_id, block_template_id, automatic_answer_name) VALUES
(uuid(), 'Numero de colegiat',   true,         false,       1,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'refereeNumber'),
(uuid(), 'Nom del colegiat',     true,         false,       2,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'refereeName'),
(uuid(), 'Categoria àrbitre',    true,         false,       3,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'refereeCategory'),
(uuid(), 'Numero de partit',     true,         false,       4,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchNumber'),
(uuid(), 'Equip Local',          true,         false,       5,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'localTeam'),
(uuid(), 'Equip Visitant',       true,         false,       6,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'visitorTeam'),
(uuid(), 'Categoria',            true,         false,       7,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchCategory'),
(uuid(), 'Génere',               true,         false,       8,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchGender'),
(uuid(), 'Tipus',                true,         false,       9,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchGroup'),
(uuid(), 'Dia',                  true,         false,       10,          6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchDay'),
(uuid(), 'Hora',                 true,         false,       11,          6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchTime'),
(uuid(), 'Localitat',            true,         false,       12,          6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchTown'),
(uuid(), 'Punts Local',          true,         false,       13,          6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'localPoints'),
(uuid(), 'Punts Visitant',       true,         false,       14,          6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'visitorPoints');






INSERT INTO `answer_option` (`id`, `uuid`, `value`, `color`, `description`, `order_num`, `last_update`, `deleted`, `last_action_user`, question_id)
VALUES
	(1, 'AAA', 'BL1 P1 Text opcio 0',NULL,NULL,1,NULL,NULL,NULL, 1),
	(2, 'BBB', 'BL1 P1 Text opcio 1',NULL,NULL,2,NULL,NULL,NULL, 1),
	(3, 'CCC', 'BL1 P2 Text opcio 0',NULL,NULL,1,NULL,NULL,NULL, 2),
	(4, 'DDD', 'BL1 P2 Text opcio 1',NULL,NULL,2,NULL,NULL,NULL, 2),
	(5, 'EEE', 'BL1 P2 Text opcio 2',NULL,NULL,3,NULL,NULL,NULL, 2),
	(6, 'FFF', 'BL1 P3 Text opcio 0',NULL,NULL,1,NULL,NULL,NULL, 3),
	(7, 'GGG', 'BL1 P3 Text opcio 1',NULL,NULL,2,NULL,NULL,NULL, 3),
	(8, 'HHH', 'BL1 P3 Text opcio 2',NULL,NULL,3,NULL,NULL,NULL, 3),
	(9, 'III', '1',NULL,NULL,1,NULL,NULL,NULL, 4),
	(10, 'JJJ', '10',NULL,NULL,2,NULL,NULL,NULL, 4),
	(11, 'KKK', 'BL1 P5 Text opcio 0','F91205','Descripcion de la opció 0',1,NULL,NULL,NULL, 5),
	(12, 'LLL', 'BL1 P5 Text opcio 1','19F905','Descripcion de la opció 1',2,NULL,NULL,NULL,5),
	(13, 'MMM', 'BL1 P5 Text opcio 2','F9F505','Descripcion de la opció 2',3,NULL,NULL,NULL,5),
	(14, 'NNN', 'BL1 P5 Text opcio 3','052EF9','Descripcion de la opció 3',4,NULL,NULL,NULL,5),
	(15, 'OOO', 'BL2 P1 Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,7),
	(16, 'PPP', 'BL2 P1 Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,7),
	(17, 'QQQ', 'BL2 P2 Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,8),
	(18, 'RRR', 'BL2 P2 Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,8),
	(19, 'SSS', 'BL2 P2 Text opcio 2',NULL,NULL,3,NULL,NULL,NULL,8),
	(20, 'XXX', 'BL2 P3 Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,9),
	(21, 'YYY', 'BL2 P3 Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,9),
	(22, 'ZZZ', 'BL2 P3 Text opcio 2',NULL,NULL,3,NULL,NULL,NULL,9),
	(23, '1AAA', '1',NULL,NULL,1,NULL,NULL,NULL,10),
	(24, '1BBB', '10',NULL,NULL,2,NULL,NULL,NULL,10),
	(25, '1CCC', 'BL2 P5 Text opcio 0','F91205','Descripcion de la opció 0',1,NULL,NULL,NULL,11),
	(26, '1DDD', 'BL2 P5 Text opcio 1','19F905','Descripcion de la opció 1',2,NULL,NULL,NULL,11),
	(27, '1EEE', 'BL2 P5 Text opcio 2','F9F505','Descripcion de la opció 2',3,NULL,NULL,NULL,11),
	(28, '1FFF', 'BL2 P5 Text opcio 3','052EF9','Descripcion de la opció 3',4,NULL,NULL,NULL,11),
	(29, '1GGG', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,13),
	(30, '1HHH', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,13),
	(31, '1III', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,14),
	(32, '1JJJ', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,14),
	(33, '1KKK', 'Text opcio 2',NULL,NULL,3,NULL,NULL,NULL,14),
	(34, '1LLL', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,15),
	(35, '1MMM', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,15),
	(36, '1NNN', 'Text opcio 2',NULL,NULL,3,NULL,NULL,NULL,15),
	(37, '1OOO', '1',NULL,NULL,1,NULL,NULL,NULL,16),
	(38, '1PPP', '10',NULL,NULL,2,NULL,NULL,NULL,16),
	(39, '1QQQ', 'Text opcio 0','F91205','Descripcion de la opció 0',1,NULL,NULL,NULL,17),
	(40, '1RRR', 'Text opcio 1','19F905','Descripcion de la opció 1',2,NULL,NULL,NULL,17),
	(41, '1SSS', 'Text opcio 2','F9F505','Descripcion de la opció 2',3,NULL,NULL,NULL,17),
	(42, '1XXX', 'Text opcio 3','052EF9','Descripcion de la opció 3',4,NULL,NULL,NULL,17),
	(43, '1YYY', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,19),
	(44, '1ZZZ', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,19),
	(45, '2AAA', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,20),
	(46, '2BBB', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,20),
	(47, '2CCC', 'Text opcio 2',NULL,NULL,3,NULL,NULL,NULL,20),
	(48, '2DDD', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,21),
	(49, '2EEE', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,21),
	(50, '2FFF', 'Text opcio 2',NULL,NULL,3,NULL,NULL,NULL,21),
	(51, '2GGG', '1',NULL,NULL,1,NULL,NULL,NULL,22),
	(52, '2HHH', '10',NULL,NULL,2,NULL,NULL,NULL,22),
	(53, '2III', 'Text opcio 0','F91205','Descripcion de la opció 0',1,NULL,NULL,NULL,23),
	(54, '2JJJ', 'Text opcio 1','19F905','Descripcion de la opció 1',2,NULL,NULL,NULL,23),
	(55, '2KKK', 'Text opcio 2','F9F505','Descripcion de la opció 2',3,NULL,NULL,NULL,23),
	(56, '2LLL', 'Text opcio 3','052EF9','Descripcion de la opció 3',4,NULL,NULL,NULL,23),
	(57, '2MMM', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,25),
	(58, '2NNN', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,25),
	(59, '2OOO', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,26),
	(60, '2PPP', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,26),
	(61, '2QQQ', 'Text opcio 2',NULL,NULL,3,NULL,NULL,NULL,26),
	(62, '2RRR', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,27),
	(63, '2SSS', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,27),
	(64, '2UUU', 'Text opcio 2',NULL,NULL,3,NULL,NULL,NULL,27),
	(65, '2VVV', '1',NULL,NULL,1,NULL,NULL,NULL,28),
	(66, '2WWW', '10',NULL,NULL,1,NULL,NULL,NULL,28),
	(67, '2XXX', 'Text opcio 0','F91205','Descripcion de la opció 0',1,NULL,NULL,NULL,29),
	(68, '2YYY', 'Text opcio 1','19F905','Descripcion de la opció 1',2,NULL,NULL,NULL,29),
	(69, '2ZZZ', 'Text opcio 2','F9F505','Descripcion de la opció 2',3,NULL,NULL,NULL,29),
	(70, '3AAA', 'Text opcio 3','052EF9','Descripcion de la opció 3',4,NULL,NULL,NULL,29),
	(71, '3BBB', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,31),
	(72, '3CCC', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,31),
	(73, '3DDD', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,32),
	(74, '3EEE', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,32),
	(75, '3FFF', 'Text opcio 2',NULL,NULL,3,NULL,NULL,NULL,32),
	(76, '3GGG', 'Text opcio 0',NULL,NULL,1,NULL,NULL,NULL,33),
	(77, '3HHH', 'Text opcio 1',NULL,NULL,2,NULL,NULL,NULL,33),
	(78, '3III', 'Text opcio 2',NULL,NULL,3,NULL,NULL,NULL,33),
	(79, '3JJJ', '1',NULL,NULL,1,NULL,NULL,NULL,34),
	(80, '3KKK', '10',NULL,NULL,2,NULL,NULL,NULL,34),
	(81, '3LLL', 'Text opcio 0','F91205','Descripcion de la opció 0',1,NULL,NULL,NULL,35),
	(82, '3MMM', 'Text opcio 1','19F905','Descripcion de la opció 1',2,NULL,NULL,NULL,35),
	(83, '3NNN', 'Text opcio 2','F9F505','Descripcion de la opció 2',3,NULL,NULL,NULL,35),
	(84, '3OOO', 'Text opcio 3','052EF9','Descripcion de la opció 3',4,NULL,NULL,NULL,35);


