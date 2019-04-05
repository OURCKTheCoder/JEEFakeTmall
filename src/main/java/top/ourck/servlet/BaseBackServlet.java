package top.ourck.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class BaseBackServlet extends HttpServlet {

	private static final long serialVersionUID = 2730043029904233693L;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		
	}

}
