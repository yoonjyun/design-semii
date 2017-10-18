package com.fastakeout.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.model.ListVO;
import com.fastakeout.model.MemberVO;
import com.fastakeout.model.OrderDAO;
import com.fastakeout.model.OrderVO;
import com.fastakeout.model.PagingBean;

public class CartListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session != null) {
			// ListVO
			ListVO<OrderVO> lvo = new ListVO<OrderVO>();

			// 세션에 담긴 로그인한 회원 정보
			MemberVO member = (MemberVO) session.getAttribute("mvo");

			// paging bean 에 사용할 totalPostCount 와 nowPage
			int totalPostCount = OrderDAO.getInstance().countAllCart(member.getMemberId());
			int nowPage = Integer.parseInt(request.getParameter("nowPage"));
			System.out.println("nowpage: "+nowPage+" totalPostCount: "+totalPostCount);
			PagingBean pagingBean = new PagingBean(totalPostCount, nowPage);
			pagingBean.setPostCountPerPage(4);
			pagingBean.setPageCountPerPageGroup(5);
			lvo.setPagingBean(pagingBean);

			// 장바구니에 있는 전체 상품 리스트를 가져오는 메서드
			ArrayList<OrderVO> cartList = OrderDAO.getInstance().cartList(member.getMemberId(), pagingBean);
			lvo.setList(cartList);

			request.setAttribute("lvo", lvo);
			request.setAttribute("url", "/customer/cart.jsp");
		} else {
			request.setAttribute("url", "/member/notlogin.jsp");
		}
		return "/template/home.jsp";
	}

}
