package com.fastakeout.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fastakeout.model.MemberDAO;
import com.fastakeout.model.MemberVO;

public class RegisterMemberController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String auth = request.getParameter("auth");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String userPayPwd = request.getParameter("userPayPwd");
		MemberVO mvo = new MemberVO(userId, userPwd, userName, userPhone, userPayPwd, auth);
		System.out.println("회원가입 컨트롤러 1 - mvo : "+mvo);
		int count = MemberDAO.getInstance().registerMember(mvo);
		String regResult = "";
		if(count == 1){
			regResult = "ok";
		}else{
			regResult = "fail";
		}
		System.out.println("회원가입 컨트롤러 2 - regResult : "+regResult);
		return "redirect:DispatcherServlet?command=regResult&result="+regResult;
	}

}
