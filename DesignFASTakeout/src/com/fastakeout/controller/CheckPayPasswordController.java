package com.fastakeout.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.model.MemberDAO;
import com.fastakeout.model.MemberVO;

public class CheckPayPasswordController implements Controller {

/** 지현
 *    결제비밀번호 확인하는 컨트롤러
 *    세션확인 후, 결제비밀번호를 확인하다. 
 *    결제비밀번호가 같으면 flag 가 true 가 되어, 결제 컨트롤러로 넘어가 결제를 진행한다.
 *    flag가 false 가 되면, 결제 실패 jsp로 넘어간다.
 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String url="";
		String returnUrl="";
		HttpSession session=request.getSession(false);
		if(session!=null) {
			MemberVO vo=(MemberVO) session.getAttribute("mvo");
			String payPassword=request.getParameter("payPassword");
			boolean flag=MemberDAO.getInstance().checkPayPassword(payPassword, vo.getMemberId());
			if(flag==true) {
				returnUrl="DispatcherServlet?command=payOrder";
			}else {
				url="../customer/payment.jsp";
				// 결제 비밀번호 틀렸을 경우 alert 띄운다.
				request.setAttribute("message", "incorrectPassword");
				returnUrl="template/home.jsp";
				request.setAttribute("url", url);
			}
		}
			return returnUrl;
	}
	
}