package com.fastakeout.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class StoreDAO {
	private static StoreDAO instance=new StoreDAO();
	private DataSource dataSource;
	private StoreDAO(){
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static StoreDAO getInstance(){
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
	/** 지원  [getStoreCategory]
	 * 가맹점 등록을 하기전에 등록 폼에서 
	 * 선택할 수 있는 가맹점 업종을 구하는 멧더ㅡ 
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> getStoreCategory() throws SQLException {
		ArrayList<String> categoryList=new ArrayList<String>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="select cate_name from category";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String category=rs.getString(1);
				categoryList.add(category);
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return categoryList;
	}
	
	
	/** 진호 -[업체등록]
	 * 업체 이미지 파일업로드 후 실행되는 업체등록 메소드. 
	 * 내부적으로 업종등록 메소드를 실행한다.
	 * 
	 * @param svo
	 * @throws SQLException
	 */
	public void registerStore(StoreVO svo) throws SQLException {
	      System.out.println("StoreDAO - registerStore() 1 : 도달");
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         con = dataSource.getConnection();
	         con.setAutoCommit(false);
	         System.out.println("StoreDAO - registerStore() 2 : 커넥션 준비");
	         StringBuilder sb=new StringBuilder();
	         sb.append("INSERT INTO STORE(s_no, s_name, s_address, tel, openday, s_img, id) ");
	         sb.append("VALUES(S_NO_SEQ.nextval, ?, ?, ?, ?, ?, ?)");
	         pstmt = con.prepareStatement(sb.toString());
	         System.out.println("StoreDAO - registerStore() 3 : ps 준비");
	         pstmt.setString(1, svo.getStorename());
	         pstmt.setString(2, svo.getAddress());
	         pstmt.setString(3, svo.getTel());
	         pstmt.setString(4, svo.getOpenDay());
	         pstmt.setString(5, svo.getStoreImgUrl());
	         pstmt.setString(6, svo.getId());
	         int count = pstmt.executeUpdate();
	         System.out.println("StoreDAO - registerStore() 4 : 쿼리 실행, 등록된 업체 개수 count :"+count);
	         System.out.println("StoreDAO - registerStore() 5 : 업체정보 등록 정상종료");
	         pstmt.close();
	         
	         // 등록된 업체의 업체번호 받아오기
	         System.out.println("StoreDAO - registerStore() 6 : 등록된 업체의 업체번호 받아오기");
	         String sql = "SELECT s_no_seq.currval FROM DUAL";
	         pstmt = con.prepareStatement(sql);
	         System.out.println("StoreDAO - registerStore() 7 : ps 준비");
	         
	         rs = pstmt.executeQuery();
	         String storeNo = null;
	         if(rs.next()) {
	        	 storeNo = rs.getString(1);
	         }
	         System.out.println("StoreDAO - registerStore() 8 : 쿼리 실행, 등록된 업체번호 :"+storeNo);
	         pstmt.close();
	         System.out.println("StoreDAO - registerStore() 9 : 업체번호 가져오기 정상종료");
	         
	         // 업종등록
	         registerStoreCategory(storeNo,svo.getCategory(),con);
	         
	         con.commit();
	      }catch(Exception e) {
	         con.rollback();
	         System.out.println("StoreDAO - RollBack");
	         e.printStackTrace();
	         throw e;
	      } finally {
	         closeAll(null, pstmt, con);
	      }
	   }
	
	/**	진호 - [업종등록]
	 * 한 업체의 업종을 DB에 등록하는 메소드.
	 * 한 업체마다 여러가지 업종분류를 가질 수 있기 때문에 내부적으로 반복문을 이용한다. 
	 * 
	 * @param storeNo
	 * @param categories
	 * @param con
	 * @throws SQLException
	 */
	   public void registerStoreCategory(String storeNo, ArrayList<String> categories, Connection con) throws SQLException {
	      System.out.println("StoreDAO - registerStoreCategory() 6 : 도달");
	      try {
	         String sql = "INSERT INTO STORE_CATEGORY(s_no, cate_name) VALUES(?, ?)";
	         for(String str:categories){
	            PreparedStatement pstmt = con.prepareStatement(sql);
	            System.out.println("StoreDAO - registerStoreCategory() 6-1 : 반복문 속 pstmt 생성");
	            pstmt.setInt(1, Integer.parseInt(storeNo));
	            pstmt.setString(2, str);
	            pstmt.executeUpdate();
	            pstmt.close();
	            System.out.println("StoreDAO - registerStoreCategory() 6-2 : 업종등록 반복문 회전 ");
	         }
	         System.out.println("StoreDAO - registerStoreCategory() 7 : 정상종료");
	      }finally{
	         
	      }
	   }
	public void registerMenu(MenuVO mvo) {
		
	}
	
	public void registerMenuDetail(MenuVO mvo) {
		
	}
	
	/**지원 [getStoreList]
	 * getStoreList
	 * 전체 가맹점 리스트를 가져와서 home.jsp 에서 보여준다 
	 * 
	 * @param pb
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<StoreVO> getStoreList(PagingBean pb) throws SQLException {
		System.out.println("StoreDAO - getStoreList() 1 : 도달");
		ArrayList<StoreVO> storeList = new ArrayList<StoreVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			System.out.println("StoreDAO - getStoreList() 2 : 커넥션 준비");
			StringBuilder sb=new StringBuilder();
			sb.append("select s_no, s_name, s_img");
			sb.append(" from ( select row_number() over(order by s_no desc) rnum,");
			sb.append(" s_no, s_name, s_img, s_address, tel, openday from store s) a");
			sb.append(" where a.rnum between ? and ?");
			pstmt = con.prepareStatement(sb.toString());
			System.out.println("StoreDAO - getStoreList() 3 : ps 준비");
			pstmt.setInt(1, pb.getStartRowNumber());
			pstmt.setInt(2, pb.getEndRowNumber());
			rs = pstmt.executeQuery();
			System.out.println("StoreDAO - getStoreList() 4 : 쿼리 실행");
			while (rs.next()) {
				StoreVO store = new StoreVO();
				store.setStoreNo(rs.getString(1));
				store.setStorename(rs.getString(2));
				store.setStoreImgUrl(rs.getString(3));
				storeList.add(store);
			}	
			System.out.println("StoreDAO - 업체리스트 : "+storeList);
			System.out.println("StoreDAO - getStoreList() 5 : 정상종료");
		} finally {
			closeAll(rs, pstmt, con);
		}
		return storeList;
	}
	
	
	
	/** 지원 [countAllStore]
	 *  paging bean 에서 사용할 전체 가맹점 수 를 구하는 메서드
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int countAllStore() throws SQLException {
		System.out.println("StoreDAO - countAllStore() 1 : 도달");
		int count=-1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			System.out.println("StoreDAO - countAllStore() 2 : 커넥션 준비");
			String sql = "select count(*) from store";
			pstmt = con.prepareStatement(sql);
			System.out.println("StoreDAO - countAllStore() 3 : ps 준비");
			rs = pstmt.executeQuery();
			System.out.println("StoreDAO - countAllStore() 4 : 쿼리 조회");
			if(rs.next()) {
				count=rs.getInt(1);
			}
			System.out.println("StoreDAO - countAllStore() 5 : 정상종료");
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}
	/**  지현 - 가맹업주가 본인의 가맹업 정보를 가져오는 메서드
	 *    업체관리.jsp 에서 사용되며, id 로 store 의 정보를 
	 *    storeVO에 담아서 return 한다
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public StoreVO getStoreVO(String id) throws SQLException{
		StoreVO vo=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("select s_no, s_name, s_address, tel, openday, s_img from store where id=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new StoreVO();
				vo.setId(id); 											// 가맹점주 id
				vo.setStoreNo(rs.getString(1)); 		// 가맹점no
				vo.setStorename(rs.getString(2)); 	// 가맹점이름
				vo.setAddress(rs.getString(3));		// 가맹점 주소
				vo.setTel(rs.getString(4));					// 가맹점 전화번호
				vo.setOpenDay(rs.getString(5));		// 가맹점 openDay	
				vo.setStoreImgUrl(rs.getString(6));// 가맹점 img url
				vo.setCategory(getStoreCategory(rs.getString(1)));	// 가맹점 업종 리스트
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
	/**  지현 - 가맹점의 업종 분류 리스트를 반환하는 메서드
	 * 	업체관리.jsp 에서 id 별 store 정보를 가져올 때, 
	 * 	업종 리스트를 받을 때, 사용한다.
	 * 	getStoreVO(String id) 에서 호출한다
 	 * 
	 * @param storeNo
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> getStoreCategory(String storeNo) throws SQLException{
		ArrayList<String> storeCategory=new ArrayList<String> ();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("select CATE_NAME from STORE_CATEGORY where s_no=?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, storeNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				storeCategory.add(rs.getString(1));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return storeCategory;
	}
}
