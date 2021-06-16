--liquibase formatted sql

-- changeset angel:20201118-131400-001
-- agregar un campo weight en la tabla question

ALTER TABLE question ADD COLUMN weight INT NULL;