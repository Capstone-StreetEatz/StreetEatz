use capstone_db;

drop table if exists reviews;

CREATE TABLE reviews
(
    id            INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id     INT UNSIGNED NOT NULL,
    truck_id    INT UNSIGNED NOT NULL,
    rating        decimal (4,2) NOT NULL,
    comment       VARCHAR(1000) NOT NULL,
    pictures      VARCHAR(1000),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) references users (id),
    FOREIGN KEY (truck_id) references trucks (id)
);
