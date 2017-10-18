package com.fastakeout.test;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fastakeout.model.StoreDAO;
import com.fastakeout.model.StoreVO;

public class MyTest {

	public static void main(String[] args) {
	try {
		StoreVO svo = new StoreVO();
		svo.setStorename("짱구분식");
		svo.setAddress("판교역 4번출구");
		svo.setTel("033-444-4444");
		svo.setOpenDay("Mon-Wed");
		svo.setStoreImgUrl("/FASTakeout/upload/jjangoo.jpg");
		svo.setId("java");
		
		ArrayList<String> cates = new ArrayList<String>();
		cates.add("한식");
		cates.add("중식");
		cates.add("일식");
		svo.setCategory(cates);
		
		StoreDAO.getInstance().registerStore(svo);
		System.out.println("업체등록 성공");
		} catch (SQLException e) {
			System.out.println("업체등록 실패");
			e.printStackTrace();
		}
		
	}
}
