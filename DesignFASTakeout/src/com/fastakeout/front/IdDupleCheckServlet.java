package com.fastakeout.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fastakeout.model.MemberDAO;

@WebServlet("/IdDupleCheckServlet")
public class IdDupleCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public IdDupleCheckServlet() {
        super();
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("input_id");
		PrintWriter out = null;
		boolean useable;
		try {
			useable = MemberDAO.getInstance().checkDuplicateId(id);
			System.out.println("아이디 중복확인 컨트롤러 - 아이디 : "+id+" , 사용가능여부 : "+useable);
			out = response.getWriter();
			out.print(useable);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(out!=null)
				out.close();
		}
	}

}
