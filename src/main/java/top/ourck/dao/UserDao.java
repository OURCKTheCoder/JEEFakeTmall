package top.ourck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import top.ourck.beans.User;
import top.ourck.utils.JDBCConnectionFactory;

public class UserDao implements SimpleDao<User> {

	// User table has 3 columns: (id, name, password).
	
	@Override
	public void add(User obj) {
		String sql = "INSERT INTO user values(null, ?, ?)"; 
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
		String sql = "DELETE FROM user WHERE id = " + id;
		try(Connection conn = JDBCConnectionFactory.getConnection();
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User obj) {
		String sql = "UPDATE user set name = ?, password = ? WHERE id = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {			
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
		String sql = "SELECT * FROM user WHERE id = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {			
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
		String sql = "SELECT * FROM user ORDER BY id DESC LIMIT ?, ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement s = conn.prepareStatement(sql)) {
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
		String sql = "SELECT COUNT(*) FROM user";
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

	public User get(String userName) {
		User user = null;
		String sql = "SELECT * FROM user WHERE name = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {	
			
			stmt.setString(1, userName);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public boolean isExist(String userName) {
		return get(userName) != null; // Maybe EXISTS in sql ?
	}
	
	public User get(String userName, String pwd) {
		User user = null;
		String sql = "SELECT * FROM user WHERE name = ?, password = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {			
			stmt.setString(1, userName);
			stmt.setString(2, pwd);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
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
