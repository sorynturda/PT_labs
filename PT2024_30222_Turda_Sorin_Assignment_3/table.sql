create database test;
use test;

create table Client (
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL
);

create table Product(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	quantity int NOT NULL,
	PRIMARY KEY(id)
);


create table Orderr(
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	client_id int NOT NULL,
	product_id int NOT NULL,
	quantity int NOT NULL,
	FOREIGN KEY (client_id) REFERENCES Client(id),
	FOREIGN KEY (product_id) REFERENCES Product(id)
);
