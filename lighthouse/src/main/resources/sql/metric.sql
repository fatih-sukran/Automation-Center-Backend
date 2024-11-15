-- Veritabanını sıfırla
DELETE FROM metric;

-- Test verilerini ekle
INSERT INTO metric (name, code) VALUES ('Metric 1', 'code1');
INSERT INTO metric (name, code) VALUES ('Metric 2', 'code2');
INSERT INTO metric (name, code) VALUES ('Metric 3', 'code3');
