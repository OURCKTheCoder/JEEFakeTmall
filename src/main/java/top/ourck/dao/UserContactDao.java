package top.ourck.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import top.ourck.beans.UserContact;
import top.ourck.utils.JDBCConnectionFactory;

//User_contact table has 5 columns: (id, phone, address, emailaddress, name, uid).

public class UserContactDao implements SimpleDao<UserContact> {

	@Override
	public void add(UserContact obj) {
		String sql="INSERT INTO user_contact values(null,?,?,?,?,?)";
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql)){
			stmt.setString(1,obj.getPhone());
			stmt.setString(2, obj.getAddress());
			stmt.setString(3, obj.getEmailaddress());
			stmt.setString(4, obj.getName());
			stmt.setInt(5, obj.getUid());
			
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FORM user_contact WHERE id = ?";
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql);){
			stmt.setInt(1 , id);
			
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteByUid(int uid) {
		String sql = "DELETE FORM user_contact WHERE uid = ?";
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql);){
			stmt.setInt(1 , uid);
			
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(UserContact obj) {
		String sql = "UPDATE user_contact set phone = ?,address = ?,emailaddress = ?,name = ?,uid = ?";
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql)){
			stmt.setString(1, obj.getPhone());
			stmt.setString(2, obj.getAddress());
			stmt.setString(3, obj.getEmailaddress());
			stmt.setString(4, obj.getName());
			stmt.setInt(5, obj.getUid());
			
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserContact query(int id) {
		UserContact uc = null;
		
		String sql = "SELECT * FROM user_contact WHERE id = ?";
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql)){
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				uc=new UserContact();
				uc.setPhone(rs.getString(2));
				uc.setAddress(rs.getString(3));
				uc.setEmailaddress(rs.getString(4));
				uc.setName(rs.getString(5));
				uc.setUid(rs.getInt(6));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return uc;
	}

	public UserContact queryByUid(int uid) {
		UserContact uc = null;
		
		String sql = "SELECT * FROM user_contact WHERE uid = ?";
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql)){
			stmt.setInt(1, uid);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				uc=new UserContact();
				uc.setPhone(rs.getString(2));
				uc.setAddress(rs.getString(3));
				uc.setEmailaddress(rs.getString(4));
				uc.setName(rs.getString(5));
				uc.setUid(rs.getInt(6));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return uc;
	}
	
	@Override
	public List<UserContact> list(int start, int count) {
		List<UserContact> list = new LinkedList<>();
		
		String sql = "SELECT * FROM user_contact ORDER BY id DESC LIMIT ?,?";
		try(PreparedStatement s = JDBCConnectionFactory.getConnection()
				 .prepareStatement(sql)) {
			
			s.setInt(1, start);
			s.setInt(2, count);
			ResultSet rs = s.executeQuery();
			
			while(rs.next()) {
				UserContact c = new UserContact();
				c.setPhone(rs.getString(1));
				c.setAddress(rs.getString(2));
				c.setEmailaddress(rs.getString(3));
				c.setName(rs.getString(4));
				c.setUid(rs.getInt(5));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<UserContact> list() {
		return list(0, Short.MAX_VALUE);
	}

	@Override
	public int getTotal() {
		int count = 0;
		String sql = "SELECT COUNT(*) FORMT user_contact";
		
		try(PreparedStatement s = JDBCConnectionFactory.getConnection()
				 .prepareStatement(sql)) {
			
			ResultSet rs = s.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	public UserContact get(int uid)
	{
		UserContact uc = null;
		
		String sql = "SELECT * FROM user_contact WHERE uid = ?";
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql)){
			stmt.setInt(1, uid);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				uc=new UserContact();
				uc.setPhone(rs.getString(1));
				uc.setAddress(rs.getString(2));
				uc.setEmailaddress(rs.getString(3));
				uc.setName(rs.getString(4));
				uc.setUid(rs.getInt(5));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return uc;
	}
}
