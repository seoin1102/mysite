-- board

desc board;

insert into board values(null, "첫번째 글", "첫번째 글의 본문", 3, "2022-05-30", 1, 1, 1,1); 
insert into board values(null, "첫번째 글", "첫번째 글의 댓글", 3, "2022-05-30", 1, 2, 2,1); 
insert into board values(null, "두번째 글", "두번째 글의 본문", 3, "2022-05-30", 2, 1, 1,1); 


select*from board;
select*from user;
select* from board where g_no=2; 


select * from board where no=4;

delete from board where g_no=2; 

select * from board order by g_no desc, o_no asc where g_no=2;