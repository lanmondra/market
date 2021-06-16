--liquibase formatted sql


-- changeset albert:20201009-161600-001
-- Afegir camp deleted a form
ALTER TABLE form ADD COLUMN deleted TIMESTAMP NULL;