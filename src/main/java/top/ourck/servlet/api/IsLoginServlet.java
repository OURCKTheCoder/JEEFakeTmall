package top.ourck.servlet.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import top.ourck.beans.User;

@WebServlet("/user/islogin")
public class IsLoginServlet extends HttpServlet{

	private static final long serialVersionUID = 7964381910033264844L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		User user = (User) req.getAttribute("rememberedUser");
		JSONObject jsobj = new JSONObject();
		if(user == null) {
			jsobj.put("success", "false");
		}
		else {
			jsobj.put("success", "true");
			jsobj.put("user", user.getName());
		}
		
		resp.setContentType("application/json");
		resp.getWriter().print(jsobj);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
	}
	
}
