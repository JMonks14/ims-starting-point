create database if not exists ims_project;
create table if not exists ims_project.Customers(id int primary key auto_increment, first_name varchar(40), last_name varchar(40), username varchar(40), password varchar(40));
