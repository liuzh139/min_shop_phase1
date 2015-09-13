CREATE TABLE products (
ID INTEGER NOT NULL,
NAME VARCHAR NOT NULL,
description varchar2 not null,
category varchar2 not null,
price number not null,
stock number not null,
CONSTRAINT PK_products PRIMARY KEY(ID)
);

create table customer (
username varchar2 not null,
name varchar2 not null,
address varchar2 not null,
cardDetails varchar2 not null,
password varchar2 not null,
--
constraint pk_customer primary key(username)
);