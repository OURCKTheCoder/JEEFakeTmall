package top.ourck.servlet.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.ourck.service.AuthService;

@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = -7785563746586215317L;
	private AuthService authService = new AuthService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cks = req.getCookies();
		for(Cookie ck : cks) {
			if(ck.getName().equals("ticket")) {
				authService.logout(ck.getValue());
				break;
			}
		}
		resp.sendRedirect("/JEEFakeTmall/index.html");
	}

}
