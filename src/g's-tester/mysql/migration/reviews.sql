use capstone_db;

drop table if exists reviews;

CREATE TABLE reviews
(
    id            INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_name     INT UNSIGNED NOT NULL,
    truck_name    INT UNSIGNED NOT NULL,
    rating        double NOT NULL,
    comment       VARCHAR(1000) NOT NULL,
    pictures      VARCHAR(1000),
    PRIMARY KEY (id),
    FOREIGN KEY (user_name) references users (id),
    FOREIGN KEY (truck_name) references trucks (id)
);
