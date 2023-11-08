CREATE TABLE characters(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(MAX),
    description varchar(MAX),
    health int(MAX),
    ammo int(MAX),
    sprite BLOB,
    PRIMARY KEY (id)
);

CREATE TABLE character_starting_deck (
                                         character_id INT,
                                         card_id INT,
                                         FOREIGN KEY (character_id) REFERENCES characters(id) ON DELETE CASCADE,
                                         FOREIGN KEY (card_id) REFERENCES cards(id) ON DELETE CASCADE
);




