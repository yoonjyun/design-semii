package com.fastakeout.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.model.ListVO;
import com.fastakeout.model.MemberVO;
import com.fastakeout.model.MenuDAO;
import com.fastakeout.model.MenuVO;
import com.fastakeout.model.PagingBean;
import com.fastakeout.model.StoreDAO;
import com.fastakeout.model.StoreVO;

public class ManagementStoreController implements Controller {

/**	지현
 * 		가맹업주가 로그인시, 
 * 		업체등록과 메뉴등록, 관리등을 할 수 있는 업체관리.jsp 로
 * 		연결해주는 jsp
 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session!=null) {
			MemberVO mvo=(MemberVO)session.getAttribute("mvo");
			StoreVO svo=StoreDAO.getInstance().getStoreVO(mvo.getMemberId());
			request.setAttribute("svo", svo);
			//System.out.println(svo.toString());
			if(svo!=null) {
				ListVO<MenuVO> lvo=new ListVO<MenuVO>();
				int totalPostCount = MenuDAO.getInstance().countAllMenu(svo.getStoreNo());
				if(totalPostCount!=0) {
					int nowPage = Integer.parseInt(request.getParameter("nowPage"));
					PagingBean pagingBean = new PagingBean(totalPostCount,nowPage);
					pagingBean.setPostCountPerPage(4);
					pagingBean.setPageCountPerPageGroup(1);
					lvo.setPagingBean(pagingBean);
					ArrayList<MenuVO> menuList=MenuDAO.getInstance().getMenuList(pagingBean, svo.getStoreNo());
					lvo.setList(menuList);
					request.setAttribute("lvo", lvo);					
					request.setAttribute("menuUrl", "/owner/managementMenu.jsp");
				}
			}
			request.setAttribute("url", "/owner/managementStore.jsp");
		}
		return "template/home.jsp";
	}

}
