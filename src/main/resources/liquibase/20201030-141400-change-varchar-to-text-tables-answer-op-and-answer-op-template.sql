--liquibase formatted sql

-- changeset angel:20201030-141400-001
-- canbia varchar a text  campo description

-- TABLE answer_option-------

ALTER TABLE answer_option MODIFY description TEXT  NULL;



-- canbia varchar a text  campo description
-- TABLE answer_option_template-------

ALTER TABLE answer_option_template MODIFY description TEXT  NULL;