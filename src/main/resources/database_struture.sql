DROP DATABASE IF EXISTS socialApp;
CREATE DATABASE IF NOT EXISTS socialApp;

USE socialApp;

CREATE TABLE IF NOT EXISTS users(
  id int not null auto_increment,
  name varchar(100) not null,
  username varchar(100) unique not null,
  password text not null,
  location varchar(100),
  email varchar(100) unique null,
  about text null,
  createdAt timestamp default current_timestamp,
  updatedAt timestamp default current_timestamp on update current_timestamp,
  primary key (id)
);

INSERT INTO `socialApp`.`users` (`name`, `username`, `password`, `location`, `email`, `about`)
VALUES ('Zino Adidi', 'zinoadidi', 'testpassword', 'Tallinn, Estonia', 'testzino@TEST.COM', 'i AM THE GUY YOU WANT TO KNOW');

CREATE TABLE IF NOT EXISTS userChats (
id int not null auto_increment,
username varchar(100)  not null,
messages
primary key(id));
