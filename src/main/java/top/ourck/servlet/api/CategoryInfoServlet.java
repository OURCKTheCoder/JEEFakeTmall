package top.ourck.servlet.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import top.ourck.service.CategoryService;

@WebServlet("/category/info")
public class CategoryInfoServlet extends HttpServlet{

	private static final long serialVersionUID = -3506843039935629668L;
	private CategoryService categoryService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONArray jobj = new JSONArray(categoryService.list());
		resp.setContentType("application/json");
		resp.getWriter().println(jobj.toString());
	}

	
}
