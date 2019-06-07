CREATE DATABASE IF NOT EXISTS Time
    DEFAULT CHARACTER SET utf8;
USE Time;
DROP TABLE IF EXISTS activities;
DROP TABLE IF EXISTS activityRequests;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role;

create table role
(
    ID       int auto_increment UNIQUE,

    RoleName varchar(64) not null UNIQUE,
    PRIMARY KEY (ID)

);

create table users
(
    ID       int auto_increment,
    Name     varchar(64)  not null,
    Email    varchar(255) not null UNIQUE,
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
    ID          int auto_increment,

    Title       varchar(64)  not null,
    Description varchar(255) null,
    Duration    int          null,
    UserId      int          null,
    Status      varchar(64)  not null,
    PRIMARY KEY (ID),
    FOREIGN KEY (UserId) REFERENCES users (ID)

);


INSERT INTO role(RoleName)
VALUES ('User');

INSERT INTO role(RoleName)
VALUES ('Admin');



INSERT INTO users(Name, Email, Password, Role)
VALUES ('Sergey4', 'admin@gmail.com', '21232f297a57a5a743894a0e4a801fc3', '2');

INSERT INTO users(Name, Email, Password, Role)
VALUES ('user', 'user@gmail.com', 'ee11cbb19052e40b07aac0ca060c23ee', '1');


INSERT INTO activities(Title, Description, Duration, UserId, Status)
VALUES ('Tittle2', 'Content2', '', NULL, 'NEW');

INSERT INTO activities(Title, Description, Duration, UserId, Status)
VALUES ('Tittle3', 'Content3', '', NULL, 'NEW');


