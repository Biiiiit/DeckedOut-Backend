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
    character_id int,
    card_id int,
    FOREIGN KEY (character_id) REFERENCES characters(id),
    FOREIGN KEY (card_id) REFERENCES card(id)
);
