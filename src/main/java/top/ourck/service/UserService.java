package top.ourck.service;

import java.util.List;

import top.ourck.beans.User;
import top.ourck.dao.UserDao;

public class UserService {
	
	private UserDao userDao = new UserDao();
	
	public List<User> list() {
		return userDao.list();
	}
	
	public boolean isExist(String userName) {
		return userDao.get(userName) != null; // Maybe EXISTS in sql ?
	}
	
	public void add(String name, String passwd) {
		User user = new User();
		user.setName(name);
		user.setPassword(passwd);
		userDao.add(user);
	}
	
	public void delete(int id) {
		userDao.delete(id);
	}
	
	public void update(int id, String name, String passwd) {
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setPassword(passwd);
		userDao.update(user);
	}
	
	public User getById(int id) {
		return userDao.query(id);
	}
	
	public String getNameById(int id) {
		User user = userDao.query(id);
		return user == null ? null : user.getName();
	}
}
