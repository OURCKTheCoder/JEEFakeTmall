package top.ourck.servlet.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import top.ourck.utils.TimeUtils;

@WebServlet("/admin/orderServlet")
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
				request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
			}
			else if(op.equals("delete")) {
				orderItemService.delete(Integer.parseInt(request.getParameter("id")));
				response.sendRedirect("./order_list");
			}
			else if(op.equals("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				OrderItem item = orderItemService.getById(id);
				request.setAttribute("orderItem", item);
				request.getRequestDispatcher("/admin/orderUpdate.jsp").forward(request, response);
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		if(op != null) {
			if(op.equals("add")) {
				Integer pid = Integer.parseInt(request.getParameter("pid"));
				Integer uid = Integer.parseInt(request.getParameter("uid"));
				Integer number = Integer.parseInt(request.getParameter("number"));
				
				Product p = productService.getById(pid);
				User u = userService.getById(uid);
				Order o = null;
				try {
					DateFormat format = new SimpleDateFormat(TimeUtils.DATE_PATTERN);
					String order_orderCode = request.getParameter("order_orderCode");
					String order_address = request.getParameter("order_address");
					String order_post = request.getParameter("order_post");
					String order_receiver = request.getParameter("order_receiver");
					String order_mobile = request.getParameter("order_mobile");
					String order_userMessage = request.getParameter("order_userMessage");
					Date order_createDate = request.getParameter("order_createDate").equals("") ? 
											null : format.parse(request.getParameter("order_createDate"));
					Date order_payDate = request.getParameter("order_payDate").equals("") ? 
											null : format.parse(request.getParameter("order_payDate"));
					Date order_deliveryDate = request.getParameter("order_deliveryDate").equals("") ? 
											null : format.parse(request.getParameter("order_deliveryDate"));
					Date order_confirmDate = request.getParameter("order_confirmDate").equals("") ? 
											null : format.parse(request.getParameter("order_confirmDate"));
					String order_status = request.getParameter("order_status");
					o = new Order(u, order_orderCode, order_address, order_post, order_receiver, order_mobile, order_userMessage,
							order_createDate, order_payDate, order_deliveryDate, order_confirmDate, order_status);
				} catch (ParseException e) {
					e.printStackTrace();
					throw new IOException("DateFormat illeagal!");
				}
				
				orderService.add(o);
				orderItemService.add(p, u, o, number);
				response.sendRedirect("./order_list");
			}
			else if(op.equals("update")) {
				Integer id = Integer.parseInt(request.getParameter("id"));
				Integer pid = Integer.parseInt(request.getParameter("pid"));
				Integer uid = Integer.parseInt(request.getParameter("uid"));
				Integer number = Integer.parseInt(request.getParameter("number"));
				
				Product p = productService.getById(pid);
				User u = userService.getById(uid);
				Order o = null;
				try {
					DateFormat format = new SimpleDateFormat(TimeUtils.DATE_PATTERN);
					Integer order_uid = Integer.parseInt(request.getParameter("order_uid"));
					String order_orderCode = request.getParameter("order_orderCode");
					String order_address = request.getParameter("order_address");
					String order_post = request.getParameter("order_post");
					String order_receiver = request.getParameter("order_receiver");
					String order_mobile = request.getParameter("order_mobile");
					String order_userMessage = request.getParameter("order_userMessage");
					Date order_createDate = request.getParameter("order_createDate").equals("") ? 
											null : format.parse(request.getParameter("order_createDate"));
					Date order_payDate = request.getParameter("order_payDate").equals("") ? 
											null : format.parse(request.getParameter("order_payDate"));
					Date order_deliveryDate = request.getParameter("order_deliveryDate").equals("") ? 
											null : format.parse(request.getParameter("order_deliveryDate"));
					Date order_confirmDate = request.getParameter("order_confirmDate").equals("") ? 
											null : format.parse(request.getParameter("order_confirmDate"));
					String order_status = request.getParameter("order_status");
					o = new Order(u, order_orderCode, order_address, order_post, order_receiver, order_mobile, order_userMessage,
							order_createDate, order_payDate, order_deliveryDate, order_confirmDate, order_status);
					o.setId(order_uid);
				} catch (ParseException e) {
					e.printStackTrace();
					throw new IOException("DateFormat illeagal!");
				}
				
				orderService.update(o);
				OrderItem newItem = new OrderItem(p, u, o, number);
				newItem.setId(id);
				orderItemService.update(newItem);
				response.sendRedirect("./order_list");
			}
		}
	}
}
