package top.ourck.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
//	private static final UserService userService = new UserService(); // TODO Coupling! See doGet() method!
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		response.getWriter().println(op + " " + "()method invoked in OrderServlet!");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
}
