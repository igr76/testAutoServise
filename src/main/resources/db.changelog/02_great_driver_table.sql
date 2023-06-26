-- Создание таблицы водителей
CREATE TABLE driver
(
    passport INT PRIMARY KEY ,
    fio VARCHAR(256),
    category VARCHAR,
    born VARCHAR,
    experience INT,
    dollar INT,
    auto_id VARCHAR,
    constraint fk_auto_id foreign key (auto_id) references auto (auto_number)
);