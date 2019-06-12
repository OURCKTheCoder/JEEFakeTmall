package top.ourck.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.ourck.service.UserService;

@WebServlet("/user/register")
public class UserRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 3552792459241860467L;
	private UserService userService = new UserService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String name = req.getParameter("userName");
		String passwd = req.getParameter("password");
		userService.add(name, passwd);
	}
	
	
}
