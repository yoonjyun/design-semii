package com.fastakeout.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fastakeout.model.ListVO;
import com.fastakeout.model.PagingBean;
import com.fastakeout.model.StoreDAO;
import com.fastakeout.model.StoreVO;

public class StoreListController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ListVO<StoreVO> lvo=new ListVO<StoreVO>();
		
		int totalPostCount = StoreDAO.getInstance().countAllStore();
		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		PagingBean pagingBean = new PagingBean(totalPostCount,nowPage);
		pagingBean.setPostCountPerPage(6);
		pagingBean.setPageCountPerPageGroup(5);
		lvo.setPagingBean(pagingBean);

		ArrayList<StoreVO> storeList=StoreDAO.getInstance().getStoreList(pagingBean);
		for(int i=0;i<storeList.size();i++)
			System.out.println("가맹점번호: "+storeList.get(i).getStoreNo());
		lvo.setList(storeList);
		
		request.setAttribute("lvo", lvo);
		request.setAttribute("url", "/menu/store_list.jsp");
		return "/template/home.jsp";
	}
}