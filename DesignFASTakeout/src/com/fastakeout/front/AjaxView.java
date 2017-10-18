package com.fastakeout.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxView
 */
@WebServlet("/AjaxView")
public class AjaxView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Model2 에서 ajax 방식으로 응답할 때 사용하는 Servlet
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(request.getAttribute("responseBody").toString());
		System.out.println("ajax 방식으로 응답");
		out.close();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Model2 에서 ajax 방식으로 응답할 때 사용하는 Servlet
				System.out.println("Ajax View 로 들어옴: 안녕안녕");
				response.setContentType("text/html;charset=utf-8");
				request.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.println(request.getAttribute("responseBody").toString());
				System.out.println("ajax 방식으로 응답");
				out.close();
	}

}
