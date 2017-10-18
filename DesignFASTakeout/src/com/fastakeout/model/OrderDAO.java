package com.fastakeout.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class OrderDAO {
	private static OrderDAO instance=new OrderDAO();
	private DataSource dataSource;
	private OrderDAO(){
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static OrderDAO getInstance(){
		return instance;
	}
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException{
		closeAll(null, pstmt,con);
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,
			Connection con) throws SQLException{
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	
	/** 지원 [장바구니 추가]
	    * 장바구니에 주문을 추가한다. 
	    * 
	    * @param ovo
	    * @throws SQLException
	    */
	   public void cartAdd(OrderVO ovo) throws SQLException {
	      System.out.println("OrderDAO - cartAdd() 1 : 도달");
	      Connection con=null;
	      PreparedStatement pstmt=null;
	      try {
	         con = dataSource.getConnection();
	         System.out.println("OrderDAO - cartAdd() 2 : 커넥션 연결");
	         con.setAutoCommit(false); 
	         StringBuilder sb=new StringBuilder();
	         // quantity, o_price, md_no, id (가져와야할 정보)
	         sb.append("INSERT INTO ORDERS(o_no, quantity, o_price, o_date, md_no, id, o_status)");
	         sb.append(" VALUES(o_no_seq.nextval, ?, ?, sysdate, ?, ?, '장바구니')");
	         pstmt=con.prepareStatement(sb.toString());
	         System.out.println("OrderDAO - cartAdd() 3 : ps 준비");
	         System.out.println("OrderDAO - quantity : "+ovo.getQuantity()+", OrderPrice :"+ovo.getOrderPrice());
	         System.out.println("OrderDAO - menuDetailNo : "+Integer.parseInt(ovo.getMenuVO().getMenuDetailVO().getMenuDetailNo()));
	         System.out.println("OrderDAO - memberId : "+ovo.getMemberId());
	         pstmt.setInt(1, ovo.getQuantity());
	         pstmt.setInt(2, ovo.getOrderPrice());
	         pstmt.setInt(3, Integer.parseInt(ovo.getMenuVO().getMenuDetailVO().getMenuDetailNo()));
	         pstmt.setString(4, ovo.getMemberId());
	         int count = pstmt.executeUpdate();
	         System.out.println("OrderDAO - cartAdd() 4 : 쿼리 조회. 삽입된 주문 개수 : "+count);
	         con.commit();
	      } catch(Exception e) {
	         System.out.println("OrderDAO - cartAdd() 5 : rollback ");
	         e.printStackTrace();
	         con.rollback();
	      } finally {
	         closeAll(pstmt, con);
	      }
	   }
	   
	   /** 지원 [장바구니 삭제]
	    * 장바구니에 있는 상품의 orderNo를 받아 삭제한다.
	    * 
	    * @param orderNo
	    * @throws SQLException
	    */
	   public void cartDelete(String orderNo, String id) throws SQLException {
	      System.out.println("OrderDAO - cartDelete() 1 : 도달");
	      Connection con=null;
	      PreparedStatement pstmt=null;
	      try {
	         con = dataSource.getConnection();
	         System.out.println("OrderDAO - cartDelete() 2 : 커넥션 연결");
	         con.setAutoCommit(false); 
	         String sql="DELETE FROM ORDERS WHERE o_no=? and id=? and o_status = '장바구니' ";
	         pstmt=con.prepareStatement(sql);
	         System.out.println("OrderDAO - cartDelete() 3 : ps 준비");
	         System.out.println("OrderDAO - orderNo : "+orderNo);
	         pstmt.setString(1, orderNo);
	         pstmt.setString(2, id);
	         int count = pstmt.executeUpdate();
	         System.out.println("OrderDAO - cartDelete() 4 : 쿼리 executeUpdate, 삭제된 주문 개수 : "+count);
	         con.commit();
	      } catch(Exception e) {
	         System.out.println("OrderDAO - cartDelete() 5 : rollback ");
	         e.printStackTrace();
	         con.rollback();
	      } finally {
	         closeAll(pstmt, con);
	      }
	   }
	public ArrayList<OrderVO> cartList(String id, PagingBean pb) throws SQLException {
		System.out.println("OrderDAO - cartList() 1 : 도달");
		ArrayList<OrderVO> cartList=new ArrayList<OrderVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con = dataSource.getConnection();
			System.out.println("OrderDAO - cartList() 2 : 커넥션 연결");
			StringBuilder sb=new StringBuilder();
			sb.append("SELECT o.o_no, o.quantity, o.o_price, to_char(o.o_date), o.o_status,");
			sb.append(" m.m_name, m.m_info, md.hot_ice, md.size_type, s.s_no, s.s_name");
			sb.append(" from (SELECT row_number() over(order by o_no desc) rnum,");
			sb.append(" o_no, quantity, o_price, o_date, o_status, md_no");
			sb.append(" FROM orders WHERE id=?) o,");
			sb.append(" menu_detail md, menu m, store s");
			sb.append(" WHERE md.md_no = o.md_no and md.m_no=m.m_no and m.s_no=s.s_no");
			sb.append(" and rnum between ? and ? order by o.o_no desc");
			pstmt=con.prepareStatement(sb.toString());
			System.out.println("OrderDAO - cartList() 3 : ps 준비");
			System.out.println("아이디:"+id+", 스타트로우넘버 :"+pb.getStartRowNumber()+", 엔드로우넘버:"+pb.getEndRowNumber());
			pstmt.setString(1, id);
			pstmt.setInt(2, pb.getStartRowNumber());
			pstmt.setInt(3, pb.getEndRowNumber());
			rs=pstmt.executeQuery();
			System.out.println("OrderDAO - cartList() 4 : 쿼리 조회");
			while(rs.next()) {
				OrderVO cart=new OrderVO();
				cart.setOrderNo(rs.getString(1));
				cart.setQuantity(rs.getInt(2));
				cart.setOrderPrice(rs.getInt(3));
				cart.setOrderDate(rs.getString(4));
				cart.setOrderStatus(rs.getString(5));
				
				MenuVO menu=new MenuVO();
				menu.setMenuName(rs.getString(6));
				menu.setMenuInfo(rs.getString(7));
				//상점 정보
				menu.setStoreNo(rs.getString(10));
				menu.setStoreName(rs.getString(11));
				
				MenuDetailVO menuDetail=new MenuDetailVO();
				menuDetail.setHotIce(rs.getString(8));
				menuDetail.setSize(rs.getString(9));
				
				menu.setMenuDetailVO(menuDetail);
				cart.setMenuVO(menu);
				cartList.add(cart);
				System.out.println("OrderDAO - cartList() 5 : 반복문 회전");
			}
		} finally {
			closeAll(rs,pstmt, con);
		}
		System.out.println("OrderDAO - 장바구니 리스트 : "+cartList);
		return cartList;
	}
	/** 지현 - 주문받은 리스트를 가져오는 메서드
	 * 주문 결제시, session 의 아이디로 
	 * 주문상태 = '장바구니' 인 orderVO 를 ArrayList로 반환받는다  
	 * 주문번호와 결제금액, 가맹점 번호와 가맹점 아이디를 갖고있다.
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	
	public ArrayList<OrderVO> getOrderList(String id) throws SQLException{
		System.out.println("OrderDAO - getOrderList() 1 : 도달");
		ArrayList<OrderVO> orderList=new ArrayList<OrderVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			System.out.println("OrderDAO - getOrderList() 2 : 커넥션 준비");
			StringBuilder sql=new StringBuilder();
			sql.append("select O.O_NO, O.O_PRICE, S.S_NO, S.ID");
			sql.append(" from ORDERS O, MENU_DETAIL D, MENU M, STORE S");
			sql.append(" where O.id=? and O.O_STATUS='장바구니'");
			sql.append(" and D.MD_NO=O.MD_NO and M.M_NO=D.M_NO and S.S_NO=M.S_NO");
			pstmt=con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - getOrderList() 3 : ps 준비");
			pstmt.setString(1, id);
			//pstmt.setString(2, "장바구니");
			rs=pstmt.executeQuery();
			System.out.println("OrderDAO - getOrderList() 4 : 쿼리 조회");
			while(rs.next()) {
				orderList.add(new OrderVO(rs.getString(1), rs.getInt(2), new MenuVO(rs.getString(3), rs.getString(4))));
			}
			System.out.println("OrderDAO - getOrderList() 5 : 정상종료, 주문내역:"+orderList);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return orderList;
	}
	
	/** 지현 - 잔액 확인하는 메서드
	 * id로 balance를 확인한다. 
	 * 주문결제 할 때 이용한다.
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int getBalance(String id) throws SQLException {
		System.out.println("OrderDAO - getBalance() 1 : 도달");
		int balance=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			System.out.println("OrderDAO - getBalance() 2 : 커넥션 준비");
			StringBuilder sql=new StringBuilder();
			sql.append("select balance from member where id=?");
			pstmt=con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - getBalance() 3 : ps 준비");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			System.out.println("OrderDAO - getBalance() 4 : 쿼리 조회");
			if(rs.next()) {
				balance=rs.getInt(1);
			}
			System.out.println("OrderDAO - getBalance() 5 : 정상종료, 잔액 :"+balance);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return balance;
	}
	/** 지현 - 잔액 업데이트하는 메서드
	 * 잔액확인 후, 고객의 잔액은 orderPrice 만큼 마이너스,
	 * 점주의 잔액은 orderPrice 만큼 플러스 해준다.
	 * 
	 * @param id
	 * @param orderPrice
	 * @throws SQLException
	 */
	public void balanceUpdates(String id, int orderPrice) throws SQLException {
		System.out.println("OrderDAO - balanceUpdates() 1 : 도달");
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			System.out.println("OrderDAO - balanceUpdates() 2 : 커넥션 준비");
			StringBuilder sql=new StringBuilder();
			sql.append("update member set balance=balance+? where id=?");
			pstmt=con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - balanceUpdates() 3 : ps 준비");
			pstmt.setInt(1, orderPrice);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			System.out.println("OrderDAO - balanceUpdates() 4 : 정상종료, 쿼리 실행");
		}finally {
			closeAll(pstmt, con);
		}
	}
	
	/**현민 - [현재 주문 내역 보기]
	 * 해당 회원의 아이디를 받아와 주문내역에서 확인할 정보를 가져온다.
	 * 리스트에 담을 정보는 
	 * 주문번호, 수량, 총금액, 주문일자, 메뉴상세정보 번호, 회원아이디, 주문상태여부
	 * hot/ice , size, 메뉴번호, 메뉴명 이며 
	 * 이 메소드에서는 조리중인 상태의 메뉴들만 보여주면 되기 때문에 
	 * 조건문을 걸어 조리중인 정보들만 담아 보낸다.
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<OrderVO> getCustomerCurrentOrderList(String id) throws SQLException {
		System.out.println("OrderDAO - getCustomerCurrentOrderList() 1 : 도달");
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO ovo = null;
		MenuVO mvo = null;
		MenuDetailVO mdvo = null;
		try {
			con = dataSource.getConnection();
			System.out.println("OrderDAO - getCustomerCurrentOrderList() 2 : 커넥션 준비");
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o.rnum, o.o_no, o.quantity, o.o_price, o.o_date, o.o_status, ");
			sql.append("m.m_name, m.m_info, md.hot_ice, md.size_type, s.s_name ");
			sql.append("from ( select row_number() over(order by o_no desc) rnum, ");
			sql.append("o_no, quantity, o_price, o_date, o_status, md_no, id  from orders) o, ");
			sql.append(" menu_detail md, menu m, store s ");
			sql.append("WHERE md.md_no = o.md_no and md.m_no=m.m_no and m.s_no=s.s_no ");
			sql.append("and o.id=? order by o.rnum asc ");
			pstmt = con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - getCustomerCurrentOrderList() 3 : ps 준비");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("OrderDAO - getCustomerCurrentOrderList() 4 : 쿼리 조회");
			while(rs.next()) {
				if(rs.getString(6).equals("조리중")) {
					ovo = new OrderVO();
					ovo.setOrderNo(rs.getString(2)); 	//주문 번호
					ovo.setQuantity(rs.getInt(3));		//수량
					ovo.setOrderPrice(rs.getInt(4));	//총 금액
					ovo.setOrderDate(rs.getString(5));	//주문일자
					ovo.setOrderStatus(rs.getString(6));//주문 상태여부
					mvo = new MenuVO();
					mvo.setMenuName(rs.getString(7));	//메뉴명
					mvo.setMenuInfo(rs.getString(8));	//메뉴설명
					mvo.setStoreName(rs.getString(11)); //가맹점 이름
					mdvo = new MenuDetailVO();
					mdvo.setHotIce(rs.getString(9));	//hot_ice
					mdvo.setSize(rs.getString(10));		//size
					mvo.setMenuDetailVO(mdvo);
					ovo.setMenuVO(mvo);
					System.out.println("OrderDAO - 가져온 주문: "+ovo);
					System.out.println(ovo.toString());
					list.add(ovo);
				}
			}
			System.out.println("OrderDAO - 현재주문내역 : "+list);
			System.out.println("OrderDAO - getCustomerCurrentOrderList() 5 : 정상종료");
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	/**현민 - [전체 주문 내역 보기]
	 * 해당 회원의 아이디를 받아와 주문내역에서 확인할 정보를 가져온다.
	 * 리스트에 담을 정보는 
	 * 주문번호, 수량, 총금액, 주문일자, 메뉴상세정보 번호, 회원아이디, 주문상태여부
	 * hot/ice , size, 메뉴번호, 메뉴명 이며 
	 * 해당 회원이 현재 주문한 내역과 장바구니에 있는 메뉴들과 과거에 주문 했었던 모든 내역들을
	 * 담는 메소드 이다.
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<OrderVO> getCustomerAllOrderList(PagingBean pb, String id) throws SQLException {
		System.out.println("OrderDAO - getCustomerAllOrderList() 1 : 도달");
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO ovo = null;
		MenuVO mvo = null;
		MenuDetailVO mdvo = null;
		try {
			con = dataSource.getConnection();
			System.out.println("OrderDAO - getCustomerAllOrderList() 2 : 커넥션 준비");
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o.rnum, o.o_no, o.quantity, o.o_price, o.o_date, o.o_status, ");
			sql.append("m.m_name, m.m_info, md.hot_ice, md.size_type, s.s_name ");
			sql.append("from ( select row_number() over(order by o_no desc) rnum, ");
			sql.append("o_no, quantity, o_price, o_date, o_status, md_no, id  from orders) o, ");
			sql.append(" menu_detail md, menu m, store s ");
			sql.append("WHERE md.md_no = o.md_no and md.m_no=m.m_no and m.s_no=s.s_no ");
			sql.append("and o.id=? and o.rnum between ? and ? order by o.rnum asc ");
			pstmt = con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - getCustomerAllOrderList() 3 : ps 준비");
			pstmt.setString(1, id);
			pstmt.setInt(2, pb.getStartRowNumber());
			pstmt.setInt(3, pb.getEndRowNumber());
			rs = pstmt.executeQuery();
			System.out.println("OrderDAO - getCustomerAllOrderList() 4 : 쿼리 조회");
			while(rs.next()) {
				ovo = new OrderVO();
				ovo.setOrderNo(rs.getString(2)); 	//주문 번호
				ovo.setQuantity(rs.getInt(3));		//수량
				ovo.setOrderPrice(rs.getInt(4));	//총 금액
				ovo.setOrderDate(rs.getString(5));	//주문일자
				ovo.setOrderStatus(rs.getString(6));//주문 상태여부
				
				mvo = new MenuVO();
				mvo.setMenuName(rs.getString(7));	//메뉴명
				mvo.setMenuInfo(rs.getString(8));	//메뉴설명
				mvo.setStoreName(rs.getString(11)); //가맹점 이름
				
				mdvo = new MenuDetailVO();
				mdvo.setHotIce(rs.getString(9));	//hot_ice
				mdvo.setSize(rs.getString(10));		//size

				mvo.setMenuDetailVO(mdvo);
				ovo.setMenuVO(mvo);
				System.out.println("OrderDAO - ovo :"+ovo);
				list.add(ovo);
			}
			System.out.println("OrderDAO - 회원의 전체주문내역 : "+list);
			System.out.println("OrderDAO - getCustomerAllOrderList() 5 : 정상종료");
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	/** 지현 - 가맹점주 현재 주문 내역 보기 
	 * 주문상태 = '조리중' 과 가맹점주 id로 select를 해서,
	 * orderVO로 받아서, ArrayList로 반환한다. 
	 * 
	 * 
	 * @param pb
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<OrderVO> getOwnerCurrentOrderList(PagingBean pb, String id) throws SQLException {
		System.out.println("OrderDAO - getCustomerAllOrderList() 1 : 도달");
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO ovo = null;
		MenuVO mvo = null;
		MenuDetailVO mdvo = null;
		try {
			con = dataSource.getConnection();
			System.out.println("OrderDAO - getCustomerAllOrderList() 2 : 커넥션 준비");
			StringBuilder sql = new StringBuilder();
			sql.append("select rnum, o_no, quantity, id, o_price, o_date, o_status, m_name, ");
			sql.append("hot_ice, size_type  from ( select row_number() over(order by o_no desc) rnum , ");
			sql.append("o.o_no, o.quantity, o.id, o.o_price, o.o_date, o.o_status, me.m_name, d.hot_ice, d.size_type  ");
			sql.append("from orders o, store s, member m, menu me, menu_detail d ");
			sql.append("where m. id = ? and o_status='조리중' and m.id=s.id and s.s_no = me.s_no ");
			sql.append("and me.m_no = d.m_no and d.md_no=o.md_no )  ");
			sql.append("where rnum between ? and ? order by rnum asc ");
			pstmt = con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - getCustomerAllOrderList() 3 : ps 준비");
			pstmt.setString(1, id);
			pstmt.setInt(2, pb.getStartRowNumber());
			pstmt.setInt(3, pb.getEndRowNumber());
			rs = pstmt.executeQuery();
			System.out.println("OrderDAO - getCustomerAllOrderList() 4 : 쿼리 조회");
			while(rs.next()) {
				ovo = new OrderVO();
				ovo.setRnum(rs.getInt(1)); //rnum
				ovo.setOrderNo(rs.getString(2)); 	//주문 번호
				ovo.setQuantity(rs.getInt(3));		//수량
				ovo.setMemberId(rs.getString(4));   //주문자 아이디
				ovo.setOrderPrice(rs.getInt(5));	//총 금액
				ovo.setOrderDate(rs.getString(6));	//주문일자
				ovo.setOrderStatus(rs.getString(7));//주문 상태여부
				mvo = new MenuVO();
				mvo.setMenuName(rs.getString(8));	//메뉴명
				
				mdvo = new MenuDetailVO();
				mdvo.setHotIce(rs.getString(9));	//hot_ice
				mdvo.setSize(rs.getString(10));		//size

				mvo.setMenuDetailVO(mdvo);
				ovo.setMenuVO(mvo);
				System.out.println("OrderDAO - ovo : "+ovo);
				list.add(ovo);
			}
			System.out.println("OrderDAO - 가맹주 현재 주문내역 :"+list);
			System.out.println("OrderDAO - getCustomerAllOrderList() 5 : 정상종료");
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	/** 지현 - 가맹점주 전체 주문 내역 보기 
	 * 가맹점주 id로 select를 해서,
	 * orderVO로 받아서, ArrayList로 반환한다. 
	 * 
	 * 
	 * @param pb
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<OrderVO> getOwnerAllOrderList(PagingBean pb,String id) throws SQLException {
		System.out.println("OrderDAO - getOwnerAllOrderList() 1 : 도달");
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO ovo = null;
		MenuVO mvo = null;
		MenuDetailVO mdvo = null;
		try {
			con = dataSource.getConnection();
			System.out.println("OrderDAO - getOwnerAllOrderList() 2 : 커넥션 준비");
			StringBuilder sql = new StringBuilder();
			sql.append("select rnum, o_no, quantity, id, o_price, o_date, o_status, m_name, ");
			sql.append("hot_ice, size_type  from ( select row_number() over(order by o_no desc) rnum , ");
			sql.append("o.o_no, o.quantity, o.id, o.o_price, o.o_date, o.o_status, me.m_name, d.hot_ice, d.size_type  ");
			sql.append("from orders o, store s, member m, menu me, menu_detail d ");
			sql.append("where m. id = ? and m.id=s.id and s.s_no = me.s_no ");
			sql.append("and me.m_no = d.m_no and d.md_no=o.md_no )  ");
			sql.append("where rnum between ? and ? order by rnum asc ");
			pstmt = con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - getOwnerAllOrderList() 3 : ps 준비");
			pstmt.setString(1, id);
			pstmt.setInt(2, pb.getStartRowNumber());
			pstmt.setInt(3, pb.getEndRowNumber());
			rs = pstmt.executeQuery();
			System.out.println("OrderDAO - getOwnerAllOrderList() 4 : 쿼리 조회");
			while(rs.next()) {
				ovo = new OrderVO();
				ovo.setRnum(rs.getInt(1)); //rnum
				ovo.setOrderNo(rs.getString(2)); 	//주문 번호
				ovo.setQuantity(rs.getInt(3));		//수량
				ovo.setMemberId(rs.getString(4));   //주문자 아이디
				ovo.setOrderPrice(rs.getInt(5));	//총 금액
				ovo.setOrderDate(rs.getString(6));	//주문일자
				ovo.setOrderStatus(rs.getString(7));//주문 상태여부
				
				mvo = new MenuVO();
				mvo.setMenuName(rs.getString(8));	//메뉴명
				
				mdvo = new MenuDetailVO();
				mdvo.setHotIce(rs.getString(9));	//hot_ice
				mdvo.setSize(rs.getString(10));		//size

				mvo.setMenuDetailVO(mdvo);
				ovo.setMenuVO(mvo);
				System.out.println("orderVOList"+ovo.getOrderNo());
				list.add(ovo);
			}
			System.out.println("OrderDAO - 가맹점주 전체 주문내역 : "+list);
			System.out.println("OrderDAO - getOwnerAllOrderList() 5 : 정상종료");
		}finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	/** 지현 - 주문상태변경 
	 * OrderVO 객체와 orderStatus 를 매개변수로 받아, 
	 * 주문받은 OrderVO객체의 주문 상태를 변경해준다. 
	 * 장바구니 -> 조리중 (주문 결제시 )
	 * 조리중 -> 주문완료 (주문완료 처리시)
	 * 
	 * @param vo
	 * @param status
	 * @throws SQLException
	 */
	public int changeOrderStatus(String orderNo, String status) throws SQLException {
		System.out.println("OrderDAO - getOwnerAllOrderList() 1 : 도달");
		Connection con=null;
		PreparedStatement pstmt=null;
		int count = -1;
		try {
			con=dataSource.getConnection();
			System.out.println("OrderDAO - getOwnerAllOrderList() 2 : 커넥션 준비");
			StringBuilder sql=new StringBuilder();
			sql.append("update ORDERS set O_STATUS=? where O_NO=?");
			pstmt=con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - getOwnerAllOrderList() 3 : ps 준비");
			pstmt.setString(1, status);
			pstmt.setString(2, orderNo);
			count = pstmt.executeUpdate();
			System.out.println("OrderDAO - getOwnerAllOrderList() 4 : 정상종료, 쿼리 실행");
		}finally {
			closeAll(pstmt, con);
		}
		return count;
	}
	/** 지현 - 전체주문내역에서 총 카운트를 가져오는 메서드
	 *  
	 *  전체주문내역 보기에서 paging bean 적용할때 필요
	 *  id로 count(*)를 가져온다
	 * 
	 * @param memberId
	 * @return
	 * @throws SQLException
	 */
	public int getCountAllOrder(String memberId) throws SQLException {
		System.out.println("OrderDAO - getCountAllOrder() 1 : 도달");
		int count=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			System.out.println("OrderDAO - getCountAllOrder() 2 : 커넥션 준비");
			StringBuilder sql=new StringBuilder();
			sql.append("select count(*) from orders where id=?");
			pstmt=con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - getCountAllOrder() 3 : ps 준비");
			pstmt.setString(1, memberId);
			rs=pstmt.executeQuery();
			System.out.println("OrderDAO - getCountAllOrder() 4 : 쿼리 조회");
			if(rs.next()) {
				count=rs.getInt(1);
			}
			System.out.println("OrderDAO - getCountAllOrder() 5 : 정상종료, count :"+count);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
	
	
	/** 지원 - [장바구니에 있는 총 상품수를  가져오는 메서드]
	 *  
	 *  order table에서 
	 *  회원 id에 따라 o_status가 '장바구니'인 것을 가져온다
	 * 
	 * @param memberId
	 * @return
	 * @throws SQLException
	 */
	public int countAllCart(String memberId) throws SQLException {
		System.out.println("OrderDAO - countAllCart() 1 : 도달");
		int count=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			System.out.println("OrderDAO - countAllCart() 2 : 커넥션 준비");
			StringBuilder sql=new StringBuilder();
			sql.append("select count(*) from orders where id=? and o_status='장바구니'");
			pstmt=con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - countAllCart() 3 : ps 준비");
			pstmt.setString(1, memberId);
			rs=pstmt.executeQuery();
			System.out.println("OrderDAO - countAllCart() 4 : 쿼리 조회");
			if(rs.next()) {
				count=rs.getInt(1);
			}
			System.out.println("OrderDAO - countAllCart() 5 : 정상종료, count :"+count);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
	/**지현 - 전체주문내역에서 판매자 id로 총 카운트를 가져오는 메서드
	 * 
	 * owner 전체주문내역 보기에서 paging bean 적용할때 필요
	 *  id로 count(*)를 가져온다
	 * 
	 * @param memberId
	 * @return
	 * @throws SQLException
	 */
	public int getCountAllOrderOwner(String memberId) throws SQLException {
		System.out.println("OrderDAO - getCountAllOrderOwner() 1 : 도달");
		int count=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			System.out.println("OrderDAO - getCountAllOrderOwner() 2 : 커넥션 준비");
			StringBuilder sql=new StringBuilder();
			sql.append("select count(*) ");
			sql.append(" from orders o, menu_detail d, menu m, store s");
			sql.append(" where s.id=? and o.md_no=d.md_no");
			sql.append(" and d.m_no=m.m_no and m.s_no=s.s_no");
			pstmt=con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - getCountAllOrderOwner() 3 : ps 준비");
			pstmt.setString(1, memberId);
			rs=pstmt.executeQuery();
			System.out.println("OrderDAO - getCountAllOrderOwner() 4 : 쿼리 조회");
			if(rs.next()) {
				count=rs.getInt(1);
			}
			System.out.println("OrderDAO - getCountAllOrderOwner() 5 : 정상종료, count:"+count);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
	/**지현 - 전체주문내역에서 판매자 id로 현재 조리중인 주문의 
	 *  총 카운트를 가져오는 메서드
	 * 
	 * owner 현재주문내역 보기에서 paging bean 적용할때 필요
	 *  id로 count(*)를 가져온다
	 * 
	 * @param memberId
	 * @return
	 * @throws SQLException
	 */
	public int getCountCurrentOrderOwner(String memberId) throws SQLException {
		System.out.println("OrderDAO - getCountCurrentOrderOwner() 1 : 도달");
		int count=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			System.out.println("OrderDAO - getCountCurrentOrderOwner() 2 : 커넥션 준비");
			StringBuilder sql=new StringBuilder();
			sql.append("select count(*) ");
			sql.append(" from orders o, menu_detail d, menu m, store s");
			sql.append(" where s.id=? and o.o_status='조리중' and o.md_no=d.md_no");
			sql.append(" and d.m_no=m.m_no and m.s_no=s.s_no");
			pstmt=con.prepareStatement(sql.toString());
			System.out.println("OrderDAO - getCountCurrentOrderOwner() 3 : ps 준비");
			pstmt.setString(1, memberId);
			rs=pstmt.executeQuery();
			System.out.println("OrderDAO - getCountCurrentOrderOwner() 4 : 쿼리 조회");
			if(rs.next()) {
				count=rs.getInt(1);
			}
			System.out.println("OrderDAO - getCountCurrentOrderOwner() 5 : 정상종료, count:"+count);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
}
