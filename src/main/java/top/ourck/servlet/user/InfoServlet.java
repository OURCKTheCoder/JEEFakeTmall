package top.ourck.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import top.ourck.beans.User;
import top.ourck.beans.UserContact;
import top.ourck.service.UserContactService;

@WebServlet("/user/info")
public class InfoServlet extends HttpServlet{

	private static final long serialVersionUID = 7857902996115670353L;
	private UserContactService usercontactservice = new UserContactService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user;
		user = (User) req.getAttribute("rememberedUser");
		
		// FIXME 判空！
		UserContact usercontact = usercontactservice.getByUid(user.getId());

		JSONObject jsobj = new JSONObject();
		jsobj.put("phone", usercontact.getPhone());
		jsobj.put("address", usercontact.getAddress());
		jsobj.put("emailaddress", usercontact.getEmailaddress());
		jsobj.put("name", usercontact.getName());
		
		resp.getWriter().print(jsobj);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User)req.getAttribute("remeberedUser");
		String phone = (String) req.getAttribute("phone");
		String address = (String) req.getAttribute("address");
		String emailaddress = (String) req.getAttribute("emailaddress");
		String name = (String)req.getAttribute("name");
		
		usercontactservice.add(phone, address, emailaddress, name, user.getId());
	}
	
}
