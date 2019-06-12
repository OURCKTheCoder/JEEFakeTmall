package top.ourck.servlet.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理用户购物车更新的API
 * @author ourck
 */
public class CartUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1255804248750307997L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// TODO 前端怎么给？
		resp.setContentType("text/html");
	}


}
