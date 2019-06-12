package top.ourck.beans;

public class UserContact {
	private int id;
	private String phone;
	private String address;
	private String emailaddress;
	private String name;
	private int uid;
	
	public UserContact() {
	}
	
	public UserContact(int id, String phone, String address, String emailaddress, String name, int uid) {
		super();
		this.id = id;
		this.phone = phone;
		this.address = address;
		this.emailaddress = emailaddress;
		this.name = name;
		this.uid = uid;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmailaddress() {
		return emailaddress;
	}
	
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	

}
