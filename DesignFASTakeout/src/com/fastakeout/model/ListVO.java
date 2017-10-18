package com.fastakeout.model;

import java.util.List;

public class ListVO<T> {
	private List<T> list;
	private PagingBean pagingBean;

	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}

	public PagingBean getPagingBean() {
		return pagingBean;
	}

	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
}
