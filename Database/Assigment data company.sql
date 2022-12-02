create database company;
use company;
create table employee(
	emp_id int auto_increment,
    emp_fristname varchar(50),
    emp_lastname varchar(50),
    emp_salary int,
    emp_joindate datetime,
    emp_department varchar(50),
    primary key(emp_id)
);
create table incentive(
	empref_id int,
    incentive_date datetime,
    incentive_amount int,
    emp_id int,
    foreign key(emp_id)references employee(emp_id)
);

insert into employee
(emp_fristname,emp_lastname,emp_salary,emp_joindate,emp_department)
values('amit','sanghani',7500000,'01-01-13','services');

insert into incentive
(empref_id,incentive_date,incentive_amount)
values(2,'2013-1-1',3500);

-- alish name;
select emp_fristname as employee_name from employee;

-- fristname and joindate
select emp_fristname,emp_joindate from employee;

-- ascending and descending 
select * from employee order by emp_fristname; 
select * from employee order by emp_salary desc;

-- frist name contains 'o'
select emp_fristname from employee where emp_fristname like '%o%';

-- joining mounth january
select emp_joindate from employee;

-- department and salary 
select * from employee where emp_department or emp_salary;
select * from employee order by emp_salary desc;

-- maximum salary and salary desecding
select max(emp_salary) from employee;
select * from employee order by emp_salary;

-- frist_name and incentive_amount greater than 3000
select * from incentive where incentive_amount >3000;

use company;
-- 2nd highest salary from emoloyee table
select max(emp_salary)  
from employee 
where emp_salary not in (select max(emp_salary) from employee) 


-- left join
select employee.emp_fristname,incentive.incentive_amount
from employee
left join incentive
on employee.emp_id = incentive.emp_id;

-- first name,lastname,salary from employee
select emp_fristname,emp_lastname,emp_salary from employee;

-- procedures to find out department higst salary 
DELIMITER //
create procedure highest()
begin
select emp_department,max(emp_salary) from employee group by(emp_department);
end;
call highest()








