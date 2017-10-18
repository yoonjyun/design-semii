--가맹점 리스트 뽑기(paging bean 이용)
select s_no, s_name
from ( select row_number() over(order by s_no desc) rnum, s_no, s_name, s_address, tel, openday
from store s) a
where a.rnum between 1 and 5;

-- 해당 가맹점별 메뉴 리스트 뽑기(paging bean 이용)
select m_no, m_name
from ( select row_number() over(order by m_no desc) rnum, m_no, m_name, s_no
		from menu) a
where a.rnum between 1 and 3 and s_no=104;

-- 해당 가맹점에 대한  가맹점 상세정보 
select s_name, s_name, s_address, tel, openday
from store s 
where s.s_no=101;

-- 특정 메뉴에 관한 상세정보 
-- 메뉴가격과 메뉴상세옵션 추가가격을 더한 것을 md_o_price 로 한다 
select md.md_no, m.m_price, md.md_add_price, (m.m_price+md.md_add_price)as md_o_price, 
md.hot_ice, md.size_type, m.m_name, m.m_info
from menu_detail md, menu m
where md.m_no=m.m_no and md.m_no=2005;

select * from menu_detail where m_no=2005;

-- 전체 가맹점 수 구하기
select count(*) from store;

-- 가맹점 별 전체 메뉴 수 구하기
select count(*) from menu where s_no=104;
