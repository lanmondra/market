--liquibase formatted sql

-- changeset angel:20201103-171200-001
-- add campo portal on poll table edit informer_categories

ALTER TABLE poll ADD portal INT , MODIFY informer_categories VARCHAR (200)  NULL ;

  


