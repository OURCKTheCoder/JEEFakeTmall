package top.ourck.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.ourck.beans.Category;
import top.ourck.beans.Product;
import top.ourck.service.CategoryService;
import top.ourck.service.ProductService;

@WebServlet("/productServlet")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 8855917531787405078L;

	private ProductService productService = new ProductService();
	private CategoryService categoryService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		if(op != null) {
			if(op.equals("list")) {
				List<Product> uList = productService.list();
				request.setAttribute("list", uList);
				request.getRequestDispatcher("/product.jsp").forward(request, response);
			}
			else if(op.equals("delete")) {
				productService.delete(Integer.parseInt(request.getParameter("id")));
				response.sendRedirect("./product_list");
			}
			else if(op.equals("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Product p = productService.getById(id);
				request.setAttribute("p", p);
				request.getRequestDispatcher("/productUpdate.jsp").forward(request, response);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		if(op != null) {
			if(op.equals("add")) {
				String name = request.getParameter("name");
				String subTitle = request.getParameter("subTitle");
				Float originalPrice = Float.parseFloat(request.getParameter("originalPrice"));
				Float promotePrice = Float.parseFloat(request.getParameter("promotePrice"));
				Integer stock = Integer.parseInt(request.getParameter("stock"));
				Integer categoryId = Integer.parseInt(request.getParameter("category"));
				Date createDate = new Date();
				
				Category c = categoryService.getById(categoryId);
				productService.add(name, subTitle, originalPrice, promotePrice, stock, c, createDate);
				response.sendRedirect("./product_list");
			}
			else if(op.equals("update")) {
				Integer id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String subTitle = request.getParameter("subTitle");
				Float originalPrice = Float.parseFloat(request.getParameter("originalPrice"));
				Float promotePrice = Float.parseFloat(request.getParameter("promotePrice"));
				Integer stock = Integer.parseInt(request.getParameter("stock"));
				Integer categoryId = Integer.parseInt(request.getParameter("category"));
				Date createDate = new Date();
				
				Category c = categoryService.getById(categoryId);
				productService.update(id, name, subTitle, originalPrice, promotePrice, stock, c, createDate);
				response.sendRedirect("./product_list");
			}
		}
	}

}
