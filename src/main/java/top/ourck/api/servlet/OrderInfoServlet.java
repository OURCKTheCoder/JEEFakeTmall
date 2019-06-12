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

import top.ourck.beans.Order;
import top.ourck.service.OrderService;

/**
 * Get order info by userId
 * TODO Authority?
 * @author ourck
 *
 */
@WebServlet("/order/info")
public class OrderInfoServlet extends HttpServlet {

	private OrderService orderService = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int uid = Integer.parseInt(req.getParameter("uid"));
		List<Order> oList = orderService.getListByUserId(uid);
		JSONArray jary = new JSONArray();
		
		for(Order o : oList) {
			JSONObject jobj = new JSONObject();
			// TODO 前端要什么？
			
		}
	}
	

}
