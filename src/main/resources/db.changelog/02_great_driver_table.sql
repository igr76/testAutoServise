CREATE TABLE driver
(
    id         integer generated always as identity primary key,
    passport INT ,
    fio VARCHAR(256),
    category VARCHAR,
    born TEXT,
    experience INT,
    dollar INT
);