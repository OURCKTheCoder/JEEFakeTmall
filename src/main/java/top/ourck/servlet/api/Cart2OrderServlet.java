package top.ourck.servlet.api;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.ourck.beans.Order;
import top.ourck.beans.OrderItem;
import top.ourck.beans.User;
import top.ourck.beans.UserContact;
import top.ourck.service.OrderItemService;
import top.ourck.service.OrderService;
import top.ourck.service.UserContactService;

/**
 *  FIXME 这里只是简单做一个示例。
 * @author ourck
 */
@WebServlet("/cart/commit")
public class Cart2OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 5660921219526897268L;
	private OrderService orderService = new OrderService();
	private OrderItemService oiService = new OrderItemService();
	private UserContactService ucService = new UserContactService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User)req.getAttribute("rememberedUser");
		UserContact uc = ucService.getByUid(user.getId());
		Order o = new Order();
		o.setAddress(uc.getAddress());
		o.setConfirmDate(new Date()); // FIXME !!
		o.setCreateDate(new Date());
		o.setDeliveryDate(new Date());
		o.setPayDate(new Date());
		o.setMobile(uc.getPhone());
		o.setOrderCode("SAMPLE_ORDER233");
		o.setPost("710000");
		o.setReceiver(uc.getName());
		o.setStatus("已下单");
		o.setUser(user);
		o.setUserMessage("23333");
		orderService.add(o);
		
		Enumeration<String> ps = req.getParameterNames();
		while(ps.hasMoreElements()) {
			String pidStr = ps.nextElement();
			int count = Integer.parseInt(req.getParameter(pidStr));
			OrderItem oi = oiService.getByUidPid(user.getId(), Integer.parseInt(pidStr));
			if(oi == null)
				return; // TODO ERROR!!
			
			oi.setNumber(count);
			oi.setOrder(o);
			oiService.update(oi);
		};
		
		resp.getWriter().println("Order commited! :)"); // TODO JSON
	}
	
	
	
}
