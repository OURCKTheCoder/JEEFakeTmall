package top.ourck.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import top.ourck.beans.LoginTicket;
import top.ourck.beans.User;
import top.ourck.dao.LoginTicketDao;
import top.ourck.dao.UserDao;

public class AuthService {
	
	private static final int EXPIRE_SEC = 60 * 60 * 24; // 1-day man
	private UserDao userDao = new UserDao();
	private LoginTicketDao ticketDao = new LoginTicketDao();
	
	public LoginTicket getTicket(String ticket) {
		return ticketDao.getByTicketString(ticket);
	}
	/**
	 * 根据提供的用户名 / 密码作登陆校验
	 * TODO 考虑对密码用Salt加密
	 * @param userName 用户名
	 * @param passwd 密码
	 * @return 校验结果
	 */
	public Map<String, String> getAuth(String userName, String passwd) {
		User u = userDao.get(userName);
		Map<String, String> info = new HashMap<String, String>();
		if(u == null) {
			info.put("success", "false");
			info.put("msgname", "用户名不存在");
		}
		else {
			if(passwd.equals(u.getPassword())) {
				// Add ticket on both side.
				int expireSec = EXPIRE_SEC;
				LoginTicket ticket = genTicket(u.getId(), expireSec);
				info.put("success", "true");
				info.put("ticket", ticket.getTicket());
				info.put("uid", String.valueOf(u.getId()));
			}
			else {
				info.put("success", "false");
				info.put("msgpwd", "密码不正确");
				info.put("uid", String.valueOf(u.getId()));
			}
		}
		return info;
	}
	
	public void logout(String ticket) {
		ticketDao.updateStatus(1, ticket);
	}
	
	private LoginTicket genTicket(int userId, int expireSec) {
		LoginTicket t = new LoginTicket();
		t.setUser(userDao.query(userId));
		t.setStatus(0);
		Date d = new Date();
		d.setTime(d.getTime() + expireSec * 1000);
		t.setExpired(d);
		t.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
		ticketDao.add(t);
		
		return t;
	}
}
