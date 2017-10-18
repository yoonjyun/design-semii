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

public class OwnerCurrentOrderController implements Controller {
/** 지현 - 가맹점주 현재 주문 내역 보기
 *   로그인 한 가맹점주가 주문 내역 보기를 클릭시 넘어오는 Controller
 *   가맹점주의 아이디를 받아와 현재 들어온 주문 내역을 받아오는 기능이다
 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			MemberVO vo = (MemberVO) session.getAttribute("mvo");
			ListVO<OrderVO> lvo = new ListVO<OrderVO>();
			int totalPostCount = OrderDAO.getInstance().getCountCurrentOrderOwner(vo.getMemberId());
			int nowPage = Integer.parseInt(request.getParameter("nowPage"));
			System.out.println("OOOO now Page: "+nowPage);
			PagingBean pagingBean = new PagingBean(totalPostCount, nowPage);
			pagingBean.setPostCountPerPage(2);
			pagingBean.setPageCountPerPageGroup(4);
			lvo.setPagingBean(pagingBean);
			ArrayList<OrderVO> orderList = OrderDAO.getInstance().getOwnerCurrentOrderList(pagingBean, vo.getMemberId());
			lvo.setList(orderList);
			request.setAttribute("url", "/owner/order_store_current.jsp");
			request.setAttribute("lvo", lvo);
		}else {
			request.setAttribute("url", "/member/notlogin.jsp");
		}
		return "template/home.jsp";
	}

}
