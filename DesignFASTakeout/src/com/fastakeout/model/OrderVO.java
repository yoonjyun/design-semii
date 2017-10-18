package com.fastakeout.model;

public class OrderVO {
	private int rnum;
	private String orderNo;
	private int quantity;
	private int orderPrice;
	private String orderDate;
	private String orderStatus;
	private MenuVO menuVO;
	private String memberId;
	
	public OrderVO() {
		super();
	}
	public OrderVO(String orderNo, int orderPrice, MenuVO vo) {
		super();
		this.orderNo = orderNo;
		this.orderPrice = orderPrice;
		this.menuVO = vo;
	}

	public OrderVO(String orderNo, int quantity, int orderPrice, String orderDate, String memberId, String orderStatus,
			MenuVO menuVO) {
		super();
		this.orderNo = orderNo;
		this.quantity = quantity;
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
		this.memberId = memberId;
		this.orderStatus = orderStatus;
		this.menuVO = menuVO;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public MenuVO getMenuVO() {
		return menuVO;
	}
	public void setMenuVO(MenuVO menuVO) {
		this.menuVO = menuVO;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "MenuVO [orderNo=" + orderNo + ", quantity=" + quantity + ", orderPrice=" + orderPrice + ", orderDate="
				+ orderDate + ", orderStatus=" + orderStatus + ", memberId=" + memberId + "]";
	}
}
