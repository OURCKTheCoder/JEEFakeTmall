package top.ourck.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import top.ourck.beans.Product;
import top.ourck.beans.Property;
import top.ourck.beans.PropertyValue;
import top.ourck.utils.JDBCConnectionFactory;
  
public class PropertyValueDao implements SimpleDao<PropertyValue>{
  
    public int getTotal() {
        int total = 0;
        try (Connection c = JDBCConnectionFactory.getConnection(); Statement s = c.createStatement();) {
            String sql = "select count(*) from propertyvalue";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
  
    public void add(PropertyValue bean) {
        String sql = "insert into propertyvalue values(null,?,?,?)";
        try (Connection c = JDBCConnectionFactory.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, bean.getProduct().getId());
            ps.setInt(2, bean.getProperty().getId());
            ps.setString(3, bean.getValue());
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
  
    public void update(PropertyValue bean) {
        String sql = "update propertyvalue set pid= ?, ptid=?, value=?  where id = ?";
        try (Connection c = JDBCConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, bean.getProduct().getId());
            ps.setInt(2, bean.getProperty().getId());
            ps.setString(3, bean.getValue());
            ps.setInt(4, bean.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
  
    }
  
    public void delete(int id) {
        try (Connection c = JDBCConnectionFactory.getConnection(); Statement s = c.createStatement();) {
            String sql = "delete from propertyvalue where id = " + id;
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  
    public PropertyValue query(int id) {
        PropertyValue bean = null;
        try (Connection c = JDBCConnectionFactory.getConnection(); Statement s = c.createStatement();) {
            String sql = "select * from propertyvalue where id = " + id;
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {
            	bean = new PropertyValue();
                int pid = rs.getInt("pid");
                int ptid = rs.getInt("ptid");
                String value = rs.getString("value");
                 
                Product product = new ProductDao().query(pid);
                Property property = new PropertyDao().query(ptid);
                bean.setProduct(product);
                bean.setProperty(property);
                bean.setValue(value);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return bean;
    }
    public PropertyValue query(int productId, int propertyId) {
    	PropertyValue pv = null;
    	String sql = "SELECT * FROM propertyvalue WHERE pid = ? AND ptid = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, productId);
			ps.setInt(2, propertyId);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				pv = new PropertyValue();
				ProductDao productDao = new ProductDao();
				PropertyDao propertyDao = new PropertyDao();
				pv.setId(rs.getInt(1));
				pv.setProduct(productDao.query(rs.getInt(2)));
				pv.setProperty(propertyDao.query(rs.getInt(3)));
				pv.setValue(rs.getString(4));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
			
		return pv;
	}

	public PropertyValue get(int ptid, int pid ) {
        PropertyValue bean = null;
         
        try (Connection c = JDBCConnectionFactory.getConnection(); Statement s = c.createStatement();) {
             
            String sql = "select * from propertyvalue where ptid = " + ptid + " and pid = " + pid;
             
            ResultSet rs = s.executeQuery(sql);
             
            if (rs.next()) {
                bean= new PropertyValue();
                int id = rs.getInt("id");
 
                String value = rs.getString("value");
                 
                Product product = new ProductDao().query(pid);
                Property property = new PropertyDao().query(ptid);
                bean.setProduct(product);
                bean.setProperty(property);
                bean.setValue(value);
                bean.setId(id);
            }
             
        } catch (SQLException e) {
             
            e.printStackTrace();
        }
        return bean;
    }
  
    public List<PropertyValue> list() {
        return list(0, Short.MAX_VALUE);
    }
  
    public List<PropertyValue> list(int start, int count) {
        List<PropertyValue> beans = new ArrayList<PropertyValue>();
  
        String sql = "select * from propertyvalue order by id desc limit ?,? ";
  
        try (Connection c = JDBCConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, start);
            ps.setInt(2, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                PropertyValue bean = new PropertyValue();
                int id = rs.getInt(1);
 
                int pid = rs.getInt("pid");
                int ptid = rs.getInt("ptid");
                String value = rs.getString("value");
                 
                Product product = new ProductDao().query(pid);
                Property property = new PropertyDao().query(ptid);
                bean.setProduct(product);
                bean.setProperty(property);
                bean.setValue(value);
                bean.setId(id);           
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
 
    public void init(Product p) {
        List<Property> pts= new PropertyDao().list(p.getCategory().getId());
         
        for (Property pt: pts) {
            PropertyValue pv = get(pt.getId(),p.getId());
            if(null==pv){
                pv = new PropertyValue();
                pv.setProduct(p);
                pv.setProperty(pt);
                this.add(pv);
            }
        }
    }
 
    public List<PropertyValue> list(int pid) {
        List<PropertyValue> beans = new ArrayList<PropertyValue>();
         
        String sql = "select * from propertyvalue where pid = ? order by ptid desc";
  
        try (Connection c = JDBCConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, pid);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                PropertyValue bean = new PropertyValue();
                int id = rs.getInt(1);
 
                int ptid = rs.getInt("ptid");
                String value = rs.getString("value");
                 
                Product product = new ProductDao().query(pid);
                Property property = new PropertyDao().query(ptid);
                bean.setProduct(product);
                bean.setProperty(property);
                bean.setValue(value);
                bean.setId(id);           
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }

}