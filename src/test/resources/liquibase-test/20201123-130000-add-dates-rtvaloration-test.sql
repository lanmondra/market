--liquibase formatted sql

-- changeset angel:20201123-130000-1
-- Insert dates to report_temaplate_valorartion to test

INSERT INTO report_template_valoration ( uuid , name , min_grade , max_grade , report_template_id , last_update )VALUES 
('vAAAA','valoracion 01 rt A', 45, 60 , ( SELECT id FROM report_template WHERE uuid ='AAAA' ),current_timestamp() ),
('vBBBB','valoracion 02 rt B', 35, 45 , ( SELECT id FROM report_template WHERE uuid ='BBBB' ),current_timestamp() ),
('vCCCC','valoracion 03 rt C', 60, 70 , ( SELECT id FROM report_template WHERE uuid ='CCCC' ),current_timestamp() ),
('vDDDD','valoracion 04 rt D' , 55, 75 , ( SELECT id FROM report_template WHERE uuid ='DDDD' ),current_timestamp() ),
('vEEEE','valoracion 05 rt C', 80, 95 , ( SELECT id FROM report_template WHERE uuid ='CCCC' ),current_timestamp() );
