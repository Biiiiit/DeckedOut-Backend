CREATE TABLE games (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    description TEXT,
    icon BLOB,
    banner BLOB,
    PRIMARY KEY (id)
);

CREATE TABLE game_areas (
    game_id INT,
    area_id INT,
    FOREIGN KEY (game_id) REFERENCES games(id)
);

CREATE TABLE game_cards (
    game_id INT,
    card_id INT,
    FOREIGN KEY (game_id) REFERENCES games(id)
);

CREATE TABLE game_enemies (
    game_id INT,
    enemy_id INT,
    FOREIGN KEY (game_id) REFERENCES games(id)
);

CREATE TABLE game_levels (
    game_id INT,
    level_id INT,
    FOREIGN KEY (game_id) REFERENCES games(id)
);

CREATE TABLE game_characters (
    game_id INT,
    character_id INT,
    FOREIGN KEY (game_id) REFERENCES games(id)
);
