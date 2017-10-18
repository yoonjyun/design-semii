package com.fastakeout.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fastakeout.model.MemberDAO;

public class ChargeMoneyFormController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String id = request.getParameter("id");
		System.out.println("ChargeMoneyForm 에서 id 값 : "+id);
		int balance = MemberDAO.getInstance().getMemberBalance(id);
		request.setAttribute("balance", balance);
		request.setAttribute("url", "../member/charge.jsp");
		return "template/home.jsp";
	}

}
