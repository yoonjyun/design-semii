package com.fastakeout.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.model.MemberDAO;

public class ChargeMoneyController implements Controller {
	/**현민
	 * 로그인 시 결제 카드 충전이 가능하다
	 * 해당 회원 아이디(id), 결제 비밀번호(payPassword), 충전 금액(money) 를 받아오고
	 * 가장 먼저 회원 결제 비밀번호와 입력 받은 결제 비밀번호가 일치하는지 검사한다
	 * 일치할 경우 회원 결제카드에 충전이 된다.
	 * 일치하지 않을 경우 충전 되지 않고 비밀번호 불일치 알림창을 보낸다.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession(false);
		String id = request.getParameter("id");
		String payPassword = request.getParameter("payPassword");
		int money = Integer.parseInt(request.getParameter("money"));
		if(session != null) {
			if(MemberDAO.getInstance().checkPayPassword(payPassword, id) == true) {
				MemberDAO.getInstance().chargeMoney(id, money);
				request.setAttribute("url", "../member/charge_ok.jsp");
				System.out.println("성공");
			}else {
				System.out.println("실패");
				request.setAttribute("url", "../member/charge_fail.jsp");
			}
			return "template/home.jsp";
		}else {
			// 비 로그인 시 알림창 필요할 듯 
		}
		return "template/home.jsp";
	}
}
