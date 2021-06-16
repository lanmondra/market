--liquibase formatted sql

-- changeset angel:20201029-103900-1
-- creacion de tabal de colores 



CREATE TABLE IF NOT EXISTS report_template_colors  (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT ,
	federation VARCHAR(10) NOT NULL,
    name VARCHAR(20) NOT NULL ,
    code_color VARCHAR (10) NOT NULL,
    deleted TIMESTAMP NULL,
	 PRIMARY KEY (`id`),
    INDEX report_template_colors_idx (federation ASC)
    
 )
ENGINE = InnoDB;
