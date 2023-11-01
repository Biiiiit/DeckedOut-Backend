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
     card_id int,  -- Allow this to be nullable
     FOREIGN KEY (character_id) REFERENCES characters(id)
);
