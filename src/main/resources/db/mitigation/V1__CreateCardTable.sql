CREATE TABLE cards{
    id int NOT NULL AUTO_INCREMENT,
    name varchar(MAX),
    type varchar(MAX),
    damage int(MAX),
    healing int(MAX),
    shielding int(MAX)
    PRIMARY KEY (id)
    }