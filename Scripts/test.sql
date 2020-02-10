select * from employee;
select * from department;
select * from title;
select title_no, title_name from title;

select title_no, title_name from title where title_no=1;

insert into title values(6, '인턴');
delete from title where title_no = 6;
update title set title_name = '회장' where title_no = 1;
update title set title_name = '사장' where title_no = 1;

select dept_no, dept_name, floor from department;

-- 조민희가 로그인하려고 할 경우 
select emp_no, emp_name, title, manager, salary, dept, hire_date
  from employee 
 where emp_no = 1003 and passwd = password('1234567');