use capstone_db;

drop table if exists trucks;

CREATE TABLE trucks
(
    id            INT UNSIGNED NOT NULL AUTO_INCREMENT,
    truck_name    VARCHAR(255) NOT NULL,
    owners_id   INT UNSIGNED NOT NULL,
    location      VARCHAR(1000) NOT NULL,
    pictures      VARCHAR(2000) Not NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (owners_id) references users (id)
);

