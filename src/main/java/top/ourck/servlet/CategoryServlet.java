package top.ourck.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/categoryServlet")
public class CategoryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 3812941669672146055L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String op = (String) request.getAttribute("op");
		response.getWriter().println(op + "() method invoked in OrderServlet!");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		
	}

}
