--liquibase formatted sql

-- changeset angel:20201118-131700-001
-- agregar un campo has-punctuation en la tabla report_template

ALTER TABLE report_template  ADD COLUMN has_punctuation  BOOLEAN NOT NULL DEFAULT FALSE;