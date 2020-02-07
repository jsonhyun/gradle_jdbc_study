select * from employee;
select * from department;
select * from title;
select title_no, title_name from title;

select title_no, title_name from title where title_no=1;

insert into title values(6, '인턴');
delete from title where title_no = 6;
update title set title_name = '회장' where title_no = 1;
update title set title_name = '사장' where title_no = 1;