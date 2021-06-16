--liquibase formatted sql


-- changeset albert:20201014-161600-001
-- Esborrar valors invalids
DELETE FROM match_report_type;
ALTER TABLE match_report_type AUTO_INCREMENT = 1;


-- changeset albert:20201014-161600-002
-- Afegir camp deleted a form
ALTER TABLE match_report_type ADD COLUMN display_lit VARCHAR(30) NOT NULL AFTER code;


-- changeset albert:20201014-161600-003
-- Inicialitzar valors
INSERT INTO `match_report_type` (federation, code, display_lit) VALUES
('fcbq', 'DOUBLE', 'Doble (principal i auxiliar)'),
('fcbq', 'SIMPLE', 'Senzill'),
('fcbq', 'TUTORIA', 'Tutoria'),
('fcbq', 'CONSULTORIA', 'Consultoria'),
('fcbq', 'AUXILIAR_COMU', 'Auxiliar Comú'),
('fcbq', 'AUXILIAR_1', 'Auxiliar 1'),
('fcbq', 'AUXILIAR_2', 'Auxiliar 2'),
('fcbq', 'AUXILIAR_3', 'Auxiliar 3'),
('fcbq', 'VIDEO_DOBLE', 'Video Doble'),
('fcbq', 'VIDEO_SENZILL', 'Video Senzill');
