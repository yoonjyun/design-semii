package com.fastakeout.model;

public class MemberVO {
	private String memberId;
	private String password;
	private String name;
	private String phone;
	private String payPassword;
	private int balance;
	private String regDate;
	private String auth;
	public MemberVO() {
		super();
	}
	public MemberVO(String memberId, String password, String name, String phone, String payPassword, String auth) {
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.payPassword = payPassword;
		this.auth = auth;
	}
	
	public MemberVO(String memberId, String password, String name, String phone, String payPassword, int balance,
			String regDate, String auth) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.payPassword = payPassword;
		this.balance = balance;
		this.regDate = regDate;
		this.auth = auth;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", payPassword=" + payPassword + ", balance=" + balance + ", regDate=" + regDate + ", auth=" + auth
				+ "]";
	}
}
