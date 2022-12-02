create database shivay_shoppingsite;
use shivay_shoppingsite;
create table products(
	pro_id int auto_increment,
    pro_name varchar(100),
    pro_price int,
    category varchar(100),
    primTablesary key(pro_id)
);

create table customers(
	cust_id int auto_increment,
    firstname varchar(100),
    lastname varchar(100),
    city varchar(100),
    state char(5),
    zip varchar(20),
    primary key(cust_id)
);

create table sales(
	sales_id int auto_increment,
    sales_price int,
    sales_date datetime,
    pro_id int,
    cust_id int,
    primary key(sales_id),
    foreign key(pro_id)references products(pro_id),
    foreign key(cust_id)references customers(cust_id)
);

insert into products
(pro_name,pro_price,category)
values('coffeepot',35.00,'kitchen');
delete from products  where pro_id = 7;

insert into customers
(firstname,lastname,city,state,zip)
values('priya','shah','varanasi','up',221002);

delete from customers  where cust_id = 6; 

insert into sales
(pro_id,cust_id,sales_price,sales_date)
values(3,20,205.00,'2005-12-31');

delete from sales where sales_id = 2;

-- b1
SELECT  FirstName,LastName,Pro_Name,Sales_Price
FROM Customers INNER JOIN Products ON Products.pro_id=customers.Cust_id INNER JOIN Sales  ON sales.sales_id=products.Pro_id
WHERE Sales_Date BETWEEN '2005-10-01' AND '2005-10-30'

-- b2
select cust_id,firstname,lastname
from customers
where cust_id not in (select distinct cust_id from sales)

-- b3
select firstname,lastname,sales_price 
from customers
inner join products on products. pro_id=  customers.cust_id
inner join sales on sales_id = products.pro_id

-- b4
select category,avg(sales_price)as total
from products inner join sales on products_id=sales_id
group by category


-- a3
select sales_date,cust_id,max(sales_price)
from sales
group by sales_date,cust_id
order by sales_date,cust_id

-- i1
DELIMITER //
create procedure vishal ()
begin
insert into sales(sales_price,sales_date,pro_id)
values('205','2005-12-31','2');
select * from sales;
end;
call vishal









  
    