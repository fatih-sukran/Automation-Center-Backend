-- METRIC
INSERT INTO metric (id, name, code) VALUES (21, 'Metric 21', 'code21');
INSERT INTO metric (id, name, code) VALUES (22, 'Metric 22', 'code22');
INSERT INTO metric (id, name, code) VALUES (23, 'Metric 23', 'code23');

-- SUITE
INSERT INTO suite (id, name, description, cron) VALUES (21, 'Suite 21', 'description 21', '2 1 0 * * ?');
INSERT INTO suite (id, name, description, cron) VALUES (22, 'Suite 22', 'description 22', '2 2 0 * * ?');
INSERT INTO suite (id, name, description, cron) VALUES (23, 'Suite 23', 'description 23', '2 3 0 * * ?');

-- SUITE_METRIC
INSERT INTO suite_metric (suite_id, metric_id) VALUES (21, 21);
INSERT INTO suite_metric (suite_id, metric_id) VALUES (21, 22);
INSERT INTO suite_metric (suite_id, metric_id) VALUES (21, 23);
INSERT INTO suite_metric (suite_id, metric_id) VALUES (22, 21);
INSERT INTO suite_metric (suite_id, metric_id) VALUES (22, 22);
INSERT INTO suite_metric (suite_id, metric_id) VALUES (23, 21);

-- PAGE
INSERT INTO page (id, suite_id, name, url) VALUES (21, 21, 'Page 21', 'url21');
INSERT INTO page (id, suite_id, name, url) VALUES (22, 21, 'Page 22', 'url22');
INSERT INTO page (id, suite_id, name, url) VALUES (23, 21, 'Page 23', 'url23');

-- REPORT
INSERT INTO report (id, suite_id, date) VALUES (21, 21, '2020-01-01 00:00:00');
INSERT INTO report (id, suite_id, date) VALUES (22, 21, '2020-01-02 00:00:00');
INSERT INTO report (id, suite_id, date) VALUES (23, 21, '2020-01-03 00:00:00');

-- RESULT
INSERT INTO result (id, page_id, metric_id, report_id, result_value) VALUES (21, 21, 21, 21, '21');
INSERT INTO result (id, page_id, metric_id, report_id, result_value) VALUES (22, 21, 22, 21, '22');
INSERT INTO result (id, page_id, metric_id, report_id, result_value) VALUES (23, 21, 23, 21, '23')
