-- METRIC
INSERT INTO metric (id, name, code) VALUES (210, 'Metric 21', 'code21');
INSERT INTO metric (id, name, code) VALUES (220, 'Metric 22', 'code22');
INSERT INTO metric (id, name, code) VALUES (230, 'Metric 23', 'code23');

-- SUITE
INSERT INTO suite (id, name, description, cron) VALUES (210, 'Suite 21', 'description 21', '2 1 0 * * ?');
INSERT INTO suite (id, name, description, cron) VALUES (220, 'Suite 22', 'description 22', '2 2 0 * * ?');
INSERT INTO suite (id, name, description, cron) VALUES (230, 'Suite 23', 'description 23', '2 3 0 * * ?');

-- SUITE_METRIC
INSERT INTO suite_metric (suite_id, metric_id) VALUES (210, 210);
INSERT INTO suite_metric (suite_id, metric_id) VALUES (210, 220);
INSERT INTO suite_metric (suite_id, metric_id) VALUES (210, 230);
INSERT INTO suite_metric (suite_id, metric_id) VALUES (220, 210);
INSERT INTO suite_metric (suite_id, metric_id) VALUES (220, 220);
INSERT INTO suite_metric (suite_id, metric_id) VALUES (230, 210);

-- PAGE
INSERT INTO page (id, suite_id, name, url) VALUES (210, 210, 'Page 21', 'url21');
INSERT INTO page (id, suite_id, name, url) VALUES (220, 210, 'Page 22', 'url22');
INSERT INTO page (id, suite_id, name, url) VALUES (230, 210, 'Page 23', 'url23');

-- REPORT
INSERT INTO report (id, suite_id, date) VALUES (210, 210, '2020-01-01 00:00:00');
INSERT INTO report (id, suite_id, date) VALUES (220, 210, '2020-01-02 00:00:00');
INSERT INTO report (id, suite_id, date) VALUES (230, 210, '2020-01-03 00:00:00');

-- RESULT
INSERT INTO result (id, page_id, metric_id, report_id, result_value) VALUES (210, 210, 210, 210, '21');
INSERT INTO result (id, page_id, metric_id, report_id, result_value) VALUES (220, 210, 220, 210, '22');
INSERT INTO result (id, page_id, metric_id, report_id, result_value) VALUES (230, 210, 230, 210, '23')
