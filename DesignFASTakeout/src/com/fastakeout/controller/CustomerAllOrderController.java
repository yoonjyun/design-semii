package com.fastakeout.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.model.ListVO;
import com.fastakeout.model.MemberVO;
import com.fastakeout.model.OrderDAO;
import com.fastakeout.model.OrderVO;
import com.fastakeout.model.PagingBean;

public class CustomerAllOrderController implements Controller {

	/**작성자 : 현민 - [전체 주문 내역 보기]
	 * 로그인 한 회원이 주문 내역 보기를 클릭시 넘어오는 Controller
	 * 회원 아이디를 받아와 해당 회원이 현재와 과거에 주문 했던 주문 내역을 받아오는 기능이다.
	 * 지현 - pagingBean 추가 
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			MemberVO vo= (MemberVO) session.getAttribute("mvo");
			ListVO<OrderVO> lvo=new ListVO<OrderVO>();
			int totalPostCount=OrderDAO.getInstance().getCountAllOrder(vo.getMemberId());
			int nowPage = Integer.parseInt(request.getParameter("nowPage"));
			PagingBean pagingBean=new PagingBean(totalPostCount, nowPage);
			pagingBean.setPostCountPerPage(3);
			pagingBean.setPageCountPerPageGroup(2);
			lvo.setPagingBean(pagingBean);
			ArrayList<OrderVO> orderList = OrderDAO.getInstance().getCustomerAllOrderList(pagingBean, vo.getMemberId());
			lvo.setList(orderList);
			request.setAttribute("url", "/customer/order_cust_all.jsp");
			request.setAttribute("lvo", lvo);
		}else {
			request.setAttribute("url", "/member/notlogin.jsp");
		}
		return "template/home.jsp";
	}

}
