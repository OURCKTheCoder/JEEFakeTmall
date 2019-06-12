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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 7709829638140293297L;
	private AuthService authService = new AuthService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String target = req.getParameter("target");
		if(target == null || target.equals("") )
			target = "/JEEFakeTmall/index";
		
		if(target.equals("logout")) {
			Cookie[] cks = req.getCookies();
			for(Cookie ck : cks) {
				if(ck.getName().equals("ticket")) {
					authService.logout(ck.getValue());
					break;
				}
			}
			resp.sendRedirect("/JEEFakeTmall/");
		}
		else {
			req.setAttribute("target", target);
			req.getRequestDispatcher("/loginPage/login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String target = req.getParameter("target");
		
		Map<String, String> info = authService.getAuth(userName, password);
		if(info.get("success").equals("false")) {
			// TODO Login failed!
		}
		else {
			String ticket = info.get("ticket");
			Cookie ck = new Cookie("ticket", ticket);
			resp.addCookie(ck);
			resp.sendRedirect(target);
		}
	}

	
}
