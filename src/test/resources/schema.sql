CREATE TABLE test_method
(
    ID            BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME          VARCHAR(256) NOT NULL,
    DESCRIPTION   VARCHAR(256) NOT NULL
);