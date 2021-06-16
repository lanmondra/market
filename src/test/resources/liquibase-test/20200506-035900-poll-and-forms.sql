--liquibase formatted sql



-- changeset albert:20200506-035900-1
-- Insert test polls and forms
-- Dos encuestas de club y una de arbitros
INSERT INTO poll
(id, uuid,   name,                   federation, status, activation_time,       ending_time,           anonymous, description,                                  login_required, change_answer_allowed, informer_type_id, informer_categories,     last_update, deleted, last_action_user) VALUES
(1,  '1111', 'TEST POLL 1',          'test',     1,      '2020-05-01 12:00:00', '2020-12-31 23:59:59', false,     'Test poll 1 description (clubs)',            true,           true,                  2,                'CAT1',                  null,    null,    null),
(2,  '2222', 'TEST POLL 2',          'test',     0,      '2020-05-01 12:00:00', '2020-12-31 23:59:59', false,     'Test poll 2 description (clubs)',            true,           true,                  2,                'CAT1',                  null,    null,    null),
(3,  '3333', 'TEST POLL 3',          'test',     1,      '2020-05-01 12:00:00', '2020-12-31 23:59:59', false,     'Test poll 3 description (arbitres)',         true,           true,                  1,                'CAT1',                  null,    null,    null),
(4,  '4444', 'TEST POLL 4 (ended)',  'test',     1,      '2020-05-01 12:00:00', '2020-05-02 23:59:59', false,     'Test poll 4 description (clubs i caducada)', true,           true,                  2,                'CAT1',                  null,    null,    null);


-- No añadir respuestas al form 1, se usa en los test para pruebas de alta i borrado de respuestas
-- Form 5 tiene todas las respuestas entradas
-- No crear mas forms para report template 1. Se usa para verificar edición de bloque y preguntas
-- Form 7 debe tener status 1. Se usa para validaciones de preguntas y respuestas
INSERT INTO form
(id, uuid,   subject_uuid,                                    informer_uuid,                          status, assigned,                 fill_out_date,          visualized, report_template_id, poll_id) VALUES
(1,  '1111', null,                                            '508952c9-8fa1-11ea-b836-02b7c2952a14', 1,      '2020-01-01 00:00:00',    null,                   false,      null,               1 ),
(2,  '2222', null,                                            '508952c9-8fa1-11ea-b836-02b7c2952a14', 1,      '2020-01-01 00:00:00',    null,                   false,      null,               2 ),
(3,  '3333', null,                                            '508952c9-8fa1-11ea-b836-02b7c2952a14', 0,      '2020-01-01 00:00:00',    null,                   false,      null,               3 ),
(4,  '4444', null,                                            '508952c9-8fa1-11ea-b836-02b7c2952a14', 0,      '2020-01-01 00:00:00',    null,                   false,      null,               4 ),
(5,  '5555', null,                                            '508ad6df-8fa1-11ea-b836-02b7c2952a14', 3,      '2020-01-01 00:00:00',    '2020-01-01 00:00:00',  false,      null,               1 ),
(6,  '6666', null,                                            '508ad6df-8fa1-11ea-b836-02b7c2952a14', 0,      '2020-01-01 00:00:00',    null,                   false,      null,               2 ),
(7,  '7777', 'a44e083e-5d77-4ac6-81d2-9c63fc1fce18',          '508952c9-8fa1-11ea-b836-02b7c2952a14', 1,      '2020-01-01 00:00:00',    '2020-05-01 00:00:00',  false,      1,              null ),
(8,  '8888', 'a44e083e-5d77-4ac6-81d2-9c63fc1fce18',          '508ad6df-8fa1-11ea-b836-02b7c2952a14', 0,      '2020-02-01 00:00:00',    '2020-06-01 00:00:00',  false,      2,              null ),
(9,  '9999', 'a44e083e-5d77-4ac6-81d2-9c63fc1fce18',          '508ad6df-8fa1-11ea-b836-02b7c2952a14', 0,      '2020-03-01 00:00:00',    '2020-07-01 00:00:00',  false,      2,              null ),
(10, '101010', 'a44e083e-5d77-4ac6-81d2-9c63fc1fce18',        '508ad6df-8fa1-11ea-b836-02b7c2952a14', 0,      '2020-04-01 00:00:00',    '2020-08-01 00:00:00',  false,      2,              null );

