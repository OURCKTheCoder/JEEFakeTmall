package top.ourck.service;

import org.json.JSONArray;

import top.ourck.dao.UserDao;

public class UserService {
	
	private static final UserDao userDao = new UserDao();
	
	public String list() {
		JSONArray jobj = new JSONArray(userDao.list());
		return jobj.toString();
	}
	
	public boolean isExist(String userName) {
		return userDao.get(userName) != null; // Maybe EXISTS in sql ?
	}
	
	public boolean getAuth(String userName, String passwd) {
		return userDao.get(userName, passwd) == null;
	}
}
