use todo_list;

drop table if exists ItemTags;
drop table if exists Item;
drop table if exists Tag;
drop table if exists User;

CREATE TABLE User(
    id INT(11) NOT NULL auto_increment, 
    login VARCHAR(50) NOT NULL,
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
    itemType INT(1) NOT NULL,
    priority INT(1) NOT NULL CHECK(NumericField BETWEEN 1 AND 5),
    severity INT(1) NOT NULL CHECK(NumericField BETWEEN 1 AND 3),
    userId INT(11) NOT NULL,
    state Int(1) NOT NULL,
    created DATE NOT NULL,
    modified DATE,
    originalEstimate INT(5) NOT NULL,
    remainingHours INT(5) NOT NULL,
    completedHours INT(5) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (userId) REFERENCES User (id)
);
--     itemType ENUM('BUG', 'TASK', 'FEATURE') NOT NULL,
--     state VARCHAR('NEW', 'ACTIVE', 'RESOLVED', 'CLOSED') NOT NULL,

CREATE TABLE ItemTags(
    id INT(11) auto_increment NOT NULL,
    idTag INT(11) NOT NULL,
    idItem INT(11) NOT NULL,
    Primary Key(id),
    unique key (idTag, idItem),
    CONSTRAINT FOREIGN KEY (idTag) REFERENCES Tag (id),
    CONSTRAINT FOREIGN KEY (idItem) REFERENCES Item (id)
    );
INSERT INTO User (login) values ('Artur'), ('Daniel');
select * from user;

INSERT INTO Tag (name) VALUES ('difficult'), ('urgent'),  ('returned'), ('tested');

INSERT INTO Item(title, body, itemType, priority, severity, userId,
	state, created, originalEstimate, remainingHours, completedHours)
    VALUES ('TestTask', 'First task for system tests', 1, 3, 1, 1,
    0, curdate(), 50, 50, 0);
    
SELECT i.id, i.title, i.body FROM Item i
JOin ItemTags it on it.idItem = i.id ;

INSERT INTO ItemTags (idTag, idItem) Values (1,1);
Select * from item;
