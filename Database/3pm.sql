 use shopping_site;
create table product(
	product_id int auto_increment,
    product_name varchar(50),
    price int,
    product_desc varchar(100),
    category_id int,
    primary key(product_id)
);
-- to add new column in table
alter table product add weight int;
alter table product add color varchar(50);
-- to delete column from table
alter table product drop color;
-- to change datatype of column
alter table product modify weight varchar(50);


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

drop table sub_category;
drop database test_db;


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

-- to insert data
insert into customer(cust_name,cust_address,cust_phone,cust_city)
values('gaurav','parvatpatia','4545445','surat');
insert into customer(cust_name,cust_phone,cust_address,cust_city)
values('sujal','udhna','878546545','surat');

insert into order_detail(order_date,total,product_id,cust_id)
values(now(),5000,1,1);
insert into product(product_name,price,category_id) 
values('mobile',5000,2);
insert into category(cat_name) values('electronics');







