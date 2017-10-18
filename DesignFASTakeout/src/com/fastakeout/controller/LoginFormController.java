package com.fastakeout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("url", "../member/login.jsp");
		return "template/home.jsp";
	}

}
