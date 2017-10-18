package com.fastakeout.model;

public class MenuVO {
	private String menuNo;
	private String menuInfo;
	private String menuName;
	private String storeNo;
	private String storeName;
	private String ownerId;
	private String menuImgUrl;
	private MenuDetailVO menuDetailVO;

	public MenuVO() {
		super();
	}
	public MenuVO(String storeNo, String ownerId) {
		super();
		this.storeNo = storeNo;
		this.ownerId = ownerId;
	}
	
	public String getMenuImgUrl() {
		return menuImgUrl;
	}
	public void setMenuImgUrl(String menuImgUrl) {
		this.menuImgUrl = menuImgUrl;
	}
	public MenuDetailVO getMenuDetailVO() {
		return menuDetailVO;
	}
	public void setMenuDetailVO(MenuDetailVO menuDetailVO) {
		this.menuDetailVO = menuDetailVO;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getMenuInfo() {
		return menuInfo;
	}
	public void setMenuInfo(String menuInfo) {
		this.menuInfo = menuInfo;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	@Override
	public String toString() {
		return "MenuVO [menuNo=" + menuNo + ", menuInfo=" + menuInfo + ", menuName=" + menuName + ", storeNo=" + storeNo
				+ ", ownerId=" + ownerId + ", menuDetailVO=" + menuDetailVO + "]";
	}
}
