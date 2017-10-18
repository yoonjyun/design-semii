package com.fastakeout.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.model.MemberVO;
import com.fastakeout.model.OrderDAO;
import com.fastakeout.model.OrderVO;

public class CustomerCurrentOrderController implements Controller {

	/**작성자 : 현민 - [현재 주문 내역 보기]
	 * 로그인 한 회원이 주문 내역 보기를 클릭시 넘어오는 Controller
	 * 회원 아이디를 받아와 해당 회원이 현재 조리중인 주문 내역을 받아오는 기능이다.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		if(session != null) {
			ArrayList<OrderVO> list = OrderDAO.getInstance().getCustomerCurrentOrderList(mvo.getMemberId());
			request.setAttribute("currentOrderList", list);
			request.setAttribute("url", "/customer/order_cust_current.jsp");
		}else {
			request.setAttribute("url", "/member/notlogin.jsp");
		}
		return "template/home.jsp";
	}

}
