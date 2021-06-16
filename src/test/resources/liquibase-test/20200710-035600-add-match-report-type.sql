--liquibase formatted sql

-- changeset albert:20200710-035600-1
-- Update report_template to set match_report_type
UPDATE report_template SET match_report_type_id = 1 WHERE id = 1;
UPDATE report_template SET match_report_type_id = 2 WHERE id = 2;
