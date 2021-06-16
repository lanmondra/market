INSERT INTO report_category
(id, uuid,     name,         federation, last_update, deleted, last_action_user) VALUES
(1,   'AAAA', 'CATEGORIA 1', 'fcbq',     null,    null,     null),
(2,   'BBBB', 'CATEGORIA 2', 'fcbq',     null,    null,     null);


INSERT INTO report_template
(id, uuid,   name,                            federation, status, created,             is_visible, show_referee, portal,    allow_not_designated, is_match_report, match_report_type_id, informer_type_id, subject_type_id, report_category_id, last_update, deleted, last_action_user) VALUES
(1, 'AAAA', 'PLANTILLA INFORME 1',            'fcbq',      0,     current_timestamp(), true,       true,         0,         false,                true,            1,                    1,                1,                1,                 null,   null,    null),
(2, 'BBBB', 'PLANTILLA INFORME 2',            'fcbq',      0,     current_timestamp(), true,       true,         0,         false,                true,            2,                    1,                1,                1,                 null,   null,    null),
(3, 'CCCC', 'PLANTILLA INFORME 3',            'fcbq',      0,     current_timestamp(), false,      true,         0,         false,                false,           null,                 1,                1,                2,                 null,   null,    null),
(4, 'DDDD', 'PLANTILLA INFORME 4',            'fcbq',      0,     current_timestamp(), true,       true,         0,         false,                false,           null,                 1,                1,                2,                 null,   null,    null);


INSERT INTO campaign
(id, uuid,   name,         start_date,   end_date,     report_template_id, last_update, deleted, last_action_user) VALUES
(1,  '1111', 'CAMPANYA 1', '2020-01-01', '2021-12-31',  1,                  null,    null,    null),
(2,  '2222', 'CAMPANYA 2', '2020-01-01', '2021-12-31',  2,                  null,    null,    null);



INSERT INTO permission
(federation,  name,                 can_edit, report_category_id) VALUES
('fcbq',      'REPORTS_CAT_EDIT_1', true,     1),
('fcbq',      'REPORTS_CAT_READ_1', false,    1),
('fcbq',      'REPORTS_CAT_EDIT_2', true,     2),
('fcbq',      'REPORTS_CAT_READ_2', false,    2);





INSERT INTO poll
(id, uuid,   name,                   federation, status, activation_time,       ending_time,           anonymous, description,                                  login_required, change_answer_allowed, informer_type_id, informer_categories,     last_update, deleted, last_action_user) VALUES
(1,  '1111', 'TEST POLL 1',          'fcbq',     1,      '2020-05-01 12:00:00', '2020-12-31 23:59:59', false,     'Test poll 1 description (clubs)',            true,           true,                  2,                'CAT1',                  null,    null,    null),
(2,  '2222', 'TEST POLL 2',          'fcbq',     0,      '2020-05-01 12:00:00', '2020-12-31 23:59:59', false,     'Test poll 2 description (clubs)',            true,           true,                  2,                'CAT1',                  null,    null,    null),
(3,  '3333', 'TEST POLL 3',          'fcbq',     1,      '2020-05-01 12:00:00', '2020-12-31 23:59:59', false,     'Test poll 3 description (arbitres)',         true,           true,                  1,                'CAT1',                  null,    null,    null),
(4,  '4444', 'TEST POLL 4 (ended)',  'fcbq',     1,      '2020-05-01 12:00:00', '2020-05-02 23:59:59', false,     'Test poll 4 description (clubs i caducada)', true,           true,                  2,                'CAT1',                  null,    null,    null);


