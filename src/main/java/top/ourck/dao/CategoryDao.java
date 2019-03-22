package top.ourck.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import top.ourck.beans.Category;
import top.ourck.utils.JDBCconnectionFactory;

public class CategoryDao implements SimpleDao<Category> {

	@Override
	public void add(Category obj) {
		String sql = "INSERT INTO category values(null, ?)"; // Category table has only 2 columns: (id, name).
		try {
			PreparedStatement stmt = JDBCconnectionFactory.getConnection().prepareStatement(sql);
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
	public void delete(Category obj) {
		String sql = "DELETE FROM category WHERE id = ?";
		try {
			Statement stmt = JDBCconnectionFactory.getConnection().createStatement();
			stmt.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Category obj) {
		String sql = "UPDATE category set name = ? WHERE id = ?";
		try {
			PreparedStatement stmt = JDBCconnectionFactory.getConnection().prepareStatement(sql);
			
			stmt.setString(1, obj.getName());
			stmt.setInt(2, obj.getId());
			
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Category query(int id) {
		return null;
	}

	@Override
	public List<Category> list(Category obj, int start, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotal(Category obj) {
		int n = 0;
		try {
			Statement stmt = JDBCconnectionFactory.getConnection().createStatement();
			String sql = "SELECT COUNT(*) FROM category";
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

}
