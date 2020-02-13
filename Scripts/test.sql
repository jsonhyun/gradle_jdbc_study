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

update department set dept_name='기획' where dept_no =2;

-- 조민희가 로그인하려고 할 경우 
select emp_no, emp_name, title, manager, salary, dept, hire_date
  from employee 
 where emp_no = 1003 and passwd = password('1234567');
 
select e.emp_no, e.emp_name , e.title , t.title_name, m.emp_name as manager_name , m.emp_no as manager_no , e.salary , e.dept , d.dept_name 
  from employee e left join employee m on e.manager = m.emp_no join department d on e.dept = d.dept_no join title t on e.title = t.title_no 
 where e.title =3;
 

select emp_no, emp_name, t.title_name 
  from employee e left join title t on e.title = t.title_no 
 where dept = 2;
 
select e.emp_no, e.emp_name, t.title_no, t.title_name, m.emp_no as manager_no, m.emp_name as manager_name,
       e.salary, d.dept_no, d.dept_name, e.hire_date, e.pic 
  from employee e left join title t on e.title = t.title_no
 	   left join employee m on e.manager = m.emp_no 
 	   left join department d on e.dept = d.dept_no;
