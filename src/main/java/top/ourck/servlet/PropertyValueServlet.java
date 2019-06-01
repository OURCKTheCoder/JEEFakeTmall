package top.ourck.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.ourck.beans.Product;
import top.ourck.beans.Property;
import top.ourck.beans.PropertyValue;
import top.ourck.service.ProductService;
import top.ourck.service.PropertyService;

/** TODO 分页！！！
 * http://how2j.cn/k/tmall-j2ee/tmall-j2ee-1000/1000.html#nowhere
 * @author ourck
 *
 */
@WebServlet("/propertyValueServlet")
public class PropertyValueServlet extends HttpServlet {

	private static final long serialVersionUID = 8855917531787405078L;

	private ProductService productService = new ProductService();
	private PropertyService propertyService = new PropertyService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		
		if(op != null) {
			if(op.equals("edit")) {
				int pid = Integer.parseInt(request.getParameter("pid"));
				Product p = productService.getById(pid);
				List<Property> pList = propertyService.getPropertiesByCategoryId(p.getCategory().getId());
				List<PropertyValue> pvList = 
						propertyService.getPropertyValueByProductId(p.getId());
				// 用一层HashMap优化查询速度
				Map<Integer, PropertyValue> pvMap = getWrappedPropertyValueMap(pvList);
				request.setAttribute("product", p);
				request.setAttribute("pList", pList);
				request.setAttribute("pvMap", pvMap);
				request.getRequestDispatcher("propertyValue.jsp").forward(request, response);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = (String) request.getAttribute("op");
		
		if(op != null) {
			if(op.equals("update")) {
				int pid = Integer.parseInt(request.getParameter("pid"));
				Product p = productService.getById(pid);
				List<Property> pts = propertyService.getPropertiesByCategoryId(p.getCategory().getId());
				for(Property property : pts) { // TODO 也许可以优化？
					PropertyValue pv = new PropertyValue();
					pv.setProduct(productService.getById(pid));
					pv.setProperty(property);
					String value = request.getParameter("pt_" + property.getId());
					pv.setValue(value);
					propertyService.updateOrAddPropertyValue(pv);
				}
				response.sendRedirect("propertyValue_edit?pid=" + pid);
			}
		}
	}

	/**
	 * 将PropertyValue的List包装为HashMap<br>
	 * 以提供"根据Property的id就可取得PropertyValue"的O(1)查询特性。
	 * @param pvList PropertyValue的List
	 * @return 包装好的Map
	 */
	private Map<Integer, PropertyValue> getWrappedPropertyValueMap(List<PropertyValue> pvList) {
		Map<Integer, PropertyValue> pvMap = new HashMap<Integer, PropertyValue>();
		for(PropertyValue pv : pvList) {
			pvMap.put(pv.getProperty().getId(), pv);
		}
		return pvMap;
	}
}
