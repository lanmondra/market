--liquibase formatted sql

-- changeset angel:20201118-130000-001
-- agregar un campo weight en la tabla block

ALTER TABLE block ADD COLUMN weight INT NULL;

