use capstone_db;

drop table if exists food_type;

CREATE TABLE food_type
(
    id            INT UNSIGNED NOT NULL AUTO_INCREMENT,
    food_type     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (food_type)
);