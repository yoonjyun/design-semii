package com.fastakeout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession(false) != null){
			request.getSession(false).invalidate();
		}
		System.out.println("로그아웃 + 세션 invalidate");
		return "redirect:welcome.jsp";
	}

}
