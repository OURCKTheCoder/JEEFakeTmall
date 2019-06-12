package top.ourck.admin.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import top.ourck.utils.TimeUtils;

/** TODO 分页！！！
 * http://how2j.cn/k/tmall-j2ee/tmall-j2ee-1000/1000.html#nowhere
 * @author ourck
 *
 */
@WebServlet("/admin/productServlet")
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
				request.getRequestDispatcher("/admin/product.jsp").forward(request, response);
			}
			else if(op.equals("delete")) {
				productService.delete(Integer.parseInt(request.getParameter("id")));
				response.sendRedirect("./admin/product_list");
			}
			else if(op.equals("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Product p = productService.getById(id);
				request.setAttribute("p", p);
				request.getRequestDispatcher("/admin/productUpdate.jsp").forward(request, response);
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
				DateFormat format = new SimpleDateFormat(TimeUtils.DATE_PATTERN);
				
				Integer id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String subTitle = request.getParameter("subTitle");
				Float originalPrice = Float.parseFloat(request.getParameter("originalPrice"));
				Float promotePrice = Float.parseFloat(request.getParameter("promotePrice"));
				Integer stock = Integer.parseInt(request.getParameter("stock"));
				Integer categoryId = Integer.parseInt(request.getParameter("category"));
				Date createDate = null;
				try {
					createDate = format.parse(request.getParameter("createDate"));
				} catch (ParseException e) {
					e.printStackTrace();
					throw new IOException("DateFormat illeagal!");
				}
				
				Category c = categoryService.getById(categoryId);
				productService.update(id, name, subTitle, originalPrice, promotePrice, stock, c, createDate);
				response.sendRedirect("./product_list");
			}
		}
	}

}
