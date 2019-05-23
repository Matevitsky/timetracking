DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS activities;

/*CREATE SEQUENCE global_seq START 100000;*/
create table users
(
    ID       int auto_increment,
    Name     varchar(64)  null,
    Email    varchar(255) null,
    Password varchar(32)  null,
    Role     varchar(255) null,
    PRIMARY KEY (ID)
);

create table activities
(
    ID       int auto_increment,

    Title    varchar(64)  null,
    Content  varchar(255) null,
    Duration int          null,
    UserId   int          null,
    PRIMARY KEY (ID),
    FOREIGN KEY (UserId) REFERENCES Users (ID)

);

INSERT INTO Users(Name, Email, Password, Role)
VALUES ('Sergey1', 'serg1@gmail.com', '11111', 'REGULAR');

INSERT INTO Users(Name, Email, Password, Role)
VALUES ('Sergey2', 'serg2@gmail.com', '2222', 'REGULAR');

INSERT INTO Users(Name, Email, Password, Role)
VALUES ('Sergey3', 'serg3@gmail.com', '3333', 'REGULAR');

INSERT INTO Users(Name, Email, Password, Role)
VALUES ('Sergey4', 'serg4@gmail.com', '4444', 'ADMIN');

INSERT INTO Users(Name, Email, Password, Role)
VALUES ('user', 'user', 'user', 'REGULAR');

INSERT INTO Activities(Title, Content, Duration, UserId)
VALUES ('Tittle1', 'Content1', '100', '1');