--liquibase formatted sql

-- changeset albert:20201112-102300-001
-- Add portal to poll
UPDATE poll SET portal = 0;