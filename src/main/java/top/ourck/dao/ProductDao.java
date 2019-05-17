package top.ourck.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import top.ourck.beans.Category;
import top.ourck.beans.Product;
import top.ourck.beans.ProductImage;
import top.ourck.utils.DBUtils;
import top.ourck.utils.JDBCConnectionFactory;

public class ProductDao implements SimpleDao<Product> {
  
    public int getTotal(int cid) {
        int total = 0;
        String sql = "select count(*) from product where cid = " + cid;
        try (Connection c = JDBCConnectionFactory.getConnection(); Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
  
    @Override
    public void add(Product bean) {
 
        try (Connection c = JDBCConnectionFactory.getConnection(); Statement s = c.createStatement();) {
        	String sql = "insert into product values("
        			+ "null, "
        			+ "\'" + bean.getName() + "\', "
        			+ "\'" + bean.getSubTitle() + "\', "
        			+ bean.getOriginalPrice() + ", "
        			+ bean.getPromotePrice() + ", "
        			+ bean.getStock() + ", "
        			+ bean.getCategory().getId() + ", "
        			+ "\'" + DBUtils.d2t(bean.getCreateDate()) + "\');";
            s.execute(sql, Statement.RETURN_GENERATED_KEYS);
  
            ResultSet rs = s.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    @Override
    public void update(Product bean) {
 
        String sql = "update product set name= ?, subTitle=?, orignalPrice=?,promotePrice=?,stock=?, cid = ?, createDate=? where id = ?";
        try (Connection c = JDBCConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getSubTitle());
            ps.setFloat(3, bean.getOriginalPrice());
            ps.setFloat(4, bean.getPromotePrice());
            ps.setInt(5, bean.getStock());
            ps.setInt(6, bean.getCategory().getId());
            ps.setTimestamp(7, DBUtils.d2t(bean.getCreateDate()));
            ps.setInt(8, bean.getId());
            ps.execute();
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
  
    }
  
    @Override
    public void delete(int id) {
    	String sql = "delete from product where id = " + id;
        try (Connection c = JDBCConnectionFactory.getConnection(); Statement s = c.createStatement();) {
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    @Override
    public Product query(int id) {
        Product bean = new Product();
        String sql = "select * from product where id = " + id;
        try (Connection c = JDBCConnectionFactory.getConnection(); Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);
  
            if (rs.next()) {
 
                String name = rs.getString("name");
                String subTitle = rs.getString("subTitle");
                float orignalPrice = rs.getFloat("orignalPrice");
                float promotePrice = rs.getFloat("promotePrice");
                int stock = rs.getInt("stock");
                int cid = rs.getInt("cid");
                Date createDate = DBUtils.t2d( rs.getTimestamp("createDate"));
               
                bean.setName(name);
                bean.setSubTitle(subTitle);
                bean.setOriginalPrice(orignalPrice);
                bean.setPromotePrice(promotePrice);
                bean.setStock(stock);
                Category category = new CategoryDao().query(cid);
                bean.setCategory(category);
                bean.setCreateDate(createDate);
                bean.setId(id);
                setFirstProductImage(bean);
            }
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
  
    public List<Product> list(int cid) {
        return list(cid,0, Short.MAX_VALUE);
    }
  
    public List<Product> list(int cid, int start, int count) {
        List<Product> beans = new ArrayList<Product>();
        Category category = new CategoryDao().query(cid);
        String sql = "select * from product where cid = ? order by id desc limit ?,? ";
  
        try (Connection c = JDBCConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, cid);
            ps.setInt(2, start);
            ps.setInt(3, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Product bean = new Product();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                String subTitle = rs.getString("subTitle");
                float orignalPrice = rs.getFloat("orignalPrice");
                float promotePrice = rs.getFloat("promotePrice");
                int stock = rs.getInt("stock");
                Date createDate = DBUtils.t2d( rs.getTimestamp("createDate"));
 
                bean.setName(name);
                bean.setSubTitle(subTitle);
                bean.setOriginalPrice(orignalPrice);
                bean.setPromotePrice(promotePrice);
                bean.setStock(stock);
                bean.setCreateDate(createDate);
                bean.setId(id);
                bean.setCategory(category);
                setFirstProductImage(bean);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }
    
    @Override
    public List<Product> list() {
        return list(0, Short.MAX_VALUE);
    }
    
    @Override
    public List<Product> list(int start, int count) {
        List<Product> beans = new ArrayList<Product>();
 
        String sql = "select * from product limit ?,? ";
  
        try (Connection c = JDBCConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Product bean = new Product();
                int id = rs.getInt(1);
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String subTitle = rs.getString("subTitle");
                float orignalPrice = rs.getFloat("orignalPrice");
                float promotePrice = rs.getFloat("promotePrice");
                int stock = rs.getInt("stock");
                Date createDate = DBUtils.t2d( rs.getTimestamp("createDate"));
 
                bean.setName(name);
                bean.setSubTitle(subTitle);
                bean.setOriginalPrice(orignalPrice);
                bean.setPromotePrice(promotePrice);
                bean.setStock(stock);
                bean.setCreateDate(createDate);
                bean.setId(id);
 
                Category category = new CategoryDao().query(cid);
                bean.setCategory(category);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }    
 
    public void fill(List<Category> cs) {
        for (Category c : cs) 
            fill(c);
    }
    public void fill(Category c) {
        List<Product> ps = this.list(c.getId());
        c.setProductList(ps);
    }
 
    public void fillByRow(List<Category> cs) {
        int productNumberEachRow = 8;
        for (Category c : cs) {
            List<Product> products =  c.getProductList();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductListByRow(productsByRow);
        }
    }
     
    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = new ProductImageDao().list(p, ProductImageDao.type_single);
        if(!pis.isEmpty())
            p.setFirstImage(pis.get(0));     
    }
     
    public void setSaleAndReviewNumber(Product p) {
        int saleCount = new OrderItemDao().getSaleCount(p.getId());
        p.setSaleCount(saleCount);          
 
        int reviewCount = new ReviewDao().getCount(p.getId());
        p.setReviewCount(reviewCount);
         
    }
 
    public void setSaleAndReviewNumber(List<Product> products) {
        for (Product p : products) {
            setSaleAndReviewNumber(p);
        }
    }
 
    public List<Product> search(String keyword, int start, int count) {
         List<Product> beans = new ArrayList<Product>();
          
         if(null == keyword || 0 == keyword.trim().length())
             return beans;
            String sql = "select * from product where name like ? limit ?,? ";
      
            try (Connection c = JDBCConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
                ps.setString(1, "%" + keyword.trim() + "%");
                ps.setInt(2, start);
                ps.setInt(3, count);
      
                ResultSet rs = ps.executeQuery();
      
                while (rs.next()) {
                    Product bean = new Product();
                    int id = rs.getInt(1);
                    int cid = rs.getInt("cid");
                    String name = rs.getString("name");
                    String subTitle = rs.getString("subTitle");
                    float orignalPrice = rs.getFloat("orignalPrice");
                    float promotePrice = rs.getFloat("promotePrice");
                    int stock = rs.getInt("stock");
                    Date createDate = DBUtils.t2d( rs.getTimestamp("createDate"));
 
                    bean.setName(name);
                    bean.setSubTitle(subTitle);
                    bean.setOriginalPrice(orignalPrice);
                    bean.setPromotePrice(promotePrice);
                    bean.setStock(stock);
                    bean.setCreateDate(createDate);
                    bean.setId(id);
 
                    Category category = new CategoryDao().query(cid);
                    bean.setCategory(category);
                    setFirstProductImage(bean);                
                    beans.add(bean);
                }
            } catch (SQLException e) {
      
                e.printStackTrace();
            }
            return beans;
    }

	@Override
	public int getTotal() {
		String sql = "SELECT COUNT(*) FROM product";
		int n = 0;
		try(Connection conn = JDBCConnectionFactory.getConnection();
				Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) n = rs.getInt(1); // Only 1 item in this query.
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return n;
	}
}