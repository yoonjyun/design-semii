package com.fastakeout.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.model.MemberVO;
import com.fastakeout.model.OrderDAO;

public class CartDeleteController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession();
		String orderNo = request.getParameter("orderNo");
		System.out.println(orderNo);
		if(session != null) {
			MemberVO mvo = (MemberVO) session.getAttribute("mvo");
			OrderDAO.getInstance().cartDelete(orderNo, mvo.getMemberId());
			request.setAttribute("url", "/customer/cart_delete_ok.jsp");
		}else {
			request.setAttribute("url", "/member/notlogin.jsp");
		}
		return "/template/home.jsp";
	}

}
