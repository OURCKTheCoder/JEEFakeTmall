package top.ourck.servlet.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import top.ourck.beans.OrderItem;
import top.ourck.beans.User;
import top.ourck.service.OrderItemService;
import top.ourck.service.ProductService;

/**
 * 处理用户购物车更新的API
 * @author ourck
 */
@WebServlet("/cart/update")
public class CartUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1255804248750307997L;
	private OrderItemService oiService = new OrderItemService();
	private ProductService productService = new ProductService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("op");
		if(op.equals("")) // TODO 非法访问
			return;
		
		JSONObject jobj = null;
		if(op.equals("add")) {
			int pid = Integer.parseInt(req.getParameter("pid")); // FIXME null!
			int count = Integer.parseInt(req.getParameter("num"));
			User user = (User)req.getAttribute("rememberedUser");
			
			OrderItem oi = new OrderItem();
			oi.setProduct(productService.getById(pid));
			oi.setUser(user);
			oi.setOrder(null);
			oi.setNumber(count);
			oiService.add(oi);
			
			jobj = new JSONObject();
			jobj.put("success", true);
			jobj.put("oiid", oi.getId()); // 返回订单项目ID给前端，以后对该订单项目的操作根据oiid来。
		}
		else if (op.equals("delete")) {
			int oiid = Integer.parseInt(req.getParameter("oiid"));
			oiService.delete(oiid);
			jobj = new JSONObject();
			jobj.put("success", true);
		}
		
		resp.setContentType("application/json");
		resp.getWriter().println(jobj.toString());
	}


}
