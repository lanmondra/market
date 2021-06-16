--liquibase formatted sql

-- changeset albert:20200506-000000-1
-- Insert report templates

INSERT INTO report_category
(id, uuid,     name,         federation, last_update, deleted, last_action_user) VALUES
(1,   'AAAA', 'CATEGORIA 1', 'test',     null,    null,     null),
(2,   'BBBB', 'CATEGORIA 2', 'test',     null,    null,     null);


INSERT INTO report_template
(id, uuid,   name,                            federation, status, created,              is_visible, show_referee, portal,    allow_not_designated, is_match_report, match_report_type_id,  informer_type_id, subject_type_id, report_category_id, last_update, deleted, last_action_user) VALUES
(1, 'AAAA', 'PLANTILLA INFORME 1',            'test',      0,     current_timestamp(), true,       true,         0,         false,                true,             1,                     1,                1,                1,                 null,   null,    null),
(2, 'BBBB', 'PLANTILLA INFORME 2',            'test',      0,     current_timestamp(), true,       true,         0,         false,                true,             2,                     1,                1,                1,                 null,   null,    null),
(3, 'CCCC', 'PLANTILLA INFORME 3',            'test',      0,     current_timestamp(), false,      true,         0,         false,                false,            null,                  1,                1,                2,                 null,   null,    null),
(4, 'DDDD', 'PLANTILLA INFORME 4',            'test',      0,     current_timestamp(), true,       true,         0,         false,                false,            null,                  1,                1,                2,                 null,   null,    null);


INSERT INTO campaign
(id, uuid,   name,         start_date,   end_date,     report_template_id, last_update, deleted, last_action_user) VALUES
(1,  '1111', 'CAMPANYA 1', '2020-01-01', '2021-12-31',  1,                  null,    null,    null),
(2,  '2222', 'CAMPANYA 2', '2020-01-01', '2021-12-31',  2,                  null,    null,    null);



INSERT INTO permission
(federation,  name,                 can_edit, report_category_id) VALUES
('test',      'REPORTS_CAT_EDIT_1', true,     1),
('test',      'REPORTS_CAT_READ_1', false,    1),
('test',      'REPORTS_CAT_EDIT_2', true,     2),
('test',      'REPORTS_CAT_READ_2', false,    2);




