package top.ourck.service;

import java.util.List;

import top.ourck.beans.Category;
import top.ourck.dao.CategoryDao;

public class CategoryService {

	private CategoryDao categoryDao = new CategoryDao();
	
	public List<Category>list() {
		return  categoryDao.list();
	}
	
	public void add(String categoryName) {
		Category c = new Category();
		c.setName(categoryName);
		categoryDao.add(c);
	}
	
	public void delete(int id) {
		categoryDao.delete(id);
	}
	
	public void update(int id, String categoryName) {
		Category c = new Category();
		c.setId(id);
		c.setName(categoryName);
		categoryDao.update(c);
	}
	
	public String getNameById(int id) {
		return categoryDao.getNameById(id);
	}
}
