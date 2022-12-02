create database filpkart;
use filpkart;
create table salseperson(
	s_no int,
    s_name varchar(50),
    city varchar(50),
    comm int,
    primary key(s_no)
);

create table customer(
	c_nm int,
    c_name varchar(50),
    city varchar(50),
    rating int,
	s_no int,
    primary key(c_nm),
    foreign key(s_no)references salseperson(s_no)
);

create table order_detail(
	o_nm int,
    amt int,
    o_date date,
    s_no int,
    c_nm int,
    primary key(o_nm),
    foreign key(c_nm)references customer(c_nm),
    foreign key(s_no)references salseperson(s_no)
);
    
insert into salseperson
(s_no,s_name,city,comm)
values('1003','axelrod','new york','1');

insert into customer
(c_nm,c_name,city,rating,s_no)
values('207','pereira','roe','100','1004');

insert into order_detail
(o_nm,amt,o_date,c_nm,s_no)
values('3011','3011','94-10-06','206','1001'); 

-- order for than 1000
select amt from order_detail where amt > 1000;

--
Select s_name, city
from salseperson
where comm >= 10 and city = 'London';

--
Select s_name,city
from salseperson
where city in ('barcelona','London');

--
Select s_name, comm
from salseperson
where comm >=10 and comm <=12;

--
select c_name
from customer
where rating <=100;

--
Select amt
from order_detail
where amt != 0 or
amt is not null;

--
Select count(distinct s_no)
from order_detail;

--
select o_date,s_no,max(amt)
from order_detail 
group by o_date,s_no
order by o_date,s_no;

--
select o_date,s_no,max(amt)
from order_detail 
where amt >3000
group by o_date,s_no
order by o_date,s_no;



