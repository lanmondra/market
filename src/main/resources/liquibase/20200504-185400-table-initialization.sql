--liquibase formatted sql


-- changeset albert:20200504-185400-1
-- Inicialitzacio de taules
INSERT INTO subject_type (id, code) VALUES
(1, 'REFEREE'),
(2, 'FEDERATED'),
(3, 'CLUB'),
(4, 'INSTALLATION');


INSERT INTO informer_type (id, code) VALUES
(1, 'REFEREE'),
(2, 'CLUB'),
(3, 'FEDERATED');


INSERT INTO answer_type (id, code, is_multiple_choice, is_value_range, is_allowed_in_poll) VALUES
(1, 'BINARY', false, false, true),
(2, 'EXCLUDING', false, false, true),
(3, 'MULTIPLE', true, false, false),
(4, 'NUMERIC', false, true, true),
(5, 'EXCLUDING_COLOR', false, false, false),
(6, 'OPEN', false, false, false);


INSERT INTO permission (id, federation, name, can_edit, report_category_id) VALUES
(1, NULL, 'REPORTS_ADMIN', true, NULL),
(2, NULL, 'REPORTS_ADMIN_REFEREE', true, NULL),
(3, NULL, 'REPORTS_ADMIN_FEDERATED', true, NULL),
(4, NULL, 'REPORTS_ADMIN_CLUB', true, NULL),
(5, NULL, 'REPORTS_POLL_ADMIN_REFEREE', true, NULL),
(6, NULL, 'REPORTS_POLL_ADMIN_FEDERATED', true, NULL),
(7, NULL, 'REPORTS_POLL_ADMIN_CLUB', true, NULL);