--liquibase formatted sql


-- changeset albert:20200705-232300-1
-- Creacio index per fede i nom a report_template
CREATE INDEX `report_template_fede_name_idx` ON `report_template` (`federation` ASC, `name` ASC);