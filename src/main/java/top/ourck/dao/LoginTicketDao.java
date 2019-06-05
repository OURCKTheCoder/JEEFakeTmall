package top.ourck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import top.ourck.beans.LoginTicket;
import top.ourck.utils.JDBCConnectionFactory;
import top.ourck.utils.TimeUtils;

public class LoginTicketDao implements SimpleDao<LoginTicket> {

	private static final String SELECT_FIELDS = " id, user_id, ticket, expired, status ";
	private static final String TABLE_NAME = " login_ticket ";
	
	private UserDao userDao = new UserDao();
	
	@Override
	public void add(LoginTicket obj) {
		String sql = "INSERT INTO" + TABLE_NAME + "VALUES (null, ?, ?, ?, ?)";
		try (Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, obj.getUser().getId());
			ps.setString(2, obj.getTicket());
			ps.setTimestamp(3, TimeUtils.d2t(obj.getExpired()));
			ps.setInt(4, obj.getStatus());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				obj.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM" + TABLE_NAME + "WHERE id = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(LoginTicket obj) {
		String sql = "UPDATE login_ticket SET "
				+ "user_id = ?, "
				+ "ticket = ?, "
				+ "expired = ?, "
				+ "status = ? "
				+ "WHERE id = ?";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, obj.getUser().getId());
			ps.setString(2, obj.getTicket());
			ps.setTimestamp(3, TimeUtils.d2t(obj.getExpired()));
			ps.setInt(4, obj.getStatus());
			ps.setInt(5, obj.getId());
			
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public LoginTicket query(int id) {
		LoginTicket ticket = null;
        String sql = "SELECT" + SELECT_FIELDS + "FROM" + TABLE_NAME + "WHERE id = ?";
        try (Connection c = JDBCConnectionFactory.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql);) {
        	ps.setInt(1, id);
        	ResultSet rs = ps.executeQuery();
        	if(rs.next()) {
        		ticket = new LoginTicket();
        		ticket.setId(rs.getInt(1));
        		ticket.setUser(userDao.query(rs.getInt(2)));
        		ticket.setTicket(rs.getString(3));
        		ticket.setExpired(rs.getDate(4)); // TODO 用不用d2t?
        		ticket.setStatus(rs.getInt(5));
        	}
        } catch(SQLException e) {
        	e.printStackTrace();
        }
        return ticket;
	}
	@Override
	public List<LoginTicket> list(int start, int count) {
		List<LoginTicket> ticketList = new LinkedList<LoginTicket>();
        String sql = "SELECT" + SELECT_FIELDS + "FROM" + TABLE_NAME + "LIMIT ?, ?";
        try (Connection c = JDBCConnectionFactory.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql);) {
        	ps.setInt(1, start);
        	ps.setInt(2, count);
        	ResultSet rs = ps.executeQuery();
        	while(rs.next()) {
        		LoginTicket ticket = new LoginTicket();
        		ticket.setId(rs.getInt(1));
        		ticket.setUser(userDao.query(rs.getInt(2)));
        		ticket.setTicket(rs.getString(3));
        		ticket.setExpired(rs.getDate(4)); // TODO 用不用d2t?
        		ticket.setStatus(rs.getInt(5));
        		ticketList.add(ticket);
        	}
        } catch(SQLException e) {
        	e.printStackTrace();
        }
        return ticketList;
	}
	@Override
	public List<LoginTicket> list() {
		List<LoginTicket> ticketList = new LinkedList<LoginTicket>();
        String sql = "SELECT" + SELECT_FIELDS + "FROM" + TABLE_NAME;
        try (Connection c = JDBCConnectionFactory.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql);) {
        	ResultSet rs = ps.executeQuery();
        	while(rs.next()) {
        		LoginTicket ticket = new LoginTicket();
        		ticket.setId(rs.getInt(1));
        		ticket.setUser(userDao.query(rs.getInt(2)));
        		ticket.setTicket(rs.getString(3));
        		ticket.setExpired(rs.getDate(4)); // TODO 用不用d2t?
        		ticket.setStatus(rs.getInt(5));
        		ticketList.add(ticket);
        	}
        } catch(SQLException e) {
        	e.printStackTrace();
        }
        return ticketList;
	}
	
	@Override
	public int getTotal() {
		String sql = "SELECT COUNT(*) FROM" + TABLE_NAME;
		int count = -1;
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void updateStatus(int newStatus, String t) {
		LoginTicket ticket = getByTicketString(t);
		ticket.setStatus(newStatus);
		update(ticket);
	}
	
	public LoginTicket getByTicketString(String t) {
		LoginTicket ticket = null;
        String sql = "SELECT" + SELECT_FIELDS + "FROM" + TABLE_NAME + "WHERE ticket = ?";
        try (Connection c = JDBCConnectionFactory.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql);) {
        	ps.setString(1, t);
        	ResultSet rs = ps.executeQuery();
        	if(rs.next()) {
        		ticket = new LoginTicket(); // `ticket` field is UNIQUE in DB.
        		ticket.setId(rs.getInt(1));
        		ticket.setUser(userDao.query(rs.getInt(2)));
        		ticket.setTicket(rs.getString(3));
        		ticket.setExpired(rs.getDate(4)); // TODO 用不用d2t?
        		ticket.setStatus(rs.getInt(5));
        	}
        } catch(SQLException e) {
        	e.printStackTrace();
        }
        return ticket;
	}
}
