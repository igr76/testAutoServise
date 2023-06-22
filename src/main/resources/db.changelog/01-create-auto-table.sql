CREATE TABLE auto
(
    id         integer generated always as identity primary key,
    auto_number VARCHAR,
    auto_vin bigint,
    manufacturer VARCHAR,
    model VARCHAR,
    year_of_release INT
);
-- CREATE TABLE auto_list_of_details
-- (
--     details_id  bigint,
--     index       bigint,
--     details text
-- );