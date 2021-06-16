--liquibase formatted sql


-- changeset albert:20200630-230000-1
-- Inicialitzacio de taula bloc_template
INSERT INTO block_template
(id, federation, type, name, description, order_num, is_visible_subject) VALUES
(1, 'fcbq', 0, 'Dades del partit', '', 0, true);


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


