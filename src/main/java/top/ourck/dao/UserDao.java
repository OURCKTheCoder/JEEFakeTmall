package top.ourck.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import top.ourck.beans.User;
import top.ourck.utils.JDBCconnectionFactory;

public class UserDao implements SimpleDao<User> {

	// User table has 3 columns: (id, name, password).
	
	@Override
	public void add(User obj) {
		String sql = "INSERT INTO user values(null, ?, ?)"; 
		try {
			PreparedStatement stmt = JDBCconnectionFactory.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, obj.getName());
			stmt.setString(2, obj.getPassword());
			
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
		try {
			Statement stmt = JDBCconnectionFactory.getConnection().createStatement();
			String sql = "DELETE FROM user WHERE id = " + id;
			stmt.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User obj) {
		String sql = "UPDATE user set name = ?, password = ? WHERE id = ?";
		try {
			PreparedStatement stmt = JDBCconnectionFactory.getConnection().prepareStatement(sql);
			
			stmt.setString(1, obj.getName());
			stmt.setString(2, obj.getPassword());
			stmt.setInt(3, obj.getId());
			
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User query(int id) {
		User u = null;
		try {
			String sql = "SELECT * FROM user WHERE id = ?";
			PreparedStatement stmt = JDBCconnectionFactory.getConnection().prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) { // Only 1 item.
				u = new User();
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	@Override
	public List<User> list(int start, int count) {
		List<User> list = new LinkedList<>();
		try {
			String sql = "SELECT * FROM user ORDER BY id DESC LIMIT ?, ?";
			PreparedStatement s = JDBCconnectionFactory.getConnection().prepareStatement(sql);
			s.setInt(1, start);
			s.setInt(2, count);
			ResultSet rs = s.executeQuery();
			
			while(rs.next()) {
				User c = new User();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setPassword(rs.getString(3));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<User> list() {
		return list(0, Short.MAX_VALUE);
	}

	@Override
	public int getTotal() {
		int n = 0;
		try {
			Statement stmt = JDBCconnectionFactory.getConnection().createStatement();
			String sql = "SELECT COUNT(*) FROM user";
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

	public static void main(String[] args) {
		User c = new User("ZZZZZZzZZZ", "lol");
		UserDao cd = new UserDao();
		
		cd.add(c);
		cd.delete(c.getId());
		c.setName("ASDFGHJKL");
		cd.update(c);
		System.out.println(cd.query(c.getId()));
		
		System.out.println(cd.getTotal() + " " + cd.list());
	}
}
