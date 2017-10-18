package com.fastakeout.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.model.MemberVO;
import com.fastakeout.model.OrderDAO;
import com.fastakeout.model.OrderVO;

public class PayOrderController implements Controller {
/**  지현
 * 	결제진행하는 컨트롤러. 
 * 	세션확인 후, db에서 고객의 잔액을 확인한다. 
 * 	전체 주문의 금액보다 db의 잔액이 많으면 
 * 	고객의 잔액에서 전체 주문 금액을 빼고, 점주의 잔액은 증가시킨다. 
 * 	결제가 진행된 주문은 db에서 주문상태를 장바구니 -> 조리중으로 업데이트한다
 * 	잔액부족시 payment_fail로 
 * 	(장바구니로 돌아가서 alert 창 띄우는거 의논)
 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="";
		HttpSession session=request.getSession(false);
		if(session!=null) {
			MemberVO vo=(MemberVO) session.getAttribute("mvo");
			int balance=OrderDAO.getInstance().getBalance(vo.getMemberId());
			System.out.println(balance);
			ArrayList<OrderVO> orderList=OrderDAO.getInstance().getOrderList(vo.getMemberId());
			int totalPrice=0;
			for(int i=0;i<orderList.size();i++) {
				totalPrice += orderList.get(i).getOrderPrice();
			}
			if(balance>totalPrice) {
				//customer 잔액 업데이트
				OrderDAO.getInstance().balanceUpdates(vo.getMemberId(), totalPrice*(-1));
				for(int i=0;i<orderList.size();i++) {
					// owner 잔액 업데이트
					OrderDAO.getInstance().balanceUpdates(orderList.get(i).getMenuVO().getOwnerId(), orderList.get(i).getOrderPrice());
					// 주문상태 업데이트
					OrderDAO.getInstance().changeOrderStatus(orderList.get(i).getOrderNo(), "조리중");
				}
				// 고객 _ 현재주문내역으로 ( command 필요시 수정 )
				url="/DispatcherServlet?command=customerCurrentOrderList";
			}else {
				//잔액부족시
				url="/customer/payment_fail.jsp";			
			}		
		}
		request.setAttribute("url", url);
		return  "template/home.jsp";
	}

}