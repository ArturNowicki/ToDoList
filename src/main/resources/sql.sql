use todo_list;

drop table if exists ItemTags;
drop table if exists Item;
drop table if exists Tag;
drop table if exists User;

CREATE TABLE User(
    id INT(11) NOT NULL auto_increment, 
    login VARCHAR(100) NOT NULL,
    pass VARCHAR(50) NOT NULL,
    email VARCHAR(50),
    type VARCHAR(30) NOT NULL,
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
    body VARCHAR(1000),
	itemType ENUM('BUG', 'TASK', 'FEATURE') NOT NULL,
    priority INT(1) NOT NULL CHECK(NumericField BETWEEN 1 AND 5),
    severity INT(1) NOT NULL CHECK(NumericField BETWEEN 1 AND 3),
    userId INT(11) NOT NULL,
    state ENUM('NEW', 'ACTIVE', 'RESOLVED', 'CLOSED') NOT NULL,
    created DATE NOT NULL,
    createdBy INT(11) NOT NULL,
    modified DATE,
    originalEstimate INT(5) NOT NULL,
    remainingHours INT(5) NOT NULL,
    completedHours INT(5) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (userId) REFERENCES User (id),
    CONSTRAINT FOREIGN KEY (createdBy) REFERENCES User (id)
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

INSERT INTO User (login, pass, email, type) values ('artur', '$2a$10$fM2geGBoqiRH1SGfEzDkxeZMFjgm90Nss/wmBMkMkxJybDDopClZG', 'anowicki@sda.pl', 'ADMIN');
INSERT INTO User (login, pass, type) values ('daniel', '$2a$10$HpZf5l7uf0uxkkjdEQkU9.VLV9IebLwwvL9eUrXXIsJwqtEVyQAFy', 'ADMIN');
INSERT INTO User (login, pass, email, type) values ('tomasz', '$2a$10$QYZMpiYBjTQkV/7C9Xppv.fJefF1uwsDDIJQl0AJGOqziTFEm.3Su', 'tomek@sda.pl', 'USER');
select * from user;

INSERT INTO Tag (name) VALUES ('difficult'), ('urgent'),  ('returned'), ('tested');

INSERT INTO Item(title, body, itemType, priority, severity, userId,
	state, created, createdBy, originalEstimate, remainingHours, completedHours)
    VALUES ('password validation', 'confirm password with JS', 2, 3, 2, 1,
    'RESOLVED', curdate(), 1, 20, 0, 15);

INSERT INTO Item(title, body, itemType, priority, severity, userId,
	state, created, createdBy, originalEstimate, remainingHours, completedHours)
    VALUES ('external JS', 'external JS not working', 'BUG', 3, 2, 1,
    'NEW', curdate(), 1, 10, 0, 12);

    
SELECT i.id, i.title, i.body FROM Item i
JOin ItemTags it on it.idItem = i.id ;

INSERT INTO ItemTags (idTag, idItem) Values (1,1), (2, 1), (1,2), (3, 2);
Select i.id, i.title from Item i join ItemTags it on (it.idItem=i.id);

SELECT * FROM USER;
