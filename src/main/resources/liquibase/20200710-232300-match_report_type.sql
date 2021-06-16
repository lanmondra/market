--liquibase formatted sql


-- changeset albert:20200710-232300-1
-- Creacio i inicialització taula match_report_type
CREATE TABLE IF NOT EXISTS `match_report_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `federation` VARCHAR(10) NOT NULL,
  `code` VARCHAR(25) NOT NULL,
  `deleted` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


INSERT INTO `match_report_type` (id, federation, code) VALUES
(1, 'fcbq', 'MAIN_REFEREE'),
(2, 'fcbq', 'AUX_REFEREE_COMMON'),
(3, 'fcbq', 'AUX_REFEREE_CRONO'),
(4, 'fcbq', 'AUX_REFEREE_24'),
(5, 'fcbq', 'AUX_REFEREE_ANNOTATOR');


-- changeset albert:20200710-232300-2
-- Clau forania a report_template
ALTER TABLE `report_template` ADD COLUMN `match_report_type_id` INT(10) UNSIGNED NULL AFTER `is_match_report`;
ALTER TABLE `report_template` ADD CONSTRAINT `match_report_type_report_template_fk`
    FOREIGN KEY (`match_report_type_id`)
    REFERENCES `match_report_type` (`id`);
