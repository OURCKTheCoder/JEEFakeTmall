package top.ourck.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.ourck.beans.Category; // TODO Coupling?
import top.ourck.service.CategoryService;

@WebServlet("/admin/categoryServlet")
public class CategoryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 3812941669672146055L;

	private CategoryService categoryService = new CategoryService();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String op = (String) request.getAttribute("op");
		if(op != null) {
			if(op.equals("list")) {
				List<Category> uList = categoryService.list();
				request.setAttribute("list", uList);
				request.getRequestDispatcher("/admin/category.jsp").forward(request, response);
			}
			else if(op.equals("delete")) {
				categoryService.delete(Integer.parseInt(request.getParameter("id")));
				response.sendRedirect("./category_list");
			}
			else if(op.equals("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("id", id);
				request.setAttribute("oldName", categoryService.getNameById(id));
				request.getRequestDispatcher("/admin/categoryUpdate.jsp").forward(request, response);
			}
		}
//		response.sendRedirect("./category.jsp"); // DO NOT DO THIS!
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String op = (String) request.getAttribute("op");
		if(op != null) {
			if(op.equals("add")) {
				String name = request.getParameter("name");
				categoryService.add(name);
				response.sendRedirect("./category_list");
			}
			else if(op.equals("update")) {
				String id = request.getParameter("id");
				String name = request.getParameter("name");
				categoryService.update(Integer.parseInt(id), name);
				response.sendRedirect("./category_list");
			}
		}
	}

}
