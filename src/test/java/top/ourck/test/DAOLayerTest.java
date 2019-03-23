package top.ourck.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import top.ourck.beans.*;
import top.ourck.dao.*;

class DAOLayerTest {

	@Test
	void testCategoryDAO() {
		Category c = new Category("ZZZZZZzZZZ");
		CategoryDao cd = new CategoryDao();
		
		cd.add(c);
		assertNotNull(cd.query(c.getId()));

		c.setName("ASDFGHJKL");
		cd.update(c);
		assertNotNull(cd.query(c.getId()));
		
		cd.delete(c.getId());
		assertNull(cd.query(c.getId()));
	}
	
	@Test
	void testOrderDAO() {
		Order c = new Order();
		OrderDao cd = new OrderDao();
		
		cd.add(c);
		assertNotNull(cd.query(c.getId()));

		c.setAddress("ASDFGHJKL");
		cd.update(c);
		assertNotNull(cd.query(c.getId()));
		
		cd.delete(c.getId());
		assertNull(cd.query(c.getId()));
	}

}
