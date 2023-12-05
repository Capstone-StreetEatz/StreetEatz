use capstone_db;

drop table if exists users;

CREATE TABLE users
(
    id            INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_name     VARCHAR(255) NOT NULL,
    user_email    VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_picture  VARCHAR(300),
    user_role     int unsigned not null,
    PRIMARY KEY (id),
    UNIQUE (user_email),
    UNIQUE (user_name),
    FOREIGN KEY (user_role) references user_role (id)
);


