DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
  customer_Id INT AUTO_INCREMENT  PRIMARY KEY,
  Customer_Name VARCHAR(256) NOT NULL,
  Customer_Profile VARCHAR(256) NOT NULL,
  Customer_Age INTEGER(3)
);

Insert into customer(customer_Id,Customer_Name,Customer_Profile,Customer_Age)
values(1001,'Cust12','itutu',34);
Insert into customer(customer_Id,Customer_Name,Customer_Profile,Customer_Age)
values(1002,'Cust45','uiuty',45);
Insert into customer(customer_Id,Customer_Name,Customer_Profile,Customer_Age)
values(1003,'Cust67','mhjhh',21);