-- No añadir respuestas al form 1, se usa en los fcbq para pruebas de alta i borrado de respuestas
-- Form 5 tiene todas las respuestas entradas
-- No crear mas forms para report template 1. Se usa para verificar edición de bloque y preguntas
INSERT INTO form
(id, uuid,   subject_uuid,                                    informer_uuid,                          status, fill_out_date, visualized, report_template_id, poll_id) VALUES
(1,  '1111', null,                                            '508952c9-8fa1-11ea-b836-02b7c2952a14', 1,      null,           false,      null,               1 ),
(2,  '2222', null,                                            '508952c9-8fa1-11ea-b836-02b7c2952a14', 1,      null,           false,      null,               2 ),
(3,  '3333', null,                                            '508952c9-8fa1-11ea-b836-02b7c2952a14', 0,      null,           false,      null,               3 ),
(4,  '4444', null,                                            '508952c9-8fa1-11ea-b836-02b7c2952a14', 0,      null,           false,      null,               4 ),
(5,  '5555', null,                                            '508ad6df-8fa1-11ea-b836-02b7c2952a14', 3,      null,           false,      null,               1 ),
(6,  '6666', null,                                            '508ad6df-8fa1-11ea-b836-02b7c2952a14', 0,      null,           false,      null,               2 ),
(7,  '7777', 'a44e083e-5d77-4ac6-81d2-9c63fc1fce18',          '508952c9-8fa1-11ea-b836-02b7c2952a14', 0,      null,           false,      1,              null ),
(8,  '8888', 'a44e083e-5d77-4ac6-81d2-9c63fc1fce18',          '508ad6df-8fa1-11ea-b836-02b7c2952a14', 0,      null,           false,      2,              null ),
(9,  '9999', 'a44e083e-5d77-4ac6-81d2-9c63fc1fce18',          '508ad6df-8fa1-11ea-b836-02b7c2952a14', 0,      null,           false,      2,              null ),
(10, '101010', 'a44e083e-5d77-4ac6-81d2-9c63fc1fce18',        '508ad6df-8fa1-11ea-b836-02b7c2952a14', 0,      null,           false,      2,              null );






INSERT INTO `block` (`id`, `uuid`, `name`, `description`, `order_num`, `report_template_id`, `poll_id`, `last_update`, `deleted`, `last_action_user`)
VALUES
	(1,'1111','Bloc numero 1','La bonica descripció del bloc num 1',2, NULL,1,NULL,NULL,NULL),
	(2,'2222','Bloc numero 2','La bonica descripció del bloc num 2',1, NULL,1,NULL,NULL,NULL),
	(3,'3333','Bloc numero 3','La bonica descripció del bloc num 3',1,NULL,2,NULL,NULL,NULL),
	(4,'4444','Bloc numero 4','Bloc del report template 1         ',1,1,NULL,NULL,NULL,NULL),
	(5,'5555','Bloc numero 5','Bloc del report template 2',1,2,NULL,NULL,NULL,NULL),
	(6,'6666','Bloc numero 6','Block del report template 3',1,3,NULL,NULL,NULL,NULL);


-- Eliminar las preguntas creadas de produccion con las respuestas para bloques predefinidos
DELETE FROM `question`;
ALTER TABLE `question` AUTO_INCREMENT = 1;


