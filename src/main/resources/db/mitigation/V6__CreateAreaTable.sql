CREATE TABLE areas(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(MAX),
    description varchar(MAX)
    backgroundSprite BLOB,
    PRIMARY KEY (id)
);

CREATE TABLE area_levels (
    area_id int,
    level_id int,
    FOREIGN KEY (area_id) REFERENCES areas(id)
);