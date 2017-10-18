package com.fastakeout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IntroController  implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("url", "intro.jsp");
		return "welcome.jsp";
	}

}