INSERT INTO `question` (`id`, `uuid`, `value`, `has_answer`, `mandatory`, `order_num`, `answer_type_id`,last_update, `deleted`, `last_action_user`, block_id)
VALUES
	(1, 'AAA', 'Pregunta 1 del bloc 1',1,1,1,1,NULL,NULL,NULL, 1),
	(2, 'BBB', 'Pregunta 2 del bloc 1',1,1,2,2,NULL,NULL,NULL, 1),
	(3, 'CCC', 'Pregunta 3 del bloc 1',1,1,3,3,NULL,NULL,NULL, 1),
	(4, 'DDD', 'Pregunta 4 del bloc 1',1,1,4,4,NULL,NULL,NULL, 1),
	(5, 'EEE', 'Pregunta 5 del bloc 1',1,1,5,5,NULL,NULL,NULL, 1),
	(6, 'FFF', 'Pregunta 6 del bloc 1',1,1,6,6,NULL,NULL,NULL, 1),
	(7, 'GGG', 'Pregunta 1 del bloc 2',1,1,1,1,NULL,NULL,NULL, 2),
	(8, 'HHH', 'Pregunta 2 del bloc 2',1,1,2,2,NULL,NULL,NULL, 2),
	(9, 'III', 'Pregunta 3 del bloc 2',1,1,3,3,NULL,NULL,NULL, 2),
	(10, 'JJJ', 'Pregunta 4 del bloc 2',1,1,4,4,NULL,NULL,NULL, 2),
	(11, 'KKK', 'Pregunta 5 del bloc 2',1,1,5,5,NULL,NULL,NULL, 2),
	(12, 'LLL', 'Pregunta 6 del bloc 2',1,1,6,6,NULL,NULL,NULL, 2),
	(13, 'MMM', 'Pregunta 1 del bloc 3',1,1,1,1,NULL,NULL,NULL, 3),
	(14, 'NNN', 'Pregunta 2 del bloc 3',1,1,2,2,NULL,NULL,NULL, 3),
	(15, 'OOO', 'Pregunta 3 del bloc 3',1,1,3,3,NULL,NULL,NULL, 3),
	(16, 'PPP', 'Pregunta 4 del bloc 3',1,1,4,4,NULL,NULL,NULL, 3),
	(17, 'QQQ', 'Pregunta 5 del bloc 3',1,1,5,5,NULL,NULL,NULL, 3),
	(18, 'RRR', 'Pregunta 6 del bloc 3',1,1,6,6,NULL,NULL,NULL, 3),
	(19, 'SSS', 'Pregunta 1 del bloc 4',1,1,1,1,NULL,NULL,NULL, 4),
	(20, 'XXX', 'Pregunta 2 del bloc 4',1,1,2,2,NULL,NULL,NULL, 4),
	(21, 'YYY', 'Pregunta 3 del bloc 4',1,1,3,3,NULL,NULL,NULL, 4),
	(22, 'ZZZ', 'Pregunta 4 del bloc 4',1,1,4,4,NULL,NULL,NULL, 4),
	(23, '1AAA', 'Pregunta 5 del bloc 4',1,1,5,5,NULL,NULL,NULL, 4),
	(24, '1BBB', 'Pregunta 6 del bloc 4',1,1,6,6,NULL,NULL,NULL, 4),
	(25, '1CCC', 'Pregunta 1 del bloc 5',1,1,1,1,NULL,NULL,NULL, 5),
	(26, '1DDD', 'Pregunta 2 del bloc 5',1,1,2,2,NULL,NULL,NULL, 5),
	(27, '1EEE', 'Pregunta 3 del bloc 5',1,1,3,3,NULL,NULL,NULL, 5),
	(28, '1FFF', 'Pregunta 4 del bloc 5',1,1,4,4,NULL,NULL,NULL, 5),
	(29, '1GGG', 'Pregunta 5 del bloc 5',1,1,5,5,NULL,NULL,NULL, 5),
	(30, '1HHH', 'Pregunta 6 del bloc 5',1,1,6,6,NULL,NULL,NULL, 5),
	(31, '1III', 'Pregunta 1 del bloc 6',1,1,1,1,NULL,NULL,NULL, 6),
	(32, '1JJJ', 'Pregunta 2 del bloc 6',1,1,2,2,NULL,NULL,NULL, 6),
	(33, '1KKK', 'Pregunta 3 del bloc 6',1,1,3,3,NULL,NULL,NULL, 6),
	(34, '1LLL', 'Pregunta 4 del bloc 6',1,1,4,4,NULL,NULL,NULL, 6),
	(35, '1MMM', 'Pregunta 5 del bloc 6',1,1,5,5,NULL,NULL,NULL, 6),
	(36, '1NNN', 'Pregunta 6 del bloc 6',1,1,6,6,NULL,NULL,NULL, 6);

