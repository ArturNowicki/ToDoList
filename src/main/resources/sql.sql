use todo_list;

drop table if exists User;
CREATE TABLE User(
    id INT NOT NULL auto_increment, 
    login VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

