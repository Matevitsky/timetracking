DROP TABLE IF EXISTS activities;


DROP TABLE IF EXISTS activityRequests;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role;

create table role
(
    ID   int auto_increment,

    Name varchar(64) not null,
    PRIMARY KEY (ID)

);

create table users
(
    ID       int auto_increment,
    Name     varchar(64)  not null,
    Email    varchar(255) not null,
    Password varchar(32)  not null,
    Role     int          not null,
    PRIMARY KEY (ID),
    FOREIGN KEY (Role) REFERENCES role (ID)
);

create table activityRequests
(
    ID     int auto_increment,
    UserId int not null,


    PRIMARY KEY (ID),
    FOREIGN KEY (UserId) REFERENCES users (ID)

);

create table activities
(
    ID       int auto_increment,

    Title    varchar(64)  not null,
    Content  varchar(255) null,
    Duration int          null,
    UserId   int          null,
    PRIMARY KEY (ID),
    FOREIGN KEY (UserId) REFERENCES users (ID)

);


INSERT INTO role(Name)
VALUES ('User');

INSERT INTO role(Name)
VALUES ('Admin');

INSERT INTO users(Name, Email, Password, Role)
VALUES ('Sergey1', 'serg1@gmail.com', '11111', '1');

INSERT INTO users(Name, Email, Password, Role)
VALUES ('Sergey2', 'serg2@gmail.com', '2222', '1');

INSERT INTO users(Name, Email, Password, Role)
VALUES ('Sergey3', 'serg3@gmail.com', '3333', '1');

INSERT INTO users(Name, Email, Password, Role)
VALUES ('Sergey4', 'admin', '4444', '2');

INSERT INTO users(Name, Email, Password, Role)
VALUES ('user', 'user', 'user', '1');

INSERT INTO activities(Title, Content, Duration, UserId)
VALUES ('Tittle1', 'Content1', '100', '1');

