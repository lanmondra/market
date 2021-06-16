--liquibase formatted sql


-- changeset angel:20201216-102500

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-- -----------------------------------------------------
-- Table base_role
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS base_role (
  id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, 
  name VARCHAR(45) UNIQUE NOT NULL,
  PRIMARY KEY (`id`)  )
 

 ENGINE = InnoDB ;
 
  -- -----------------------------------------------------
-- Table base_user-role
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS base_user_role (
  base_user_id INT NOT NULL , 
  base_role_id INT NOT NULL 
    )
 
 ENGINE = InnoDB ;


 
 
 -- -----------------------------------------------------
-- Table base_user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS base_user (
  id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT, 
  uuid VARCHAR(36) UNIQUE NOT NULL,
  email VARCHAR(50) UNIQUE NULL,
  default_lang VARCHAR(2) NOT NULL,
  register_status INT NOT NULL DEFAULT 0,
  validation_code VARCHAR(36) NULL,
  pw_recover_code VARCHAR(36) NULL,
  password VARCHAR(100) NULL,
  PRIMARY KEY (`id`))

 ENGINE = InnoDB ;
 
 -- -----------------------------------------------------
-- Table person
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS person (
   id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,  
  name VARCHAR(100) NOT NULL,
  identification_number_type INT NOT NULL,
  identification_number VARCHAR(20) UNIQUE NOT NULL,
  address VARCHAR(100) NOT NULL,
  zip_code VARCHAR(15) NOT NULL,
  town_id INT(10) UNSIGNED NOT NULL,
  base_user_id INT(10) UNSIGNED NOT NULL,
  created TIMESTAMP NOT NULL,
  updated TIMESTAMP NULL,
  deleted TIMESTAMP NULL,
PRIMARY KEY (`id`),

  CONSTRAINT person_town_fk
    FOREIGN KEY (town_id)
    REFERENCES town (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT person_user_fk
    FOREIGN KEY (base_user_id)
    REFERENCES base_user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
 ENGINE = InnoDB ;