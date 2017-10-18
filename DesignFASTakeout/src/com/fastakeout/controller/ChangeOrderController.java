package com.fastakeout.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.controller.Controller;
import com.fastakeout.model.OrderDAO;

public class ChangeOrderController implements Controller {
/**  지현 - 가맹점주가 주문상태를 바꾸는 컨트롤러
 * 		현재 주문 내역에서 orderNo를 가져와서,
 * 		DAO에서 orderNo의 주문을 조리중-> 주문완료로 변경한다
 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("********************************************");
		HttpSession session=request.getSession(false);
		String url = null;
		if(session!=null) {
			String orderNo=request.getParameter("orderNo");
			System.out.println(orderNo);
			int count = OrderDAO.getInstance().changeOrderStatus(orderNo, "주문완료");
			
			if(count == 1) {
				// 성공
				url =  "redirect:DispatcherServlet?command=ownerCurrentOrderList&nowPage=1";
			}else {
				// 실패
				url = "";
			}
			
		}
		return url;
		
//			request.setAttribute("url", "/DispatcherServlet?command=ownerCurrentOrderList&nowPage=1");
//		return "template/home.jsp";
	}
}
