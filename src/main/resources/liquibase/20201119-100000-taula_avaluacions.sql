--liquibase formatted sql

-- changeset albert:20201119-100000-001
-- Taula criteris valoracio
CREATE TABLE `report_template_valoration` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid` VARCHAR(36) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `min_grade` INT NOT NULL,
  `max_grade` INT NOT NULL,
  `report_template_id` INT(10) UNSIGNED NULL,
  `last_update` TIMESTAMP NULL,
  `deleted` TIMESTAMP NULL,
  `last_action_user` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `report_template_valoration_fk_idx` (`report_template_id` ASC) ,
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC),
  CONSTRAINT `evaluation_report_template_fk`
    FOREIGN KEY (`report_template_id`)
    REFERENCES `report_template` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
;
  


