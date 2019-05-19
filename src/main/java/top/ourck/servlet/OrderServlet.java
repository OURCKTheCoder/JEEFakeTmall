package top.ourck.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.ourck.beans.Order;
import top.ourck.beans.OrderItem;
import top.ourck.beans.Product;
import top.ourck.beans.User;
import top.ourck.service.OrderItemService;
import top.ourck.service.OrderService;
import top.ourck.service.ProductService;
import top.ourck.service.UserService;

@WebServlet("/orderServlet")
public class OrderServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8601392259411168996L;

	private OrderItemService orderItemService = new OrderItemService();
	private ProductService productService = new ProductService();
	private UserService userService = new UserService();
	private OrderService orderService = new OrderService();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		if(op != null) {
			if(op.equals("list")) {
				List<OrderItem> uList = orderItemService.list();
				request.setAttribute("list", uList);
				request.getRequestDispatcher("/order.jsp").forward(request, response);
			}
			else if(op.equals("delete")) {
				orderItemService.delete(Integer.parseInt(request.getParameter("id")));
				response.sendRedirect("./order_list");
			}
			else if(op.equals("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				OrderItem item = orderItemService.getById(id);
				request.setAttribute("orderItem", item);
				request.getRequestDispatcher("/orderUpdate.jsp").forward(request, response);
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		if(op != null) {
			if(op.equals("add")) {
				// TODO 不能直接搞个oid完事！还要接收订单细节的值。
				Integer pid = Integer.parseInt(request.getParameter("pid"));
				Integer uid = Integer.parseInt(request.getParameter("uid"));
				Integer oid = Integer.parseInt(request.getParameter("oid"));
				Integer number = Integer.parseInt(request.getParameter("number"));
				
				Product p = productService.getById(pid);
				User u = userService.getById(uid);
				Order o = orderService.getById(oid);
				
				orderItemService.add(p, u, o, number);
				response.sendRedirect("./order_list");
			}
			else if(op.equals("update")) {
				// TODO 不能直接搞个oid完事！还要接收订单细节的值。
				Integer id = Integer.parseInt(request.getParameter("id"));
				Integer pid = Integer.parseInt(request.getParameter("pid"));
				Integer uid = Integer.parseInt(request.getParameter("uid"));
				Integer oid = Integer.parseInt(request.getParameter("oid"));
				Integer number = Integer.parseInt(request.getParameter("number"));
				
				
				
				Product p = productService.getById(pid);
				User u = userService.getById(uid);
				Order o = orderService.getById(oid);

				orderItemService.update(id, p, u, o, number);
				response.sendRedirect("./order_list");
			}
		}
	}
}
