package top.ourck.service;

import java.util.Date;
import java.util.List;

import top.ourck.beans.Order;
import top.ourck.beans.User;
import top.ourck.dao.OrderDao;

public class OrderService {

	private OrderDao orderDao = new OrderDao();
	
	public List<Order> list() {
		return orderDao.list();
	}
	
	public void add(Order order) {
		orderDao.add(order);
	}
	
	public void delete(int id) {
		orderDao.delete(id);
	}
	
	public void update(Order order) {
		orderDao.update(order);
	}
	
	public Order getById(int id) {
		return orderDao.query(id);
	}
	
	public void add(User user,
					String orderCode,
					String address,
					String post,
					String receiver,
					String phone,
					String userMsg,
					Date createDate,
					Date payDate,
					Date deliveryDate,
					Date confirmDate,
					String status) {
		Order order = new Order();
		order.setUser(user);
		order.setOrderCode(orderCode);
		order.setAddress(address);
		order.setPost(post);
		order.setReceiver(receiver);
		order.setMobile(phone);
		order.setUserMessage(userMsg);
		order.setCreateDate(createDate);
		order.setPayDate(payDate);
		order.setDeliveryDate(deliveryDate);
		order.setConfirmDate(confirmDate);
		order.setConfirmDate(confirmDate);
		order.setStatus(status);
		
		add(order);
	}
	
	public void update(Integer id,
						User user,
						String orderCode,
						String address,
						String post,
						String receiver,
						String phone,
						String userMsg,
						Date createDate,
						Date payDate,
						Date deliveryDate,
						Date confirmDate,
						String status) {
		Order order = new Order();
		order.setId(id);
		order.setUser(user);
		order.setOrderCode(orderCode);
		order.setAddress(address);
		order.setPost(post);
		order.setReceiver(receiver);
		order.setMobile(phone);
		order.setUserMessage(userMsg);
		order.setCreateDate(createDate);
		order.setPayDate(payDate);
		order.setDeliveryDate(deliveryDate);
		order.setConfirmDate(confirmDate);
		order.setConfirmDate(confirmDate);
		order.setStatus(status);
		
		update(order);
	}
}
