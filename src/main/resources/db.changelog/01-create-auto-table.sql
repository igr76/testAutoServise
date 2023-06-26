-- Создание таблицы автомобилей
CREATE TABLE auto
(
    auto_number VARCHAR PRIMARY KEY ,
    auto_vin INT,
    manufacturer VARCHAR,
    model VARCHAR,
    year_of_release INT,
    list_of_details INT,
    driver_id INT,
    constraint fk_driver_id foreign key (driver_id) references driver (passport)
);
CREATE TABLE auto_list_of_details
(
    details_id  INT,
    index       INT,
    details VARCHAR
);