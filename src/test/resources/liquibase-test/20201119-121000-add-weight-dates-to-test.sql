--liquibase formatted sql

-- changeset angel:20201119-121000-1
-- agrega datos a block.weight question.weight para test

UPDATE  block SET weight =100  WHERE uuid ='4444';


-- update questions 
UPDATE  question SET weight =10  WHERE  uuid ='SSS';
UPDATE  question SET weight =10  WHERE  uuid ='XXX';
UPDATE  question SET weight =5  WHERE  uuid ='YYY';
UPDATE  question SET weight =20  WHERE  uuid ='ZZZ';
UPDATE  question SET weight =10  WHERE  uuid ='SSS';
UPDATE  question SET weight =40  WHERE  uuid ='1AAA';
UPDATE  question SET weight =15  WHERE  uuid ='1BBB';


-- update report_template

UPDATE report_template SET has_punctuation = true WHERE uuid ='AAAA';
