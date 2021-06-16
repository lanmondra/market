--liquibase formatted sql

-- changeset angel:20201117-124100-1

-- insert report template
INSERT INTO report_template
(id, uuid,   name,                            federation, status, created,              is_visible, show_referee, portal,    allow_not_designated, is_match_report, match_report_type_id,  informer_type_id, subject_type_id, report_category_id, last_update, deleted, last_action_user, has_punctuation) VALUES
(5, 'EEEE', 'PLANTILLA INFORME 4',            'test',      0,     current_timestamp(), true,       true,         0,         false,                true,             1,                     1,                1,                1,                 null,   null,    null   , true);
-- insert form 
INSERT INTO form
(id, uuid,  		 subject_uuid,                                  informer_uuid,         status, 		assigned,          fill_out_date,  visualized, report_template_id, poll_id) VALUES
(11,  'FORM9', 'a44e083e-5d77-4ac6-81d2-9c63fc1fce18', '508ad6df-8fa1-11ea-b836-02b7c2952a14', 0, '2020-11-27 13:17:00', '2020-11-27 13:17:00',  false,      (SELECT id FROM report_template where uuid ='EEEE'),              null );
-- insert block 
INSERT INTO `block` (`id`,	 `uuid`,	 `name`,	 `description`,			 `order_num`, 	`report_template_id`,	 `poll_id`, 	`last_update`,	 `deleted`,	 `last_action_user`,	`weight`)VALUES
					(7,  	 '7777','Bloc numero 7','Block del report template 5',1,		(SELECT id FROM report_template where uuid ='EEEE'),					NULL,			 NULL,			 NULL,			NULL,				 40),
					(8,		'8888','Bloc numero 8','Block del report template 5',1,			(SELECT id FROM report_template where uuid ='EEEE'),					NULL,			 NULL,			 NULL,	 		NULL, 				 60);


INSERT INTO `question` (`id`, `uuid`, `value`, `has_answer`, `mandatory`, `order_num`, `answer_type_id`,last_update, `deleted`, `last_action_user`, block_id, block_template_id, automatic_answer_name, weight)
VALUES
	(51, '7AAA', 'Pregunta 1 del bloc 7',1,1,1,1,NULL,NULL,NULL, (SELECT id FROM block where uuid ='7777'), NULL, NULL,20 ),
	(52, '7BBB', 'Pregunta 2 del bloc 7',1,1,2,2,NULL,NULL,NULL,  (SELECT id FROM block where uuid ='7777'), NULL, NULL,10),
    (53, '7CCC', 'Pregunta 3 del bloc 7',1,1,1,1,NULL,NULL,NULL,  (SELECT id FROM block where uuid ='7777'), NULL, NULL, 30),
	(54, '7DDD', 'Pregunta 4 del bloc 7',1,1,2,2,NULL,NULL,NULL,  (SELECT id FROM block where uuid ='7777'), NULL, NULL, 5),
    (55, '7EEE', 'Pregunta 5 del bloc 7',1,1,1,1,NULL,NULL,NULL,  (SELECT id FROM block where uuid ='7777'), NULL, NULL, 10),
	(56, '7FFF', 'Pregunta 6 del bloc 7',1,1,2,2,NULL,NULL,NULL,  (SELECT id FROM block where uuid ='7777'), NULL, NULL,25 ),
    (57, '8AAA', 'Pregunta 1 del bloc 8',1,1,1,1,NULL,NULL,NULL,  (SELECT id FROM block where uuid ='8888'), NULL, NULL, 20),
	(58, '8BBB', 'Pregunta 2 del bloc 8',1,1,2,2,NULL,NULL,NULL,  (SELECT id FROM block where uuid ='8888'), NULL, NULL,10),
	(59, '8CCC', 'Pregunta 3 del bloc 8',1,1,1,1,NULL,NULL,NULL,  (SELECT id FROM block where uuid ='8888'), NULL, NULL,30),
	(60, '8DDD', 'Pregunta 4 del bloc 8',1,1,2,4,NULL,NULL,NULL,  (SELECT id FROM block where uuid ='8888'), NULL, NULL,5),
    (61, '8EEE', 'Pregunta 5 del bloc 8',1,1,1,1,NULL,NULL,NULL,  (SELECT id FROM block where uuid ='8888'), NULL, NULL,20),
	(62, '8FFF', 'Pregunta 6 del bloc 8',1,1,2,2,NULL,NULL,NULL,  (SELECT id FROM block where uuid ='8888'), NULL, NULL,15);

-- update questions

INSERT INTO `answer_option` 
		( `uuid`, 				`value`, 	  `color`, `description`, `order_num`, `last_update`, `deleted`, `last_action_user`, question_id, 		punctuation) VALUES
	('ANSWER-OPTION-AA', 'answer option  1',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 51, 				20),
    ( 'ANSWER-OPTION-BB', 'answer option  2',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 52,				35),
    ( 'ANSWER-OPTION-CC', 'answer option  3',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 53,				12),
    ( 'ANSWER-OPTION-DD', 'answer option  4',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 54,				22),
    ('ANSWER-OPTION-EE', 'answer option  5',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 55,				60),
    ('ANSWER-OPTION-FF', 'answer option  6',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 56,				35),
    ( 'ANSWER-OPTION-GG', 'answer option  7',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 57,				40),
	('ANSWER-OPTION-HH', 'answer option  8',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 58,				66),
    ( 'ANSWER-OPTION-II', 'answer option  9',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 59,				26),
    ( 'ANSWER-OPTION-JJ', 'answer option  10',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 34,				88),
    ( 'ANSWER-OPTION-KK', 'answer option  11',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 61,				55),
    ( 'ANSWER-OPTION-LL', 'answer option  12',NULL,	  NULL,			 1,				NULL,		NULL,			NULL,				 62,				76),
    ( 'ANSWER-OPTION-MM', '1',				  NULL,	  NULL,			 1,			    NULL,		NULL,			NULL,				 60, 			   NULL),
	( 'ANSWER-OPTION-NN', '10', 			  NULL,	  NULL,			 1,			    NULL,		NULL,			NULL,				 60, 			   NULL);
   
	

