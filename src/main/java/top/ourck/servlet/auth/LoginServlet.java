package top.ourck.servlet.auth;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.ourck.service.AuthService;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 7709829638140293297L;
	private AuthService authService = new AuthService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		
		Map<String, String> info = authService.getAuth(userName, password);
		if(info.get("success").equals("false")) {
			resp.sendRedirect("/JEEFakeTmall/login.html");
		}
		else {
			String ticket = info.get("ticket");
			Cookie ck = new Cookie("ticket", ticket);
			ck.setPath("/");
			resp.addCookie(ck);
			resp.sendRedirect("/JEEFakeTmall");
		}
	}

	
}
