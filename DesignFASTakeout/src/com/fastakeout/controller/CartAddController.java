package com.fastakeout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.model.MemberVO;
import com.fastakeout.model.MenuDetailVO;
import com.fastakeout.model.MenuVO;
import com.fastakeout.model.OrderDAO;
import com.fastakeout.model.OrderVO;

public class CartAddController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session != null) {
			// quantity, o_price, md_no, id (메뉴 상세보기 jsp 에서 가져와야할 정보)
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int orderPrice = Integer.parseInt(request.getParameter("orderPrice"));
			String menuDetailNo = request.getParameter("menuDetailNo");
			System.out.println("수량 : "+quantity+"총금액 : "+orderPrice+"메뉴 번호 :"+menuDetailNo);
			OrderVO ovo = new OrderVO();
			// 수량
			ovo.setQuantity(quantity);
			// 주문금액
			ovo.setOrderPrice(orderPrice);
			// 메뉴 상세 번호
			MenuVO menu = new MenuVO();
			MenuDetailVO menuDetail = new MenuDetailVO();
			menuDetail.setMenuDetailNo(menuDetailNo);
			menu.setMenuDetailVO(menuDetail);
			ovo.setMenuVO(menu);
			// 주문한 회원 id
			MemberVO mvo = (MemberVO) session.getAttribute("mvo");
			ovo.setMemberId(mvo.getMemberId());

			// 장바구니에 추가 하는 메서드
			OrderDAO.getInstance().cartAdd(ovo);

			// 장바구니로 이동하시겠습니가? 페이지
			// ajax 처리부분: 수정해야함
			//request.setAttribute("url", "/customer/cart_move.jsp");
			request.setAttribute("responseBody", "장바구니에 담았습니다.");
		} else {
			request.setAttribute("url", "/member/notlogin.jsp");
		}
		return "AjaxView";
	}
}
