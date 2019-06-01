package top.ourck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import top.ourck.beans.Category;
import top.ourck.utils.JDBCConnectionFactory;

/**
 * FIXME 修复 #所有# Dao类使用的Statement为PreparedStatement.
 * @author ourck
 *
 */
public class CategoryDao implements SimpleDao<Category> {

	// Category table has only 2 columns: (id, name).
	
	@Override
	public void add(Category obj) {
		String sql = "INSERT INTO category values(null, ?)"; 
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, obj.getName());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys(); // Fill in the empty id field back of bean.
			if(rs.next()) {
				int id = rs.getInt(1);
				obj.setId(id);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM category WHERE id = " + id;
		try(Connection conn = JDBCConnectionFactory.getConnection();
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Category obj) {
		String sql = "UPDATE category set name = ? WHERE id = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, obj.getName());
			stmt.setInt(2, obj.getId());
			
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Category query(int id) {
		Category c = null;
		String sql = "SELECT * FROM category WHERE id = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) { // Only 1 item.
				c = new Category();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public List<Category> list(int start, int count) {
		List<Category> list = new LinkedList<>();
		String sql = "SELECT * FROM category ORDER BY id DESC LIMIT ?, ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement s = conn.prepareStatement(sql)) {
			s.setInt(1, start);
			s.setInt(2, count);
			ResultSet rs = s.executeQuery();
			
			while(rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<Category> list() {
		return list(0, Short.MAX_VALUE);
	}

	@Override
	public int getTotal() {
		int n = 0;
		String sql = "SELECT COUNT(*) FROM category";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				Statement stmt = conn.createStatement()) {
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
	
	public String getNameById(int id) {
		String name = null;
		String sql = "SELECT name FROM category WHERE id = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			//n = rs.getFetchSize();
			while(rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
}
