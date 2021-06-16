--liquibase formatted sql


-- changeset albert:20200527-030000-1
-- Inicialitzacio de taula answerTemplate
INSERT INTO answer_template
(id, uuid,   name,                                federation, language, answer_type_id) VALUES
(1,  uuid(), "Binaria si - no",                   "ALL",       "ca",     1),
(2,  uuid(), "Binaria si - no",                   "ALL",       "es",     1),
(3,  uuid(), "Binaria acord-no acord",            "ALL",       "ca",     1),
(4,  uuid(), "Binaria acuerdo - no acuerdo",      "ALL",       "es",     1),
(5,  uuid(), "Numérica 1-5",                      "ALL",       "ca",     1),
(6,  uuid(), "Numérica 1-5",                      "ALL",       "es",     1),
(7,  uuid(), "Numérica 1-10",                     "ALL",       "ca",     1),
(8,  uuid(), "Numérica 1-10",                     "ALL",       "es",     1);


INSERT INTO answer_option_template
(id, value,             color, description, order_num, answer_template_id) VALUES
(1,  'Si',              null,  null, 1, 1),
(2,  'No',              null,  null, 2, 1),
(3,  'Sí',              null,  null, 1, 2),
(4,  'No',              null,  null, 2, 2),
(5,  'D''acord',        null,  null, 1, 3),
(6,  'No d''acord',     null,  null, 2, 3),
(7,  'De acuerdo',      null,  null, 1, 4),
(8,  'No de acuerdo',   null,  null, 2, 4),
(9,  '1',               null,  null, 1, 5),
(10,  '5',              null,  null, 2, 5),
(11,  '1',              null,  null, 1, 6),
(12,  '5',              null,  null, 2, 6),
(13,  '1',              null,  null, 1, 7),
(14,  '10',             null,  null, 2, 7),
(15,  '1',              null,  null, 1, 8),
(16,  '10',             null,  null, 2, 8);


