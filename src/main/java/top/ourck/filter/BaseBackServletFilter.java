package top.ourck.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseBackServletFilter extends HttpFilter {

	private static final long serialVersionUID = 844088609266205943L;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String path = request.getContextPath();
		String cmd = request.getRequestURI().replace(path + "/", "");
		// TODO 如果有人使坏 输的是admin而不是admin/会导致异常
		if(cmd.startsWith("admin")) {
			cmd = cmd.substring(6); // remove the prefix "admin/".
			String[] command = cmd.split("_");
			if(command.length == 2) {
				String obj = command[0];
				String op = command[1];
				String targetServlet = obj + "Servlet";
				request.setAttribute("op", op);
				request.getRequestDispatcher("/admin/" + targetServlet).forward(request, response);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}
	
}
