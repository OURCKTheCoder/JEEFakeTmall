package top.ourck.service;

import java.util.List;

import top.ourck.beans.Category;
import top.ourck.beans.Property;
import top.ourck.beans.PropertyValue;
import top.ourck.dao.PropertyDao;
import top.ourck.dao.PropertyValueDao;

public class PropertyService {

	private PropertyDao propertyDao = new PropertyDao();
	private PropertyValueDao pvDao = new PropertyValueDao();
	
	public List<Property> getPropertiesByCategoryId(int categoryId) {
		return propertyDao.list(categoryId);
	}
	
	public List<PropertyValue> getPropertyValueByProductId(int productId) {
		return pvDao.list(productId);
	}
	
	public void addProperty(Category category, String name) {
		Property p = new Property();
		p.setCategory(category);
		p.setName(name);
		propertyDao.add(p);
	}
	
	public void deleteProperty(int id) {
		propertyDao.delete(id);
	}
	
	public void updateProperty(Property p) {
		propertyDao.update(p);
	}
	
	public void updateOrAddPropertyValue(PropertyValue pv) {
		PropertyValue oldpv = pvDao.query(pv.getProduct().getId(), pv.getProperty().getId());
		if(oldpv == null) {
			pvDao.add(pv);
		}
		else {
			pv.setId(oldpv.getId());
			pvDao.update(pv);
		}
	}
	
	public Property getById(int id) {
		return propertyDao.query(id);
	}
	
	
}