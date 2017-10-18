package com.fastakeout.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fastakeout.model.StoreDAO;

public class RegisterStoreFormController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 업종 리스트를 가져온다
		ArrayList<String> categoryList = StoreDAO.getInstance().getStoreCategory();
		System.out.println("categoryList: " + categoryList);

		request.setAttribute("categoryList", categoryList);
		request.setAttribute("url", "/owner/register_store_form.jsp");
		return "/template/home.jsp";
	}

}
