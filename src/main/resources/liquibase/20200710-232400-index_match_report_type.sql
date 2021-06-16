--liquibase formatted sql


-- changeset albert:20200710-232400-1
-- Index per federacio
CREATE INDEX `match_report_type_fede_idx` ON `match_report_type` (`federation` ASC);