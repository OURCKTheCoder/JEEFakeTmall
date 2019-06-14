package top.ourck.servlet.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import top.ourck.beans.Product;
import top.ourck.beans.PropertyValue;
import top.ourck.service.ProductService;
import top.ourck.service.PropertyService;

/**
 * 查看商品细节的API
 * @author ourck
 */
@WebServlet("/product/detail")
public class ProductDetailServlet extends HttpServlet {

	private static final long serialVersionUID = -8152307407054163312L;
	private ProductService productService = new ProductService();
	private PropertyService propService = new PropertyService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pid = Integer.parseInt(req.getParameter("pid"));
		Product p = productService.getById(pid);
//		List<Property> propList = propService.getPropertiesByCategoryId(p.getCategory().getId());
		// 上边这个没必要了。因为下面那个方法返回的Bean已经持有对应Property的引用了。
		List<PropertyValue> pvList = propService.getPropertyValueByProductId(p.getId());
		
		JSONObject jsonProduct = new JSONObject();
		JSONArray props = new JSONArray();
		for(PropertyValue pv : pvList) {
			JSONObject jobj = new JSONObject();
			String key = pv.getProperty().getName();
			String value = pv.getValue();
			jobj.put(key, value);
			props.put(jobj);
		}
		jsonProduct.put("name", p.getName());
		jsonProduct.put("description", p.getSubTitle());
		
		resp.setContentType("application/json");
	}

	
}
