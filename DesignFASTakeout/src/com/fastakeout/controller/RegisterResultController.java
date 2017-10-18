package com.fastakeout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterResultController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = request.getParameter("result");
		String url = "";
		if(result.equals("ok")){
			url = "/member/register_member_ok.jsp";
		}else{
			url = "/member/register_member_fail.jsp";
		}
		System.out.println("url : "+url);
		request.setAttribute("url", url);

		return "template/home.jsp";
	}

}
