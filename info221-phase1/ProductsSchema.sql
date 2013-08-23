CREATE TABLE products (
ID INTEGER NOT NULL,
NAME VARCHAR NOT NULL,
description varchar2 not null,
category varchar2 not null,
price number not null,
stock number not null,
CONSTRAINT PK_products PRIMARY KEY(ID)
);