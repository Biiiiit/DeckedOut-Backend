CREATE TABLE levels(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(MAX),
    lvlSprite BLOB,
    backgroundSprite BLOB,
    PRIMARY KEY (id)
);

CREATE TABLE level_enemies (
     level_id int,
     enemy_id int,
     FOREIGN KEY (level_id) REFERENCES levels(id),
     FOREIGN KEY (enemy_id) REFERENCES enemies(id)
);