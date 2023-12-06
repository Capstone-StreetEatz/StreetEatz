# use capstone_db;
#
# drop table if exists user_role;
#
# CREATE TABLE user_role
# (
#     id         INT UNSIGNED NOT NULL AUTO_INCREMENT,
#     roles      VARCHAR(255) NOT NULL,
#     PRIMARY KEY (id),
#     UNIQUE (roles)
# );