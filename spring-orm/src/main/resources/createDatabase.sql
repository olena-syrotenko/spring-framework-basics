CREATE DATABASE `spring_orm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION = 'N' */

create table spring_orm.product
(
	id          int auto_increment
		primary key,
	name        varchar(50)   not null,
	description varchar(100)  null,
	price       decimal(8, 2) not null
);

