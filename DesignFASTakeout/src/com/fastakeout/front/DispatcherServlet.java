package com.fastakeout.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fastakeout.controller.Controller;

@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DispatcherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DispatcherServlet - doGet()");
		requestProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("DispatcherServlet - doPost()");
		requestProcess(request, response);
	}

	private void requestProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try{
			String command=null;
			if(request.getContentType() != null && 
				    request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1) {	
				command= "upload";
				System.out.println("파일업로드요청 DispatcherServlet multipart command: "+command);
			}else {
				command = request.getParameter("command");
			}
			System.out.println("DispatcherServlet - requestProcess() 1 - command :"+command);
			Controller controller = HandlerMapping.getInstance().create(command);
			System.out.println("DispatcherServlet - requestProcess() 2 - controller :"+controller);
			String url = controller.execute(request, response);	
			System.out.println("DispatcherServlet - requestProcess() 3 - url :"+url);
			
			if(url.trim().startsWith("redirect:")) {
				System.out.println("DispatcherServlet - requestProcess() 4 : redirect");
				response.sendRedirect(url.trim().substring(9));
			}else {
				System.out.println("DispatcherServlet - requestProcess() 5 : forward");
				request.getRequestDispatcher(url).forward(request, response);
			}
		}catch(Exception e){
			System.out.println("DispatcherServlet - requestProcess() 6 : forward");
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}	
	}
}
