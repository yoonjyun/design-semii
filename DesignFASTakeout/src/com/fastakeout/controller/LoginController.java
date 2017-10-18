package com.fastakeout.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fastakeout.model.MemberDAO;
import com.fastakeout.model.MemberVO;

public class LoginController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		System.out.println("LoginController 1 ");
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		MemberVO mvo = MemberDAO.getInstance().login(id, pwd);
		System.out.println("LoginController 2 - mvo : "+mvo);
		if(mvo == null) {
			request.setAttribute("url", "../member/login_fail.jsp");
			return "template/home.jsp";
		}else {
			request.getSession().setAttribute("mvo", mvo);
			return "redirect:DispatcherServlet?command=getStoreList&nowPage=1";
		}
	}

}
