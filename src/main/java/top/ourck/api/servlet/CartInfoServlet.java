package top.ourck.api.servlet;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/cart/list")
public class CartInfoServlet extends HttpServlet {

	private static final long serialVersionUID = -112080712398671757L;
	private OrderItemService oiService = new OrderItemService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User)req.getAttribute("currentUser");
		if(user == null) {
			// TODO 跳转到登录
		}
		else {
			List<OrderItem> oiList = oiService.listByUserId(user.getId());
			JSONArray jary = new JSONArray();
			for(OrderItem oi : oiList) {
				Product p = oi.getProduct();
				JSONObject jobj = new JSONObject();
				jobj.put("pName", p.getName());
				jobj.put("imageUrl", "www.baidu.com"); // TODO 商品图！
				jobj.put("orderedCount", oi.getNumber());
				jobj.put("pPrice", p.getOriginalPrice());
				jary.put(jobj);
			}
			resp.getWriter().println(jary.toString());
		}
	}
	
	
}
