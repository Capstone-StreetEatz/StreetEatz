use nfl_adlister;

drop table if exists truck_foodtypes;

CREATE TABLE truck_foodtypes
(
 truck_id      INTEGER UNSIGNED NOT NULL,
 foodtype_id    INTEGER UNSIGNED NOT NULL,
       foreign key (truck_id) references trucks(id),
       foreign key (foodtype_id) references type_of_food (id)
