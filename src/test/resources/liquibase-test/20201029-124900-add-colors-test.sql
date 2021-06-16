--liquibase formatted sql

-- changeset angel:20201029-124900-1
-- Insert colors for test

INSERT INTO report_template_colors (federation , name , code_color,deleted )VALUES 
('AAAA' , 'black','#000000',null ),
('AAAA' , 'blue','#0000FF',null ),
('AAAA' , 'green','#008000',null ),
('AAAA' , 'darkcyan','#008B8B',null ),
('AAAA' , 'darkorange','#FF8C00',null );