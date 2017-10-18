package com.fastakeout.model;

public class MenuDetailVO {
	private String menuDetailNo;
	private String hotIce;
	private String size;
	private int menuPrice;
	public MenuDetailVO() {
		super();
	}
	public String getMenuDetailNo() {
		return menuDetailNo;
	}
	public void setMenuDetailNo(String menuDetailNo) {
		this.menuDetailNo = menuDetailNo;
	}
	public String getHotIce() {
		return hotIce;
	}
	public void setHotIce(String hotIce) {
		this.hotIce = hotIce;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	@Override
	public String toString() {
		return "MenuDetailVO [menuDetailNo=" + menuDetailNo + ", hotIce=" + hotIce + ", size=" + size + ", menuPrice="
				+ menuPrice + "]";
	}
	
}
