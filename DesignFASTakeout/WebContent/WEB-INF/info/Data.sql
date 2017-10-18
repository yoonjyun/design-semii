-- 권한 
insert into authority(auth) values('customer');
insert into authority(auth) values('owner');

-- 회원
--업주
insert into member(id, password, name, phone, paypassword, balance, regdate, auth)
values('java','1234','송중기','01012341234','9999',0,sysdate,'customer');
-- 고객 (fast입니다!!)
insert into member(id, password, name, phone, paypassword, balance, regdate, auth)
values('fast','1234','이동욱','01012341234','9999',0,sysdate,'customer');

update member set auth='owner' where id='java';
select * from member;

-- 업종분류에 넣을 데이터
insert into category(cate_name) values('한식');
insert into category(cate_name) values('중식');
insert into category(cate_name) values('일식');
insert into category(cate_name) values('양식');
insert into category(cate_name) values('분식');
insert into category(cate_name) values('카페&디저트');
insert into category(cate_name) values('아이스크림');
insert into category(cate_name) values('베이커리');
select * from category;

-- 가맹점에 넣을 데이터 
insert into store(s_no,s_name,s_address,tel,openday,s_img, id) 
values(s_no_seq.nextval,'탐앤탐스','판교','01011111111','월요일 부터 화요일','/img/img.jpg','java');

insert into store(s_no,s_name,s_address,tel,openday,s_img, id) 
values(s_no_seq.nextval,'스타벅스','판교','01011111111','월요일 부터 화요일','/img/img.jpg','java');

insert into store(s_no,s_name,s_address,tel,openday,s_img, id) 
values(s_no_seq.nextval,'계절밥상','판교','01011111111','월요일 부터 화요일','/img/img.jpg','java');

insert into store(s_no,s_name,s_address,tel,openday,s_img, id) 
values(s_no_seq.nextval,'차이웍','판교','01011111111','월요일 부터 화요일','/img/img.jpg','java');

insert into store(s_no,s_name,s_address,tel,openday,s_img, id) 
values(s_no_seq.nextval,'스시스시','판교','01011111111','월요일 부터 화요일','/img/img.jpg','java');

insert into store(s_no,s_name,s_address,tel,openday,s_img, id) 
values(s_no_seq.nextval,'베트남쌀국수','판교','01011111111','월요일 부터 화요일','/img/img.jpg','java');

insert into store(s_no,s_name,s_address,tel,openday,s_img, id) 
values(s_no_seq.nextval,'한솥도시락','판교','01011111111','월요일 부터 화요일','/img/img.jpg','java');

insert into store(s_no,s_name,s_address,tel,openday,s_img, id) 
values(s_no_seq.nextval,'서서갈비','판교','01011111111','월요일 부터 화요일','/img/img.jpg','java');

select * from store;

-- 가맹점 별 분류에 넣을 데이터
insert into store_category(cate_name,s_no) values('카페&디저트',100);
insert into store_category(cate_name,s_no) values('카페&디저트',101);
insert into store_category(cate_name,s_no) values('한식',102);
select * from store_category;

-- 메뉴에 넣을 데이터
insert into menu(m_no,s_no,m_info,m_name,m_img) values(m_no_seq.nextval,102,'맛있어요','김치찌개','/img/img.jpg');
insert into menu(m_no,s_no,m_info,m_name,m_img) values(m_no_seq.nextval,102,'맛있어요','된장찌개','/img/img.jpg');
insert into menu(m_no,s_no,m_info,m_name,m_img) values(m_no_seq.nextval,102,'맛있어요','백숙','/img/img.jpg');
insert into menu(m_no,s_no,m_info,m_name,m_img) values(m_no_seq.nextval,100,'시원해요','아메리카노','/img/img.jpg');
insert into menu(m_no,s_no,m_info,m_name,m_img) values(m_no_seq.nextval,100,'달콤해요','카페라떼','/img/img.jpg');
insert into menu(m_no,s_no,m_info,m_name,m_img) values(m_no_seq.nextval,101,'보성','녹차라떼','/img/img.jpg');
insert into menu(m_no,s_no,m_info,m_name,m_img) values(m_no_seq.nextval,101,'초콜릿','카페모카','/img/img.jpg');
insert into menu(m_no,s_no,m_info,m_name,m_img) values(m_no_seq.nextval,101,'상큼해요','스무디','/img/img.jpg');
select * from menu;

--  사이즈 타입
insert into size_type(size_type) values('small');
insert into size_type(size_type) values('medium');
insert into size_type(size_type) values('large');

-- hot,ice정보
insert into HOT_ICE_TYPE(hot_ice) values('hot');
insert into HOT_ICE_TYPE(hot_ice) values('ice');

