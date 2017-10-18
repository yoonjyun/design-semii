package com.fastakeout.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class MenuDAO {
	private static MenuDAO instance=new MenuDAO();
	private DataSource dataSource;
	private MenuDAO(){
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static MenuDAO getInstance(){
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
	
	/** 지원 [getMenuList]
	 * 가맹점 별 메뉴리스트
	 * 가맹점 번호를 매개변수로 하여 해당 가맹점의 전체 메뉴를 가져온다 
	 * 
	 * @param pb
	 * @param storeNo
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<MenuVO> getMenuList(PagingBean pb, String storeNo ) throws SQLException {
		ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = dataSource.getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select m_no, m_name,s_no, m_img");
			sb.append(" from ( select row_number() over(order by m_no desc) rnum,");
			sb.append(" m_no, m_name, s_no, m_img from menu) a");
			sb.append(" where s_no=? and a.rnum between ? and ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, Integer.parseInt(storeNo));
			pstmt.setInt(2, pb.getStartRowNumber());
			pstmt.setInt(3, pb.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MenuVO vo= new MenuVO();
				vo.setMenuNo(rs.getString(1));
				vo.setMenuName(rs.getString(2));
				vo.setStoreNo(storeNo);
				vo.setMenuImgUrl(rs.getString(4));
				menuList.add(vo);
			}
			return menuList;
		} finally {
			closeAll(rs, pstmt, con);
		}
	}
	
	
	/** 지원 [countAllMenu]
	 *  paging bean 에서 사용할 
	 *  해당 가맹점이 보유하고 있는 메뉴의 개수를 구하는 메서드
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int countAllMenu(String storeNo) throws SQLException {
		System.out.println("MenuDAO - countAllMenu() 1 : 도달");
		int count=-1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			System.out.println("MenuDAO - countAllMenu() 2 : 커넥션 준비");
			String sql = "select count(*) from menu where s_no=?";
			pstmt = con.prepareStatement(sql);
			System.out.println("MenuDAO - countAllMenu() 3 : ps 준비");
			pstmt.setString(1, storeNo);
			rs = pstmt.executeQuery();
			System.out.println("MenuDAO - countAllMenu() 4 : 쿼리 조회");
			if(rs.next()) {
				count=rs.getInt(1);
			}
			System.out.println("MenuDAO - countAllMenu() 5 : 정상종료, count = "+count);
			return count;
		} finally {
			closeAll(rs, pstmt, con);
		}
	}
	
	/** 지원 [getMenuDetail]
	 * 가맹점의 메뉴별 상세옵션 
	 * 메뉴리스트에서 메뉴를 클릭하면 
	 * 해당 메뉴에 관한 상세 옵션정보를 가져오는 메서드 
	 * 메뉴번호를 매개변수로 하여 해당 메뉴번호에 관한 메뉴상세 리스트를 가져온다 
	 * 
	 * @param menuNo
	 * @return
	 * @throws SQLException
	 */
	
	public ArrayList<MenuVO> getMenuDetail(String menuNo) throws SQLException {
		System.out.println("MenuDAO - getMemberInfo() 1 : 도달");
		System.out.println("MenuDAO - getMemberInfo() 1 : 도달");
		System.out.println("MenuDAO - getMemberInfo() 2 : 커넥션 준비");
		System.out.println("MenuDAO - getMemberInfo() 3 : ps 준비");
		System.out.println("MenuDAO - getMemberInfo() 4 : 쿼리 조회");
		System.out.println("MenuDAO - getMemberInfo() 5 : 정상종료");
		ArrayList<MenuVO> mdList = new ArrayList<MenuVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			System.out.println(menuNo);
			con = dataSource.getConnection();
			System.out.println("MenuDAO - getMemberInfo() 2 : 커넥션 준비");
			StringBuilder sb=new StringBuilder();
			sb.append("select md.md_no, md.md_price, md.hot_ice, md.size_type,");
			sb.append("  m.m_no, m.m_name, m.m_info");
			sb.append(" from menu_detail md, menu m");
			sb.append(" where md.m_no=m.m_no and md.m_no=?");
			pstmt = con.prepareStatement(sb.toString());
			System.out.println("MenuDAO - getMemberInfo() 3 : ps 준비");
			pstmt.setInt(1,Integer.parseInt(menuNo));
			rs = pstmt.executeQuery();
			System.out.println("MenuDAO - getMemberInfo() 4 : 쿼리 조회");
			while (rs.next()) {
				MenuDetailVO md=new MenuDetailVO();
				md.setMenuDetailNo(rs.getString(1));
				md.setMenuPrice(rs.getInt(2));
				md.setHotIce(rs.getString(3));
				md.setSize(rs.getString(4));
				
				MenuVO menu= new MenuVO();
				menu.setMenuNo(rs.getString(5));
				menu.setMenuName(rs.getString(6));
				menu.setMenuInfo(rs.getString(7));
				menu.setMenuDetailVO(md);

				System.out.println(menu);
				mdList.add(menu);
			}
			System.out.println("MenuDAO - 메뉴상세리스트 : "+mdList);
			System.out.println("MenuDAO - getMemberInfo() 5 : 정상종료");
		} finally {
			closeAll(rs, pstmt, con);
		}
		return mdList;
	}
	public MemberVO getMemberInfo(String id) {
		System.out.println("MenuDAO - getMemberInfo() 1 : 도달");
		System.out.println("MenuDAO - getMemberInfo() 2 : 커넥션 준비");
		System.out.println("MenuDAO - getMemberInfo() 3 : ps 준비");
		System.out.println("MenuDAO - getMemberInfo() 4 : 쿼리 조회");
		System.out.println("MenuDAO - getMemberInfo() 5 : 정상종료");
		return null;
	}
	public void registerMenu(MenuVO mvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			System.out.println("MenuDAO 메뉴 등록 진입 1");
			String sql = "insert into menu(m_no,s_no,m_info,m_name,m_img) values(m_no_seq.nextval,?,?,?,?)";
			System.out.println("MenuDAO 메뉴 등록 진입 2");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mvo.getStoreNo());
			pstmt.setString(2, mvo.getMenuInfo());
			pstmt.setString(3, mvo.getMenuName());
			pstmt.setString(4, mvo.getMenuImgUrl());
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("MenuDAO 메뉴 등록 진입 3");
			sql="select m_no_seq.currval from dual";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				mvo.setMenuNo(rs.getString(1));	
			}
			pstmt.close();
			
			System.out.println("MenuDAO 메뉴 등록 진입 4");
			sql = "insert into menu_detail(md_no, md_price, hot_ice, size_type, m_no) values(md_no_seq.nextval, ?, ?, ?, ?)";
			System.out.println("MenuDAO 메뉴 등록 진입 5");
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mvo.getMenuDetailVO().getMenuPrice());
			pstmt.setString(2, mvo.getMenuDetailVO().getHotIce());
			pstmt.setString(3, mvo.getMenuDetailVO().getSize());
			pstmt.setString(4, mvo.getMenuNo());
			pstmt.executeUpdate();
			System.out.println("MenuDAO 메뉴 등록 진입 6");
		}finally {
			closeAll(rs, pstmt, con);
		}
	}
	public MenuVO getStoreInfo(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MenuVO vo = new MenuVO();
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select s_no, s_name from store where id=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setStoreNo(rs.getString(1));
				vo.setStoreName(rs.getString(2));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
}
