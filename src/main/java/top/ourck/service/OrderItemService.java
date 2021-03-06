package top.ourck.service;

import java.util.List;

import top.ourck.beans.Order;
import top.ourck.beans.OrderItem;
import top.ourck.beans.Product;
import top.ourck.beans.User;
import top.ourck.dao.OrderItemDao;

public class OrderItemService {

	private OrderItemDao orderItemDao = new OrderItemDao();
	
	public List<OrderItem> list() {
		return orderItemDao.list();
	}
	
	public List<OrderItem> listCartByUserId(int uid) {
		return orderItemDao.listCartByUserId(uid);
	}
	
	public List<OrderItem> listCommitedByUserId(int uid) {
		return orderItemDao.listCommitedByUserId(uid);
	}
	
	public OrderItem getByUidPid(int uid, int pid) {
		return orderItemDao.getCartByUidPid(uid, pid);
	}
	
	public void add(OrderItem item) {
		OrderItem origin = orderItemDao.getCartByUidPid(item.getUser().getId(), item.getProduct().getId());
		if(origin == null) {
			orderItemDao.add(item);
		}
		else {
			origin.setNumber(origin.getNumber() + 1);
			orderItemDao.update(origin);
		}
		
	}
	
	
	public void update(OrderItem item) {
		orderItemDao.update(item);
	}
	

	public void add(Product p,
					User u,
					Order o,
					Integer n) {
		OrderItem item = new OrderItem();
		item.setProduct(p);
		item.setUser(u);
		item.setOrder(o);
		item.setNumber(n);
		
		add(item);
	}

	public void update(Integer id,
						Product p,
						User u,
						Order o,
						Integer n) {
		OrderItem item = new OrderItem();
		item.setId(id);
		item.setProduct(p);
		item.setUser(u);
		item.setOrder(o);
		item.setNumber(n);
		
		update(item);
	}	
	
	public void delete(int id) {
		orderItemDao.delete(id);
	}
	
	public void delete(int pid, int uid) {
		
	}
	
	public OrderItem getById(int id) {
		return orderItemDao.query(id);
	}
	
}
