package com.fastakeout.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fastakeout.model.MenuDAO;
import com.fastakeout.model.MenuDetailVO;
import com.fastakeout.model.MenuVO;
import com.oreilly.servlet.MultipartRequest;

public class RegisterInfo {

	public void insertInfo(MultipartRequest multi, String memberId, ArrayList<MenuDetailVO> list) throws SQLException {
		System.out.println("RegisterInfo 클래스 진입 1");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("RegisterInfo 클래스 진입 2");
			MenuVO mvo = new MenuVO();
			MenuDetailVO dvo = new MenuDetailVO();
			MenuVO mvo2 = MenuDAO.getInstance().getStoreInfo(memberId);
			mvo.setOwnerId(memberId);
			mvo.setMenuName(multi.getParameter("menuId"));
			mvo.setMenuInfo(multi.getParameter("menuInfo"));
			mvo.setStoreNo(mvo2.getStoreNo()); //  스벅 이라 가정하고
			mvo.setStoreName(mvo.getStoreName()); // 스벅이라 가정하고
			// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
			String fileName = multi.getFilesystemName("picture");
			mvo.setMenuImgUrl(fileName);
			// 각 사이즈 별 hot ice 별로 set 을 해준다.
			System.out.println("RegisterInfo 클래스 진입 3");
			dvo.setHotIce(list.get(i).getHotIce());
			dvo.setMenuPrice(list.get(i).getMenuPrice());
			dvo.setSize(list.get(i).getSize());
			System.out.println("RegisterInfo 클래스 진입 4");
			mvo.setMenuDetailVO(dvo);
			System.out.println("db insert전 pvo:"+mvo);
			//ProductDAO.getInstance().registerProduct(mvo);
			MenuDAO.getInstance().registerMenu(mvo);
			System.out.println("db insert후 pvo:"+mvo);
			//request.setAttribute("pvo", mvo);
		}	
	}
	
}
