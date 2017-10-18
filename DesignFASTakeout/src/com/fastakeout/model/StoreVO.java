package com.fastakeout.model;

import java.util.ArrayList;

public class StoreVO {
	private String storeNo;
	private String storename;
	private String address;
	private String tel;
	private String openDay;
	private String id;
	private ArrayList<String> category;
	private String storeImgUrl;
	public StoreVO() {
		super();
	}
	
	public String getStoreImgUrl() {
		return storeImgUrl;
	}

	public void setStoreImgUrl(String storeImgUrl) {
		this.storeImgUrl = storeImgUrl;
	}

	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getOpenDay() {
		return openDay;
	}
	public void setOpenDay(String openDay) {
		this.openDay = openDay;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<String> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<String> category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "StoreVO [storeNo=" + storeNo + ", storename=" + storename + ", address=" + address + ", tel=" + tel
				+ ", openDay=" + openDay + ", id=" + id + ", category=" + category + "]";
	}
}
