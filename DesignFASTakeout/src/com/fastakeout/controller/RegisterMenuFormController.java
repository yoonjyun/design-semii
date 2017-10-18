package com.fastakeout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterMenuFormController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session != null) {
			request.setAttribute("url", "/owner/register_menu_form.jsp");
		}
		return "/template/home.jsp";
	}

}
