package top.ourck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import top.ourck.beans.Category;
import top.ourck.beans.Property;
import top.ourck.utils.JDBCConnectionFactory;

public class PropertyDao implements SimpleDao<Property> {

	// property: (id, cid, name)
	
	@Override
	public void add(Property obj) {
		String sql = "INSERT INTO property values(null, ?, ?)"; 
		try(Connection conn = JDBCConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, obj.getCategory().getId());
			stmt.setString(2, obj.getName());
			
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys(); // Fill in the empty id field back of bean.
			if(rs.next()) {
				int id = rs.getInt(1);
				obj.setId(id);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	@Override
	public void delete(int id) {
		try(Connection conn = JDBCConnectionFactory.getConnection()) {
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM property WHERE id = " + id;
			stmt.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(Property obj) {
		String sql = "UPDATE property set cid = ?, name = ? WHERE id = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, obj.getCategory().getId());
			stmt.setString(2, obj.getName());
			stmt.setInt(3, obj.getId());
			
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Property query(int id) {
		Property u = null;
		String sql = "SELECT * FROM property WHERE id = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) { // Only 1 item.
				u = new Property();
				Category c = new CategoryDao().query(rs.getInt("cid"));
				u.setId(rs.getInt("id"));
				u.setCategory(c);
				u.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public List<Property> list(int start, int count) {
		List<Property> list = new LinkedList<>();
		String sql = "SELECT * FROM property ORDER BY id DESC LIMIT ?, ?";
		try(Connection conn = JDBCConnectionFactory.getConnection()) {
			PreparedStatement s = conn.prepareStatement(sql);
			s.setInt(1, start);
			s.setInt(2, count);
			
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				Property p = new Property();
				Category c = new CategoryDao().query(rs.getInt("cid"));
				p.setId(rs.getInt("id"));
				p.setCategory(c);
				p.setName(rs.getString("name"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Property> list() {
		return list(0, Short.MAX_VALUE);
	}

	@Override
	public int getTotal() {
		int n = 0;
		String sql = "SELECT COUNT(*) FROM property";
		try(Connection conn = JDBCConnectionFactory.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//n = rs.getFetchSize();
			while(rs.next()) {
				n = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	// OURCK: Business relative.
	
	public List<Property> list(int cid) {
        return list(cid, 0, Short.MAX_VALUE);
    }
  
    public List<Property> list(int cid, int start, int count) {
        List<Property> beans = new LinkedList<Property>();
  
        String sql = "select * from Property where cid = ? order by id desc limit ?,? ";
  
        try (Connection c = JDBCConnectionFactory.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setInt(1, cid);
            ps.setInt(2, start);
            ps.setInt(3, count);
  
            ResultSet rs = ps.executeQuery();
  
            while (rs.next()) {
                Property bean = new Property();
                int id = rs.getInt(1);
                String name = rs.getString("name");
                bean.setName(name);
                Category category = new CategoryDao().query(cid);
                bean.setCategory(category);
                bean.setId(id);
                 
                beans.add(bean);
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
        return beans;
    }
	
	public static void main(String[] args) {
		Category cat = new Category("ZZZZZ");
		cat.setId(83);
		Property c = new Property(cat, "Tets");
		PropertyDao cd = new PropertyDao();
		
		cd.add(c);
		cd.delete(c.getId());
		c.setName("ASDFGHJKL");
		cd.update(c);
		System.out.println(cd.query(c.getId()));
		
		System.out.println(cd.getTotal() + " " + cd.list());
	}
	
}
