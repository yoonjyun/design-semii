package com.fastakeout.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class MemberDAO {
	private static MemberDAO instance=new MemberDAO();
	
	private DataSource dataSource;
	private MemberDAO(){
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static MemberDAO getInstance(){
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
	
	/** 진호 - [아이디 중복확인]
	 * 회원가입시 아이디 "중복확인"버튼을 눌렀을 때 동작하는 메소드.
	 * 아이디가 중복된다면 false를 반환하고 ( useable = false )
	 * 중복되지 않으면 true를 반환한다. ( useable = true )
	 * 
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public boolean checkDuplicateId(String id) throws SQLException {
		System.out.println("MemberDAO - checkDuplicateId() 1 : 도달");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean useable = false;
		try {
			con = dataSource.getConnection();
			System.out.println("MemberDAO - checkDuplicateId() 2 : 커넥션 생성");
			String sql = "SELECT count(id) from MEMBER WHERE id=?";
			ps = con.prepareStatement(sql);
			System.out.println("MemberDAO - checkDuplicateId() 3 : ps 준비");
			ps.setString(1, id);
			rs = ps.executeQuery();
			System.out.println("MemberDAO - checkDuplicateId() 4 : 쿼리 조회");
			if(rs.next()) {
				int count = rs.getInt(1);
				if(count == 0){
					useable = true;
				}
			}
			System.out.println("MemberDAO - checkDuplicateId() 5 : 정상종료, 사용가능여부 :"+useable);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, ps, con);
		}
		
		return useable;
	}
	
	/** 진호 - [회원가입]
	 * 회원가입시 회원을 등록하는 메소드이다.
	 * 
	 * @param mvo
	 * @param auth
	 * @return
	 * @throws SQLException 
	 */
	public int registerMember(MemberVO mvo) throws SQLException {
		System.out.println("MemberDAO - registerMember() 1 : 도달");
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			con = dataSource.getConnection();
			System.out.println("MemberDAO - registerMember() 2 : 커넥션 생성");
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT into member(id, password, name, phone, paypassword, balance, regdate, auth) ");
			sql.append("VALUES(?,?,?,?,?,0,sysdate,?)");
			ps = con.prepareStatement(sql.toString());
			System.out.println("MemberDAO - registerMember() 3 : ps 준비");
			ps.setString(1, mvo.getMemberId());
			ps.setString(2, mvo.getPassword());
			ps.setString(3, mvo.getName());
			ps.setString(4, mvo.getPhone());
			ps.setString(5, mvo.getPayPassword());
			ps.setString(6, mvo.getAuth());
			count = ps.executeUpdate();
			System.out.println("MemberDAO - registerMember() 4 : 쿼리 실행, 정상종료");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(ps, con);
		}
		return count;
	}
	
	/**현민 - [결제 비밀번호 확인]
	 * 결제 카드 충전시 결제 비밀번호 일치 여부를 확인하는 메소드이다.
	 * 해당 회원 결제 비밀번호와 입력 받은 비밀번호가 
	 * 일치할 경우 true
	 * 불일치 할 경우 false
	 * 반환하는 메소드이다.
	 * @param payPassword
	 * @return
	 * @throws SQLException
	 */
	public boolean checkPayPassword(String payPassword, String id) throws SQLException {
		System.out.println("MemberDAO - checkPayPassword() 1 : 도달");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = dataSource.getConnection();
			System.out.println("MemberDAO - checkPayPassword() 2  : 커넥션 준비");
			String sql = "select paypassword from member where id = ?";
			pstmt = con.prepareStatement(sql);
			System.out.println("MemberDAO - checkPayPassword() 3 : ps 준비");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("MemberDAO - checkPayPassword() 4 : 쿼리 조회");
			if (rs.next()) {
				if (rs.getString(1).equals(payPassword)) {
					flag = true;
				}
			}
			System.out.println("MemberDAO - checkPayPassword() 5 : 정상종료,  결제비밀번호 일치여부 :"+flag);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return flag;
	}
	/**현민 - [카드 충전]
	 * 전제 조건으로는 결제 비밀번호가 일치할 경우 이 메소드가 실행된다.
	 * 충전하려는 회원의 아이디와 충전 금액을 받아온 다음 
	 * 회원이 갖고있는 카드에 충전 금액을 더한다.
	 * @param id
	 * @param money
	 * @throws SQLException
	 */
	public void chargeMoney(String id, int money) throws SQLException {
		System.out.println("MemberDAO - chargeMoney() 1 : 도달");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			System.out.println("MemberDAO - chargeMoney() 2 : 커넥션 준비");
			String sql = "update member set balance = balance + ? where id = ?";
			pstmt = con.prepareStatement(sql);
			System.out.println("MemberDAO - chargeMoney() 3 : ps 준비");
			pstmt.setInt(1, money);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			System.out.println("MemberDAO - chargeMoney() 4 : 정상종료,  쿼리 실행");
		}finally {
			closeAll(pstmt, con);
		}	
	}
	public int getBalance(String id) {
		return 0;
	}
	
	/** 작성자 : 현진호 - [로그인]
    * 로그인 메소드이다.
    * 정확한 로그인 정보를 입력받은 경우 해당 MemberVO를 반환하고, 
    * 부정확한 로그인 정보를 입력받았다면 null객체를 리턴한다. 
    * @param payPassword
    * @return
    * @throws SQLException
    */
	public MemberVO login(String id, String password) throws SQLException {
		System.out.println("MemberDAO - login() 1 : 도달");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberVO mvo = null;
		try {
			con = dataSource.getConnection();
			System.out.println("MemberDAO - login() 2 : 커넥션 준비");
			String sql = "SELECT id,name,auth from MEMBER WHERE id=? and password=?";
			ps = con.prepareStatement(sql);
			System.out.println("MemberDAO - login() 3 : ps 준비");
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			System.out.println("MemberDAO - login() 4 : 쿼리 조회");
			if(rs.next()) {
				mvo = new MemberVO();
				mvo.setMemberId(rs.getString("id"));
				mvo.setName(rs.getString("name"));
				mvo.setAuth(rs.getString("auth"));
			}
			System.out.println("MemberDAO - login() 5 : 정상종료");
			System.out.println("MemberDAO - mvo : "+mvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, ps, con);
		}
		
		return mvo;
	}
	/** 지현 - [마이페이지 member정도 가져오는 메서드]
	 *    id로 select 해서 MemberVO(모든정보) 객체를 가져온다 
	 *    
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public MemberVO getMemberInfo(String id) throws SQLException {
		System.out.println("MemberDAO - getMemberInfo() 1 : 도달");
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			System.out.println("MemberDAO - getMemberInfo() 2 : 커넥션 준비");
			StringBuilder sql=new StringBuilder();
			sql.append("select password, name, phone, paypassword, balance, regdate, auth from member where id=?");
			pstmt=con.prepareStatement(sql.toString());
			System.out.println("MemberDAO - getMemberInfo() 3 : ps 준비");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			System.out.println("MemberDAO - getMemberInfo() 4 : 쿼리 조회");
			if(rs.next()) {
				vo=new MemberVO(id, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),rs.getString(6), rs.getString(7));
			}
			System.out.println("MemberDAO - getMemberInfo() 5 : 정상종료");
		}finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
	
	public int getMemberBalance(String id) throws SQLException {
		System.out.println("MemberDAO - getMemberBalance() 1 : 도달");
		int balance = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			System.out.println("MemberDAO - getMemberBalance() 2 : 커넥션 준비");
			StringBuilder sql = new StringBuilder();
			sql.append("select balance from member where id=?");
			pstmt = con.prepareStatement(sql.toString());
			System.out.println("MemberDAO - getMemberBalance() 3 : ps 준비");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("MemberDAO - getMemberBalance() 4 : 쿼리 조회");
			if (rs.next()) {
				balance = rs.getInt(1);
			}
			System.out.println("MemberDAO - getMemberBalance() 5 : 정상종료");
		} finally {
			closeAll(rs, pstmt, con);
		}
		return balance;
	}
}
