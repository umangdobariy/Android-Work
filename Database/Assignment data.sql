create database school;
use school;
create table user(
	user_fristname varchar(50),
    user_lastname varchar(50),
    user_address varchar(50),
    user_city varchar(50),
    use_age int
);

create table student(
	stu_rollno int auto_increment,
    stu_name varchar(50),
    stu_branch varchar(50),
    primary key(stu_rollno)
);

create table exam(
	exam_id int auto_increment,
    exam_rollno int,
    exam_scode varchar(50),
    exam_marks int,
    exam_pcode varchar(50),
    stu_rollno int,
    primary key(exam_id),
    foreign key(stu_rollno)references student(stu_rollno)
);
insert into user(user_fristname,user_lastname,user_address,user_city,use_age)
values('sagar','sakhiya','130 yuva park','gondal',27);

insert into student(stu_rollno,stu_name,stu_branch)
values(3,'ravi','electronic science');

insert into exam(exam_rollno,exam_scode,exam_marks,exam_pcode)
values(3,'ec102',50,'ec')

    


    
    