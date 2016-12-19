use todo_list;

drop table if exists Item;
drop table if exists Tags;
drop table if exists User;
CREATE TABLE User(
    id INT(10) NOT NULL auto_increment, 
    login VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (login)
);

CREATE TABLE Tags(
    id INT(10) NOT NULL auto_increment,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (name)
);

CREATE TABLE Item(
    id INT(10) NOT NULL auto_increment,
    title VARCHAR(50) NOT NULL,
    body VARCHAR(50),
    itemType ENUM('BUG', 'TASK', 'FEATURE') NOT NULL,
    priority INT(1) NOT NULL,
    severity INT(1) NOT NULL,
    tagId INT(10) NOT NULL,
    userId INT(10) NOT NULL,
    state ENUM('NEW','ACTIVE', 'RESOLVED', 'CLOSED') NOT NULL,
    created DATE NOT NULL ,
    modified DATE NOT NULL ,
    originalEstimate INT(5) NOT NULL,
    remainingHours INT(5) NOT NULL,
    completedHours INT(5) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (tagId) REFERENCES Tags (id),
    CONSTRAINT FOREIGN KEY (userId) REFERENCES User (id)

);

INSERT INTO user (login) values ('Artur'), ('Daniel');
select * from user;

INSERT INTO Tags (name) VALUES ('difficult'), ('urgent'),  ('returned');