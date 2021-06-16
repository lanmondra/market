--liquibase formatted sql

-- changeset albert:20201106-131200-001
-- nou tipus resposta link
INSERT INTO answer_type (code, is_multiple_choice, is_value_range, is_allowed_in_poll) VALUES
('LINK', 0, 0, 0);

  


