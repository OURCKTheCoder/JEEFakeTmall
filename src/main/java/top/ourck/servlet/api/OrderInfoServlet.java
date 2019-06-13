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

import top.ourck.beans.OrderItem;
import top.ourck.beans.User;
import top.ourck.service.OrderItemService;

/**
 * 得到用户订单信息的API
 * TODO Authority?
 * @author ourck
 */
@WebServlet("/order/info")
public class OrderInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 7885017565969746458L;
	private OrderItemService oiService = new OrderItemService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User)req.getAttribute("rememberedUser");
		int uid = user.getId();
		List<OrderItem> oList = oiService.listCommitedByUserId(uid);
		JSONArray jary = new JSONArray();
		
		for(OrderItem oi : oList) {
			JSONObject jobj = new JSONObject();
			jobj.put("orderCode", oi.getOrder().getOrderCode());
			jobj.put("orderContent", oi.getProduct().getName() + " * " + oi.getNumber());
			jobj.put("totalprice", oi.getNumber() + oi.getProduct().getOriginalPrice());
			jary.put(jobj);
		}
		
		resp.setContentType("application/json");
		resp.getWriter().println(jary.toString());
	}
	

}
