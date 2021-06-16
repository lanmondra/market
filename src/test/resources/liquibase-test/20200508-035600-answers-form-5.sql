--liquibase formatted sql

-- changeset albert:20200508-035600-1
-- Insert selected answers for form 5
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

