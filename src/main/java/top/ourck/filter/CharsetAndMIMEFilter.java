package top.ourck.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharsetAndMIMEFilter extends HttpFilter {

	private static final long serialVersionUID = -5086146497139835L;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String path = request.getContextPath();
		String resPath = request.getRequestURI().replace(path + "/", "");
		if(resPath.endsWith(".css"))
			response.setContentType("text/css");
		
		chain.doFilter(request, response);
	}

}
