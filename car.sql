DROP DATABASE IF EXISTS car;
CREATE DATABASE car;
USE car;
CREATE TABLE users(ID varchar(24) NOT NULL, username varchar(255), password varchar(255), PRIMARY KEY(ID));
INSERT INTO users VALUES ("123", "noah" , "noah123");
INSERT INTO users VALUES ("333", "william" , "william333");
INSERT INTO users VALUES ("999", "emma" , "emma999");
SELECT * FROM users;