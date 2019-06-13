package top.ourck.servlet.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 查看商品细节的API
 * @author ourck
 */
@WebServlet("/product/detail")
public class ProductDetailServlet extends HttpServlet {

	private static final long serialVersionUID = -8152307407054163312L;

	// TODO ...
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		resp.setContentType("text/html");
	}

	
}