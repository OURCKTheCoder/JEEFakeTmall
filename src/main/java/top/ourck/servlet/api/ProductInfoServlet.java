package top.ourck.servlet.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


import top.ourck.beans.Product;
import top.ourck.service.ProductService;

/**
 * 首页商品展示的API
 * @author ourck
 */
@WebServlet("/product/info")
public class ProductInfoServlet extends HttpServlet {

	private static final long serialVersionUID = -5805984784987983354L;
	private ProductService productService = new ProductService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONArray jary = new JSONArray();
		List<Product> pList = productService.list();
		
		for(Product p : pList) {
			JSONObject jobj = new JSONObject();
			jobj.put("name", p.getName());
			jobj.put("price", p.getOriginalPrice());
			jobj.put("detailUrl", "www.baidu.com");
			jobj.put("imageUrl", "http://localhost:8080/JEEFakeTmall/product/image"); // FIXME !!!
			jary.put(jobj);
		}
		resp.setContentType("text/html");
		resp.getWriter().println(jary.toString());
	}

	
	
}
