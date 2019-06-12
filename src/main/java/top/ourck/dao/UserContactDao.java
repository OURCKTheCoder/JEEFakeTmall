package top.ourck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import top.ourck.beans.User;
import top.ourck.beans.UserContact;
import top.ourck.utils.JDBCConnectionFactory;

//User_contact table has 5 columns: (id, phone, address, emailaddress, name, uid).

public class UserContactDao implements SimpleDao<UserContact> {

	@Override
	public void add(UserContact obj) {
		// TODO 自动生成的方法存根
		String sql="INSRET INTO user_contact values(null,?,?,?,?,?)";
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql)){
			stmt.setString(1,obj.getPhone());
			stmt.setString(2, obj.getAddress());
			stmt.setString(3, obj.getEmailaddress());
			stmt.setInt(4, obj.getUid());
			
			stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO 自动生成的方法存根
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
		// TODO 自动生成的方法存根
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
		// TODO 自动生成的方法存根
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
		// TODO 自动生成的方法存根
		UserContact uc = null;
		
		String sql = "SELECT * FROM user_contact WHERE id = ?";
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql)){
			stmt.setInt(1, id);
			
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
	
	@Override
	public List<UserContact> list(int start, int count) {
		// TODO 自动生成的方法存根
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
		// TODO 自动生成的方法存根
		return list(0, Short.MAX_VALUE);
	}

	@Override
	public int getTotal() {
		// TODO 自动生成的方法存根
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
