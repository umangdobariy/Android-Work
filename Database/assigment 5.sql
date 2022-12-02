create database itcompany;
use itcompany;
create table employee(
	emp_no int,
    emp_name varchar(50),
    emp_bdate date,
    emp_title varchar(50),
    emp_salary int,
    emp_dno int
);

create table project(
	p_no int,
    p_name varchar(50),
    budget int,
    dno int
);

create table department(
	d_dno int,
    d_name varchar(50),
    d_m