-- Inserta las preguntas predefinidas
INSERT INTO `question`
(`uuid`, `value`,               `has_answer`, `mandatory`, `order_num`, `answer_type_id`,last_update,        `deleted`, `last_action_user`, block_id, block_template_id, automatic_answer_name) VALUES
(uuid(), 'Número de col·legiat', true,         false,       1,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'refereeNumber'),
(uuid(), 'Nom del col·legiat',   true,         false,       2,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'refereeName'),
(uuid(), 'Nom segon col·legiat', true,         false,       3,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'secondRefereerName'),
(uuid(), 'Nom informador',       true,         false,       4,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'informerName'),
(uuid(), 'Categoria àrbitre',    true,         false,       5,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'refereeCategory'),
(uuid(), 'Número de partit',     true,         false,       6,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchNumber'),
(uuid(), 'Categoria',            true,         false,       7,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchCategory'),
(uuid(), 'Competició',           true,         false,       8,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchCompetition'),
(uuid(), 'Equip Local',          true,         false,       9,           6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'localTeam'),
(uuid(), 'Equip Visitant',       true,         false,       10,          6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'visitorTeam'),
(uuid(), 'Dia',                  true,         false,       11,          6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchDay'),
(uuid(), 'Hora',                 true,         false,       12,          6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchTime'),
(uuid(), 'Localitat',            true,         false,       13,          6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'matchTown'),
(uuid(), 'Punts Local',          true,         false,       14,          6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'localPoints'),
(uuid(), 'Punts Visitant',       true,         false,       15,          6,              current_timestamp(), NULL,       NULL,             NULL,      1, 'visitorPoints');



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



INSERT INTO answer
(id, uuid,   value,              form_id, question_id, answer_time) VALUES
(1, '1111',  '',                 5,       1,           current_timestamp()),
(2, '2222',  '',                 5,       2,           current_timestamp()),
(3, '3333',  '',                 5,       3,           current_timestamp()),
(4, '4444',  '6',                5,       4,           current_timestamp()),
(5, '5555',  '',                 5,       5,           current_timestamp()),
(6, '6666',  'PROVA OPEN Form 5',5,       6,           current_timestamp()),
(7, '7777',  '',                 5,       7,           current_timestamp()),
(8, '8888',  '',                 5,       8,           current_timestamp()),
(9, '9999',  '',                 5,       9,           current_timestamp()),
(10, '101010',  '2',             5,       10,          current_timestamp()),
(11, '111111',  '',              5,       11,          current_timestamp()),
(12, '121212',  'PROVA OPEN 2 form 5',5,       12,          current_timestamp());


INSERT INTO selected_answer_option
(answer_id, answer_option_id) VALUES
(1, 2),
(2, 4),
(3, 7),
(3, 8),
(5, 12),
(7, 15),
(8, 18),
(9, 20),
(9, 22),
(11, 26);


INSERT INTO answer
(id, uuid,      value,              form_id, question_id, answer_time) VALUES
(13, '131313',  '',                 1,       1,           current_timestamp()),
(14, '141414',  '',                 1,       2,           current_timestamp()),
(15, '151515',  '',                 1,       3,           current_timestamp()),
(16, '161616',  '5',                1,       4,           current_timestamp()),
(17, '171717',  '',                 1,       5,           current_timestamp()),
(18, '181818',  'PROVA OPEN form 1',1,       6,           current_timestamp()),
(19, '191919',  '',                 1,       7,           current_timestamp()),
(20, '202020',  '',                 1,       8,           current_timestamp()),
(21, '212121',  '',                 1,       9,           current_timestamp()),
(22, '222222',  '1',                1,       10,          current_timestamp()),
(23, '232323',  '',                 1,       11,          current_timestamp()),
(24, '242424',  'PROVA OPEN 2 form 1',1,       12,          current_timestamp());


INSERT INTO selected_answer_option
(answer_id, answer_option_id) VALUES
(13, 2),
(14, 5),
(15, 6),
(15, 7),
(17, 13),
(19, 16),
(20, 18),
(21, 20),
(21, 21),
(23, 26);
