CREATE TABLE games (
                       id INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(255),
                       description TEXT,
                       icon BLOB,
                       banner BLOB,
                       user_id INT,  -- Foreign key column
                       PRIMARY KEY (id),
                       FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE game_areas (
                            game_id INT,
                            area_id INT,
                            FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
                            FOREIGN KEY (area_id) REFERENCES areas(id) ON DELETE CASCADE
);

CREATE TABLE game_cards (
                            game_id INT,
                            card_id INT,
                            FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
                            FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE CASCADE
);

CREATE TABLE game_enemies (
                              game_id INT,
                              enemy_id INT,
                              FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
                              FOREIGN KEY (enemy_id) REFERENCES enemies(id) ON DELETE CASCADE
);

CREATE TABLE game_levels (
                             game_id INT,
                             level_id INT,
                             FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
                             FOREIGN KEY (level_id) REFERENCES levels(id) ON DELETE CASCADE
);

CREATE TABLE game_characters (
                                 game_id INT,
                                 character_id INT,
                                 FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
                                 FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE
);
