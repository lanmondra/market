--liquibase formatted sql

-- changeset angel:20201118-132000-001
-- agregar un campo punctuaction en la tabla answer_option
ALTER TABLE answer_option ADD COLUMN punctuation INT;