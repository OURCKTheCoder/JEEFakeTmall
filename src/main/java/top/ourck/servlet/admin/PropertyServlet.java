package top.ourck.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.ourck.beans.Category;
import top.ourck.beans.Property;
import top.ourck.service.CategoryService;
import top.ourck.service.PropertyService;

/** TODO 分页！！！
 * http://how2j.cn/k/tmall-j2ee/tmall-j2ee-1000/1000.html#nowhere
 * @author ourck
 *
 */
@WebServlet("/admin/propertyServlet")
public class PropertyServlet extends HttpServlet {

	private static final long serialVersionUID = 8855917531787405078L;

	private PropertyService propertyService = new PropertyService();
	private CategoryService categoryService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		
		if(op != null) {
			if(op.equals("list")) {
				String categoryIdStr = request.getParameter("categoryId");
				if(categoryIdStr == null || categoryIdStr.equals(""))
					throw new ServletException("Parameter categoryId is null!");
				int categoryId  = Integer.parseInt(categoryIdStr);
				Category category = categoryService.getById(categoryId);
				
				List<Property> pList = propertyService.getPropertiesByCategoryId(categoryId);
				request.setAttribute("category", category);
				request.setAttribute("list", pList);
				request.getRequestDispatcher("/admin/property.jsp").forward(request, response);
			}
			else if(op.equals("delete")) {
				String originPath = request.getHeader("Referer"); // 这里偷了个懒。。
				int id = Integer.parseInt(request.getParameter("id"));
				propertyService.deleteProperty(id);
				response.sendRedirect(originPath);
			}
			else if(op.equals("edit")) {
				String idStr = request.getParameter("id");
				int id = Integer.parseInt(idStr);
				Property p = propertyService.getById(id);
				request.setAttribute("item", p);
				request.getRequestDispatcher("/admin/propertyUpdate.jsp").forward(request, response);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		
		if(op != null) {
			if(op.equals("add")) {
				String categoryIdStr = request.getParameter("categoryId");
				if(categoryIdStr == null || categoryIdStr.equals(""))
					throw new ServletException("Parameter categoryId is null!");
				int categoryId  = Integer.parseInt(categoryIdStr);
				Category category = categoryService.getById(categoryId);
				
				String name = request.getParameter("name");
				propertyService.addProperty(category, name);
				response.sendRedirect("./property_list?categoryId=" + categoryIdStr);
			}
			else if(op.equals("update")) {
				int id = Integer.parseInt(request.getParameter("id"));
				int cid = Integer.parseInt(request.getParameter("cid"));
				String name = request.getParameter("name");
				Property p = new Property();
				p.setId(id);
				p.setName(name);
				p.setCategory(categoryService.getById(cid));
				propertyService.updateProperty(p);
				response.sendRedirect("./property_list?categoryId=" + cid);
			}
		}
	}

}
