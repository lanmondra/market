--liquibase formatted sql

-- changeset albert:99999999-999999-1 runAlways:true
-- Change federation to test
UPDATE report_category SET federation = 'test';
UPDATE permission SET federation = 'test';
UPDATE report_template SET federation = 'test';
UPDATE poll SET federation = 'test';
UPDATE answer_template SET federation = 'test' WHERE federation = 'fcbq';
UPDATE block_template SET federation = 'test' WHERE federation = 'fcbq';
UPDATE match_report_type SET federation = 'test' WHERE federation = 'fcbq';

-- changeset albert:99999999-999999-2 runAlways:true
-- Change answer_template uuid
UPDATE answer_template SET uuid = CAST(id AS CHAR);
