package top.ourck.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.ourck.beans.User;
import top.ourck.service.UserService;

@WebServlet("/admin/userServlet")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService(); // TODO Coupling!
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		if(op != null) {
			if(op.equals("list")) {
				List<User> uList = userService.list();
				request.setAttribute("list", uList);
				request.getRequestDispatcher("/admin/user.jsp").forward(request, response);
			}
			else if(op.equals("delete")) {
				userService.delete(Integer.parseInt(request.getParameter("id")));
				response.sendRedirect("./user_list");
			}
			else if(op.equals("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("id", id);
				request.setAttribute("oldName", userService.getNameById(id));
				request.setAttribute("oldPwd", userService.getById(id).getPassword());
				request.getRequestDispatcher("/admin/userUpdate.jsp").forward(request, response);
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		if(op != null) {
			if(op.equals("add")) {
				String name = request.getParameter("name");
				String passwd = request.getParameter("passwd");
				userService.add(name, passwd);
				response.sendRedirect("./user_list");
			}
			else if(op.equals("update")) {
				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String passwd = request.getParameter("passwd");
				userService.update(Integer.parseInt(id), name, passwd);
				response.sendRedirect("./user_list");
			}
		}
	}
	
}
