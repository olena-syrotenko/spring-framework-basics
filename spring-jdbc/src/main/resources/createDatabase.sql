CREATE DATABASE `spring_jdbc` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION = 'N' */;

create table if not exists spring_jdbc.employee
(
	id         int auto_increment
		primary key,
	first_name varchar(100) null,
	last_name  varchar(100) null
);