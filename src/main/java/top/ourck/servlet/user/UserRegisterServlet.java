package top.ourck.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.ourck.dao.UserDao;
import top.ourck.service.UserContactService;
import top.ourck.service.UserService;

@WebServlet("/user/register")
public class UserRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 3552792459241860467L;
	private UserService userService = new UserService();
	private UserContactService usercontactservice = new UserContactService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String account = req.getParameter("r_account");
		String passwd = req.getParameter("r_password");
		String phone = req.getParameter("r_phone");
		String address = req.getParameter("r_addr");
		String emailaddress = req.getParameter("r_email");
		String name = req.getParameter("r_name");
		
		userService.add(account, passwd);
		usercontactservice.add(phone, address, emailaddress, name, UserDao.getId(account, passwd));
		
		resp.sendRedirect("/JEEFakeTmall/login.html");
	}
	
	
}
