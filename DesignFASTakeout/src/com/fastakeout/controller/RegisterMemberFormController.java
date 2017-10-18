package com.fastakeout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterMemberFormController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("url", "../member/register_member.jsp");
		return "template/home.jsp";
	}

}
