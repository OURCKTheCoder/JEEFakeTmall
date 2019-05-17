package top.ourck.service;

import java.util.Date;
import java.util.List;

import top.ourck.beans.Category;
import top.ourck.beans.Product;
import top.ourck.dao.ProductDao;

public class ProductService {

	private ProductDao productDao = new ProductDao();
	
	public List<Product> list() {
		return productDao.list();
	}
	
	public void delete(int id) {
		productDao.delete(id);
	}
	
	public Product getById(int id) {
		return productDao.query(id);
	}
	
	public void add(String name,
					String subTitle,
					Float originalPrice,
					Float promotePrice,
					Integer stock,
					Category category,
					Date createDate) {
		Product p = new Product();
		p.setName(name);
		p.setSubTitle(subTitle);
		p.setOriginalPrice(originalPrice);
		p.setPromotePrice(promotePrice);
		p.setStock(stock);
		p.setCategory(category);
		p.setCreateDate(createDate);
		
		productDao.add(p);
	}
	
	public void update(int id,
						String name,
						String subTitle,
						Float originalPrice,
						Float promotePrice,
						Integer stock,
						Category category,
						Date createDate) {
		Product p = new Product();
		p.setId(id);
		p.setName(name);
		p.setSubTitle(subTitle);
		p.setOriginalPrice(originalPrice);
		p.setPromotePrice(promotePrice);
		p.setStock(stock);
		p.setCategory(category);
		p.setCreateDate(createDate);
		
		productDao.update(p);
	}
}
