create database if not exists ims_test;
drop table if exists ims_test.Customers;
create table if not exists ims_test.Customers(cust_ID int primary key auto_increment, first_name varchar(40), last_name varchar(40), username varchar(40), password varchar(40), UNIQUE(cust_ID), UNIQUE(username));
CREATE TABLE if not exists ims_test.Orders (order_ID INT NOT NULL AUTO_INCREMENT, fk_cust_ID INT NOT NULL, total_cost DEC(7,2), PRIMARY KEY(order_ID), FOREIGN KEY(fk_cust_ID) REFERENCES Customers (cust_ID), UNIQUE(order_ID));
CREATE TABLE if not exists ims_test.Items (item_ID int NOT NULL AUTO_INCREMENT, item_name varchar(60) NOT NULL, quant_in_stock INT, price dec(7,2), UNIQUE(item_ID), PRIMARY KEY(item_ID));
CREATE TABLE if not exists ims_test.Order_details (fk_order_ID INT, fk_item_ID INT NOT NULL, quantity INT DEFAULT 1, total_price DEC(7,2), FOREIGN KEY(fk_item_ID) REFERENCES Items (item_ID), FOREIGN KEY(fk_order_ID) REFERENCES Orders (order_ID));




