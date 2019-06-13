package top.ourck.service;

import java.util.List;

import top.ourck.beans.UserContact;
import top.ourck.dao.UserContactDao;

public class UserContactService {
	private UserContactDao usercontactdao = new UserContactDao();
	
	public List<UserContact> list(){
		return usercontactdao.list();
	}
	
	public boolean isExist(int uid) {
		return usercontactdao.get(uid) != null;
	}
	
	public void add(String phone,String address,String emailaddress,String name,int uid) {
		UserContact usercontact = new UserContact();
		usercontact.setPhone(phone);
		usercontact.setAddress(address);
		usercontact.setEmailaddress(emailaddress);
		usercontact.setName(name);
		usercontact.setUid(uid);
		
		usercontactdao.add(usercontact);
	}
	
	public void delete(int id) {
		usercontactdao.delete(id);
	}
	
	public void deleteByUid(int uid) {
		usercontactdao.deleteByUid(uid);
	}
	
	public void update(int id,String phone,String address,String emailaddress,String name,int uid) {
		UserContact usercontact = new UserContact();
		usercontact.setPhone(phone);
		usercontact.setAddress(address);
		usercontact.setEmailaddress(emailaddress);
		usercontact.setName(name);
		usercontact.setUid(uid);
		
		usercontactdao.update(usercontact);
	}
	
	public UserContact getByUid(int uid) {
		return usercontactdao.queryByUid(uid);
	}
}
