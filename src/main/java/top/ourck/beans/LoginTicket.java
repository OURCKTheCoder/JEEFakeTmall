package top.ourck.beans;

import java.util.Date;

public class LoginTicket {

	private int id;
	private User user;
	private String ticket;
	private Date expired;
	private int status;
	
	public LoginTicket() {
	}

	public LoginTicket(int id, User user, String ticket, Date expired, int status) {
		super();
		this.id = id;
		this.user = user;
		this.ticket = ticket;
		this.expired = expired;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
