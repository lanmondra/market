--liquibase formatted sql


-- changeset albert:20200805-161600-1
-- AnswerType ha de poder ser null per les questions que no tenen resposta
ALTER TABLE question MODIFY COLUMN answer_type_id INT(10) UNSIGNED NULL;