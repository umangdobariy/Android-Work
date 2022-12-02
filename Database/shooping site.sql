create database shopping_site;
use shopping_site;
create table customer(
	cust_id int auto_increment,
    cust_name varchar(50),
    cust_add varchar(50),
    cust_phone varchar(50),
    cust_city varchar(50),
    primary key (cust_id)
);

create table product(
	product_id int auto_increment,
    product_name varchar(50),
	product_price varchar(50),
    product_category varchar(50),
    primary key(product_id)
);

create table category(
	cat_id int auto_increment,
    cat_name varchar(50),
    primary key(cat_id)
);

create table sub_category(
	subcat_id int auto_increment,
    subcat_name varchar(50),
    primary key(subcat_id)
);
create table order_detail(
	order_id int auto_increment,
    order_date datetime,
    total int,
    product_id int,
    cust_id int,
    primary key(order_id),
    foreign key(product_id) references product(product_id),
    foreign key(cust_id) references customer(cust_id)
);

-- to add new column in table

alter table product add weight int;
alter table product add color varchar(50);

-- to delete column from table
alter table product drop color;

-- to change datatype of column 
alter table product modify weight varchar(50);

drop table sub_category;

--- to insert data
insert into customer
(cust_name,cust_add,cust_phone,cust_city)
values('nilesh','mahadev park','6246948','mumbai');



insert into order_detail(order_date,total,product_id,cust_id)
values(now(),80000,9,9);

insert into product(product_name,product_price,product_category)
values('eletornic car','80000',4);

insert into category(cat_name)
values('electronics');

update order_detail set order_date=now()where order_id = 12; 
update order_detail set total=35000 where order_id = 15;
delete from order_detail where order_id = 12;

select * from customer;
select * from product;

select cust_id,cust_name from customer;

-- title name capital 

select cust_name as CUSTOMER_NAME,cust_phone as CUST_PHONE from customer;

select distinct cust_name,cust_phone from customer;
select * from customer where cust_id=1

select product_name from product where product_price > 10000; 

select * from product where category_id <>1;

select * from product where price between 20000 and 30000;

select * from customer where  customer_name like 'a%';

--- ascding- desding

select * from customer;
select * from customer order by cust_name;
select * from customer order by cust_name desc;
select * from product order by product_price;

-- function group by :- 
select count(cust_id),cust_city from customer group by cust_city;

select count(cust_id) from customer group by cust_city;

select avg(order_price), product_name from product group by product_name;

select customer.cust_name,order_detail.total from customer inner join 
order_detail on customer.cust_id =  order_detail.cust_id;

select *
from order_detail
left join product
on product.product_id = order_detail.cust_id;

select *
from order_detail
right join product
on product.product_id = order_detail.cust_id;

-- functions
--- aggregate function

select avg(product_price) as avrage_price from product;
select count(*) as total from product;
select count(*) as total from product where product_name='mobile';
select max(product_price) from product;
select * from product;

-- scalar function 
select ucase(cust_name) from customer;
select mid(cust_name,1,3)from customer;
select date_format(order_date,'%m-%d') from order_detail;  

DELIMITER //
create procedure insertshow()
begin
insert into product (product_name,product_price) values('cable',5000);		
select * from product;
end;

call insertshow

DELIMITER //
create procedure update_detail(in product_name varchar(50),in cid int,in w int)
begin
update product set product_name=name,product_category=cid,weight=w;
select * from product;
end;
call update_detail('mouse','1','50');

DELIMITER //
create trigger testtrigger
after delete on product for each row 
begin 
insert into 
order_detail(order_date,total,product_id,customer_id)
values(now(),400,3,2);
end 


  
    
    
    
