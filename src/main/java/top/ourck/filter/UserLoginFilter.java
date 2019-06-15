package top.ourck.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import top.ourck.beans.LoginTicket;
import top.ourck.beans.User;
import top.ourck.service.AuthService;
import top.ourck.service.UserService;

/**
 * TODO 这仅仅只是后台管理系统的登录验证Filter 还应该照顾前台，也搞一个。
 * @author ourck
 *
 */
public class UserLoginFilter extends HttpFilter {

	private static final long serialVersionUID = 3963316667619810889L;

	private HashSet<String> pathToBeIgnored = new HashSet<>();
	private AuthService authService = new AuthService();
	private UserService userService = new UserService();
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		boolean passFilterFlag = false;
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		Cookie[] cookies = request.getCookies();
		
		// 检查是否访问的是不受限制的页面（登陆页面）
		String str = request.getRequestURI();
		boolean unrestrictedFlag = false;
		for(String pattern : pathToBeIgnored) {
			if(str.contains(pattern))
				unrestrictedFlag = true;
		}
		
		// 根据上一步的Flag执行不同动作
		if(unrestrictedFlag) {
			passFilterFlag = true;
		}
		else if(cookies == null) {
			passFilterFlag = false;
		}
		else {
			for(Cookie ck : cookies) {
				if(ck.getName().equals("ticket")) {
					// 1. Get ticket string.
					String tStr = ck.getValue();
					LoginTicket t = authService.getTicket(tStr);
					if(t == null) {
						passFilterFlag = false;
						break;
					}
					
					// 2. Validate this ticket.
					Date deadline = t.getExpired();
					Date now = new Date();
					if(deadline.compareTo(now) < 0 || t.getStatus() != 0) {
						// If this ticket has expired.
						passFilterFlag = false;
						break;
					}
					
					// 3. Get this ticket's owner.
					int uid = t.getUser().getId();
					User user = userService.getById(uid);
					
					// 4. Attach this user's info into request.
					request.setAttribute("rememberedUser", user);
					passFilterFlag = true;
					break;
				}
			}
		}
		
		if(passFilterFlag) {
			chain.doFilter(req, res);
		}
		else {
			JSONObject jobj = new JSONObject();
			jobj.put("success", "false");
			jobj.put("msg", "Needs login!");
			response.getWriter().println(jobj);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		pathToBeIgnored.add(config.getInitParameter("loginPagePath"));
	}
	
	

}
