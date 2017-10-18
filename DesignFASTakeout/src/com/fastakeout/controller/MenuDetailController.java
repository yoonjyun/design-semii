package com.fastakeout.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fastakeout.model.MenuDAO;
import com.fastakeout.model.MenuVO;

public class MenuDetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		System.out.println("메뉴상세정보 컨트롤러");
		String menuNo=request.getParameter("menuNo");
		System.out.println("메뉴넘버 : "+menuNo);
		ArrayList<MenuVO> mdList=MenuDAO.getInstance().getMenuDetail(menuNo);
		System.out.println("메뉴상세리스트 : "+mdList);
		request.setAttribute("mdList", mdList);
		request.setAttribute("url", "/menu/menu_detail.jsp");
		return "/template/home.jsp";
	}

}
