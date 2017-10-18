package com.fastakeout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.model.MemberDAO;
import com.fastakeout.model.MemberVO;

public class MyPageController implements Controller {
	
	/** 지현
	 *   MyPageController. 
	 *   세션확인 후 id로 memberDAO에서 MemberVO 객체를 가져온뒤, 
	 *   권한을 확인하여 customer이면 고객 마이페이지로, 
	 *   owner 이면 업주 마이페이지로 url을 설정 request 에 set하고, home.jsp로 return 한다
	 */

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="";
		HttpSession session=request.getSession(false);
		if(session!=null) {
			MemberVO vo=(MemberVO) session.getAttribute("mvo");
			MemberVO memberInfo=MemberDAO.getInstance().getMemberInfo(vo.getMemberId());
			String auth=vo.getAuth();
			if(auth.equals("customer")) {
				url="/customer/mypage_customer.jsp";
			}else {
				url="/owner/mypage_owner.jsp";
			}
			request.setAttribute("url", url);
			request.setAttribute("memberInfo", memberInfo);
		}//if
//			else {
			//비 로그인시
//		}
		return "template/home.jsp";
	}//execute
}