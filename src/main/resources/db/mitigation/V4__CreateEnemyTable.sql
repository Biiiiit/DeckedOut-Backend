CREATE TABLE enemies{
    id int NOT NULL AUTO_INCREMENT,
    name varchar(MAX),
    health int(MAX),
    attack int(MAX),
    healing int(MAX),
    shielding int(MAX),
    pattern varchar(MAX),
    sprite BLOB
    }