use todo_list;

drop table if exists ItemTags;
drop table if exists Item;
drop table if exists Tag;
drop table if exists User;

CREATE TABLE User(
    id INT(11) NOT NULL auto_increment, 
    login VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    PRIMARY KEY (id),
    UNIQUE KEY (login)
);

CREATE TABLE Tag(
    id INT(11) NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (name)
);

CREATE TABLE Item(
    id INT(11) NOT NULL auto_increment,
    title VARCHAR(50) NOT NULL,
    body VARCHAR(50),
	itemType ENUM('BUG', 'TASK', 'FEATURE') NOT NULL,
    priority INT(1) NOT NULL CHECK(NumericField BETWEEN 1 AND 5),
    severity INT(1) NOT NULL CHECK(NumericField BETWEEN 1 AND 3),
    userId INT(11) NOT NULL,
    state ENUM('NEW', 'ACTIVE', 'RESOLVED', 'CLOSED') NOT NULL,
    created DATE NOT NULL,
    modified DATE,
    originalEstimate INT(5) NOT NULL,
    remainingHours INT(5) NOT NULL,
    completedHours INT(5) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (userId) REFERENCES User (id)
);

CREATE TABLE ItemTags(
    id INT(11) auto_increment NOT NULL,
    idTag INT(11) NOT NULL,
    idItem INT(11) NOT NULL,
    Primary Key(id),
    unique key (idTag, idItem),
    CONSTRAINT FOREIGN KEY (idTag) REFERENCES Tag (id),
    CONSTRAINT FOREIGN KEY (idItem) REFERENCES Item (id)
    );
INSERT INTO User (login, email) values ('Artur', 'anowicki@sda.pl');
INSERT INTO User (login) values ('Daniel');
INSERT INTO User (login, email) values ('Tomasz', 'tomek@sda.pl');
select * from user;

INSERT INTO Tag (name) VALUES ('difficult'), ('urgent'),  ('returned'), ('tested');

INSERT INTO Item(title, body, itemType, priority, severity, userId,
	state, created, originalEstimate, remainingHours, completedHours)
    VALUES ('TestTask', 'First task for system tests', 1, 3, 1, 3,
    'ACTIVE', curdate(), 50, 50, 0);

INSERT INTO Item(title, body, itemType, priority, severity, userId,
	state, created, originalEstimate, remainingHours, completedHours)
    VALUES ('TestTask2', 'Second task for system tests', 2, 2, 2, 2,
    'NEW', curdate(), 24, 8, 18);

    
SELECT i.id, i.title, i.body FROM Item i
JOin ItemTags it on it.idItem = i.id ;

INSERT INTO ItemTags (idTag, idItem) Values (1,1), (2, 1), (1,2), (3, 2);
Select i.id, i.title from Item i join ItemTags it on (it.idItem=i.id);
