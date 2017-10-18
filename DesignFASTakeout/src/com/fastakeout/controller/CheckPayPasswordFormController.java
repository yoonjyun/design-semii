package com.fastakeout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckPayPasswordFormController implements Controller {
/**
 *  지현 
 *  결제비밀번호확인Form 컨트롤러 
 *  세션확인 후 url을 payment.jsp로 설정해준다.
 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception  {
		HttpSession session=request.getSession(false);
		if(session!=null) {
			request.setAttribute("url","/customer/payment.jsp");
		}
		return "template/home.jsp";
	}
}
