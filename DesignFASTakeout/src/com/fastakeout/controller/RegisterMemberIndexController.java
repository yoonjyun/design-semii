package com.fastakeout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterMemberIndexController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("url", "../member/register_index.jsp");
		return "template/home.jsp";
	}

}
