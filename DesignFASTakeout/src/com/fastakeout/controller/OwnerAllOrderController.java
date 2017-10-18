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

public class OwnerAllOrderController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			MemberVO vo = (MemberVO) session.getAttribute("mvo");
			ListVO<OrderVO> lvo = new ListVO<OrderVO>();
			int totalPostCount = OrderDAO.getInstance().getCountAllOrderOwner(vo.getMemberId());
			int nowPage = Integer.parseInt(request.getParameter("nowPage"));
			PagingBean pagingBean = new PagingBean(totalPostCount, nowPage);
			pagingBean.setPostCountPerPage(2);
			pagingBean.setPageCountPerPageGroup(4);
			lvo.setPagingBean(pagingBean);
			ArrayList<OrderVO> orderList = OrderDAO.getInstance().getOwnerAllOrderList(pagingBean, vo.getMemberId());
			lvo.setList(orderList);
			request.setAttribute("url", "/owner/order_store_all.jsp");
			request.setAttribute("lvo", lvo);
		}else {
			request.setAttribute("url", "/member/notlogin.jsp");
		}
		return "template/home.jsp";
	}

}