-- menu_detail
insert into menu_detail(md_no, md_price, hot_ice, size_type, m_no) values(md_no_seq.nextval, 2000, 'hot','small',2004);
insert into menu_detail(md_no, md_price, hot_ice, size_type, m_no) values(md_no_seq.nextval, 2500, 'ice','small',2004);
insert into menu_detail(md_no, md_price, hot_ice, size_type, m_no) values(md_no_seq.nextval, 3000, 'hot','large',2004);
insert into menu_detail(md_no, md_price, hot_ice, size_type, m_no) values(md_no_seq.nextval, 3500, 'ice','large',2004);
insert into menu_detail(md_no, md_price, hot_ice, size_type, m_no) values(md_no_seq.nextval, 5000, 'hot','medium',2006);
insert into menu_detail(md_no, md_price, hot_ice, size_type, m_no) values(md_no_seq.nextval, 5500, 'ice','medium',2006);
select * from menu_detail;

-- 주문 처리상태
select * from order_status;

insert into order_status(o_status) values('조리중');
insert into order_status(o_status) values('주문완료');
insert into order_status(o_status) values('장바구니');

--주문 
select * from orders;

insert into orders(o_no, quantity, o_price, o_date, md_no, id, o_status)
values(o_no_seq.nextval, 2, 4000, sysdate, 10101, 'fast', '주문완료');

insert into orders(o_no, quantity, o_price, o_date, md_no, id, o_status)
values(o_no_seq.nextval, 3, 7500, sysdate, 10102, 'fast', '조리중');

insert into orders(o_no, quantity, o_price, o_date, md_no, id, o_status)
values(o_no_seq.nextval, 4, 12000, sysdate, 10103, 'fast', '장바구니');

insert into orders(o_no, quantity, o_price, o_date, md_no, id, o_status)
values(o_no_seq.nextval, 1, 3500, sysdate, 10104, 'fast', '주문완료');

insert into orders(o_no, quantity, o_price, o_date, md_no, id, o_status)
values(o_no_seq.nextval, 3, 15000, sysdate, 10105, 'fast', '조리중');

insert into orders(o_no, quantity, o_price, o_date, md_no, id, o_status)
values(o_no_seq.nextval, 5, 30000, sysdate, 10106, 'fast', '장바구니');

-- 조인 조건
/*
 * SELECT 컬럼명, 컬럼명....
 * 	FROM A Table명 별칭, B Table명 별칭
 *  WHERE A.컬럼명 = B.컬럼명 --> 조인조건
 */
/*
 * 주문 내역에서 볼 요구 정보
 * 주문번호, 수량, 총금액, 주문일자, 메뉴상세정보 번호, 회원아이디, 주문상태여부
 * hot/ice , size, 메뉴번호, 메뉴명
 * 
 * 별칭 명
 * orders o, menu_detail md, menu m, 
 * member mem, order_status os, 
 * hot_ice_type hi, size_type st
 * 
 * 조인 할 테이블들
 * mem.id=o.id
 * o.o_status= os.o_status
 * md.md_no = o.md_no 
 * md,.hot_ice
 * md.size_type = st.size_type
 * mem.id = 'java'
 */

select * from orders where id = 'java' and o_status = '장바구니';

-- 현재 주문 내역 보기 원본
SELECT o.o_no, o.quantity, o.o_price, o.o_date, o.id, o.o_status, m.m_name, m.m_info, md.hot_ice, md.size_type 
FROM orders o, menu_detail md, menu m, member mem, order_status os, hot_ice_type hi, size_type st 
WHERE mem.id=o.id and o.o_status= os.o_status and md.md_no = o.md_no 
and md.hot_ice = hi.hot_ice and md.size_type = st.size_type and mem.id = 'java';

and os.o_status = '장바구니'

-- 3차 수정 전체 주문 내역 보기
SELECT o.rnum, o.o_no, o.quantity, o.o_price, o.o_date, o.o_status,
      m.m_name, m.m_info, md.hot_ice, md.size_type, s.s_name
from ( select row_number() over(order by o_no desc) rnum,
 o_no, quantity, o_price, o_date, o_status, md_no, id  from orders) o,
 menu_detail md, menu m, store s
WHERE md.md_no = o.md_no and md.m_no=m.m_no and m.s_no=s.s_no and o.id='fast' and o.rnum between 1 and 3 order by o.rnum asc;

-- 3차 수정 현재 주문 내역 보기
SELECT o.rnum, o.o_no, o.quantity, o.o_price, o.o_date, o.o_status,
      m.m_name, m.m_info, md.hot_ice, md.size_type, s.s_name
from ( select row_number() over(order by o_no desc) rnum,
 o_no, quantity, o_price, o_date, o_status, md_no, id  from orders) o,
 menu_detail md, menu m, store s
WHERE md.md_no = o.md_no and md.m_no=m.m_no and m.s_no=s.s_no and o.id='fast' and o.o_status='조리중' order by o.rnum asc;











