package com.fastakeout.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fastakeout.model.ListVO;
import com.fastakeout.model.MenuDAO;
import com.fastakeout.model.MenuVO;
import com.fastakeout.model.PagingBean;
import com.fastakeout.model.StoreDAO;
import com.fastakeout.model.StoreVO;

public class MenuListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("menuListController 도달");
		ListVO<MenuVO> lvo=new ListVO<MenuVO>();
		
		String storeNo=request.getParameter("storeNo");
		System.out.println(storeNo);
		int totalPostCount = MenuDAO.getInstance().countAllMenu(storeNo);
		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		System.out.println(nowPage);
		PagingBean pagingBean = new PagingBean(totalPostCount,nowPage);
		pagingBean.setPostCountPerPage(4);
		pagingBean.setPageCountPerPageGroup(3);
		lvo.setPagingBean(pagingBean);

		ArrayList<MenuVO> menuList=MenuDAO.getInstance().getMenuList(pagingBean,storeNo);
		lvo.setList(menuList);
		
		StoreVO svo = StoreDAO.getInstance().getStoreVO(storeNo);
		
		request.setAttribute("lvo", lvo);
		request.setAttribute("url", "/menu/menu_list.jsp");
		request.setAttribute("svo", svo);
		return "/template/home.jsp";
	}

}
