package top.ourck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import top.ourck.beans.Product;
import top.ourck.beans.ProductImage;
import top.ourck.utils.JDBCConnectionFactory;

public class ProductImageDao implements SimpleDao<ProductImage>{
 
	public static final String type_single = "type_single";
	public static final String type_detail = "type_detail";
	private static final String TABLE_NAME = " productimage ";
	
    public int getTotal() {
        int total = 0;
        try (Connection c = JDBCConnectionFactory.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from" + TABLE_NAME;
 
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return total;
    }
 
    public void add(ProductImage bean) {



        String sql = "insert into" + TABLE_NAME + "values(null,?,?)";
        try (Connection c = JDBCConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, bean.getProduct().getId());
            ps.setString(2, bean.getType());
            ps.execute();
 
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
 
    // TODO
    public void update(ProductImage bean) {
 
    }
 
    public void delete(int id) {
 
        try (Connection c = JDBCConnectionFactory.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "delete from" + TABLE_NAME + "where id = " + id;
 
            s.execute(sql);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
 
    @Override
    public ProductImage query(int id) {
        ProductImage bean = new ProductImage();

        try (Connection c = JDBCConnectionFactory.getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select * from" + TABLE_NAME + "where id = " + id;
 
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {
                int pid = rs.getInt("pid");
                String type = rs.getString("type");
                Product product = new ProductDao().query(pid);
                bean.setProduct(product);
                bean.setType(type);
                bean.setId(id);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;
    }
 
    public List<ProductImage> list(Product p, String type) {
        return list(p, type,0, Short.MAX_VALUE);
    }
 
    public List<ProductImage> list(Product p, String type, int start, int count) {
        List<ProductImage> beans = new ArrayList<ProductImage>();
 
        String sql = "select * from" + TABLE_NAME + "where pid =? and type =? order by id desc limit ?,? ";
 
        try (Connection c = JDBCConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, p.getId());
            ps.setString(2, type);
 
            ps.setInt(3, start);
            ps.setInt(4, count);
            
            
            
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {

                ProductImage bean = new ProductImage();
                int id = rs.getInt(1);


                bean.setProduct(p);
                bean.setType(type);
                bean.setId(id);
                  
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

	@Override
	public List<ProductImage> list(int start, int count) {
		List<ProductImage> pList = new LinkedList<ProductImage>();
		String sql = "SELECT * FROM" + TABLE_NAME + "ORDER BY id DESC LIMIT ?, ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, start);
			stmt.setInt(2, count);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ProductImage pi = new ProductImage();
				pi.setId(rs.getInt("id"));
				pi.setType(rs.getString("type"));
				pi.setProduct(new ProductDao().query(rs.getInt("pid")));
				pList.add(pi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pList;
	}

    @Override
    public List<ProductImage> list() {
        return list(0, Short.MAX_VALUE);
    }
 
}