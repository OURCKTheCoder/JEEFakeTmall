package top.ourck.servlet.api;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import top.ourck.beans.OrderItem;
import top.ourck.beans.Product;
import top.ourck.beans.User;
import top.ourck.service.OrderItemService;

/**
 * 获取用户购物车的API
 * @author ourck
 */
@WebServlet("/cart/list")
public class CartInfoServlet extends HttpServlet {

	private static final long serialVersionUID = -112080712398671757L;
	private OrderItemService oiService = new OrderItemService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User)req.getAttribute("rememberedUser");
		if(user == null) {
			resp.sendRedirect("http://localhost:8080/JEEFakeTmall/login");
		}
		else {
			List<OrderItem> oiList = oiService.listCartByUserId(user.getId());
			JSONArray jary = new JSONArray();
			for(OrderItem oi : oiList) {
				Random rand = new Random();
				int randImgId = (int) ((rand.nextInt(100)) / 100.0 * 24.0 + 1.0);
				
				Product p = oi.getProduct();
				JSONObject jobj = new JSONObject();
				jobj.put("name", p.getName());
				jobj.put("img_url", "http://localhost:8080/JEEFakeTmall/img/product/" + randImgId + ".jpg");
				jobj.put("quantity", oi.getNumber());
				jobj.put("price", p.getOriginalPrice());
				jary.put(jobj);
			}
			
			resp.setContentType("application/json");
			resp.getWriter().println(jary.toString());
		}
	}
	
	
}