INSERT INTO answer
(id, uuid,   value,            		  form_id,									 question_id, 											answer_time) VALUES
(13, 'AAA1',  '',               	(SELECT id FROM form where uuid ='FORM9') , (SELECT id FROM question where uuid ='7AAA')   ,           current_timestamp()),
(14, 'AAA2',  '',                	(SELECT id FROM form where uuid ='FORM9'),  (SELECT id FROM question where uuid ='7BBB')    ,           current_timestamp()),
(15, 'AAA3',  '',                 	(SELECT id FROM form where uuid ='FORM9'),  (SELECT id FROM question where uuid ='7CCC')    ,           current_timestamp()),
(16, 'AAA4',  '6',                	(SELECT id FROM form where uuid ='FORM9'),  (SELECT id FROM question where uuid ='7DDD')   ,           current_timestamp()),
(17, 'AAA5',  '',                   (SELECT id FROM form where uuid ='FORM9'),  (SELECT id FROM question where uuid ='7EEE')   ,           current_timestamp()),
(18, 'AAA6',  'PROVA OPEN Form 5',  (SELECT id FROM form where uuid ='FORM9'),  (SELECT id FROM question where uuid ='7FFF')   ,           current_timestamp()),
(19, 'AAA7',  '',                   (SELECT id FROM form where uuid ='FORM9'),  (SELECT id FROM question where uuid ='8AAA')   ,           current_timestamp()),
(20, 'AAA8',  '',                   (SELECT id FROM form where uuid ='FORM9'),  (SELECT id FROM question where uuid ='8BBB')   ,           current_timestamp()),
(21, 'AAA9',  '',                   (SELECT id FROM form where uuid ='FORM9'),  (SELECT id FROM question where uuid ='8CCC')   ,           current_timestamp()),
(22, 'AAA10',  '2',                 (SELECT id FROM form where uuid ='FORM9'),  (SELECT id FROM question where uuid ='8DDD')   ,          current_timestamp()),
(23, 'AAA11',  '',                  (SELECT id FROM form where uuid ='FORM9'),  (SELECT id FROM question where uuid ='8EEE')   ,          current_timestamp()),
(24, 'AAA12',  'PROVA OPEN 2 form 5',(SELECT id FROM form where uuid ='FORM9'), (SELECT id FROM question where uuid ='8FFF')   ,          current_timestamp());









INSERT INTO selected_answer_option
(answer_id, answer_option_id) VALUES
((SELECT id FROM answer where uuid ='AAA1'),(SELECT id FROM answer_option where uuid ='ANSWER-OPTION-AA' ) ),
((SELECT id FROM answer where uuid ='AAA2'), (SELECT id FROM answer_option where uuid ='ANSWER-OPTION-BB' ) ),
((SELECT id FROM answer where uuid ='AAA3'), (SELECT id FROM answer_option where uuid ='ANSWER-OPTION-CC' ) ),
((SELECT id FROM answer where uuid ='AAA4'), (SELECT id FROM answer_option where uuid ='ANSWER-OPTION-DD' ) ),
((SELECT id FROM answer where uuid ='AAA5'), (SELECT id FROM answer_option where uuid ='ANSWER-OPTION-EE' ) ),
((SELECT id FROM answer where uuid ='AAA6'), (SELECT id FROM answer_option where uuid ='ANSWER-OPTION-FF' ) ),
((SELECT id FROM answer where uuid ='AAA7'), (SELECT id FROM answer_option where uuid ='ANSWER-OPTION-GG' ) ),
((SELECT id FROM answer where uuid ='AAA8'), (SELECT id FROM answer_option where uuid ='ANSWER-OPTION-HH' ) ),
((SELECT id FROM answer where uuid ='AAA9'), (SELECT id FROM answer_option where uuid ='ANSWER-OPTION-II' ) ),
((SELECT id FROM answer where uuid ='AAA10'), (SELECT id FROM answer_option where uuid ='ANSWER-OPTION-JJ' ) ),
((SELECT id FROM answer where uuid ='AAA11'), (SELECT id FROM answer_option where uuid ='ANSWER-OPTION-KK' ) ),
((SELECT id FROM answer where uuid ='AAA12'), (SELECT id FROM answer_option where uuid ='ANSWER-OPTION-LL' ) );


-- valoration 

INSERT INTO report_template_valoration ( uuid , name , min_grade , max_grade , report_template_id , last_update )VALUES 
('FFF','val muy mala', 0, 20 , ( SELECT id FROM report_template WHERE uuid ='EEEE' ),current_timestamp() ),
('GGG','val mala', 20, 35 , ( SELECT id FROM report_template WHERE uuid ='EEEE' ),current_timestamp() ),
('HHH','val regular', 35, 60 , ( SELECT id FROM report_template WHERE uuid ='EEEE' ),current_timestamp() ),
('III','val aceptable' , 60, 75 , ( SELECT id FROM report_template WHERE uuid ='EEEE' ),current_timestamp() ),
('JJJ','val sobresaliente', 75, 99 , ( SELECT id FROM report_template WHERE uuid ='EEEE' ),current_timestamp() );







