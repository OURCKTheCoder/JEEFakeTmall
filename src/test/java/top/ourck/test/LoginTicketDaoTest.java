package top.ourck.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import top.ourck.beans.LoginTicket;
import top.ourck.beans.User;
import top.ourck.dao.LoginTicketDao;
import top.ourck.utils.JDBCConnectionFactory;
import top.ourck.utils.TimeUtils;

public class LoginTicketDaoTest {

	private static final String SAMPLE_TICKET_STR = "ASDFGHJKL1234567890";
	private static final int SAMPLE_TICKET_ID = 1;
	private static final String TABLE_NAME = " login_ticket ";

	private User user; // Sample user.
	private LoginTicket obj; // Sample ticket.
	private LoginTicketDao dao = new LoginTicketDao();
	
	@BeforeEach
	void beforeTest() {
		user = new User();
		user.setId(1);
		user.setName("Jack");
		user.setPassword("233");
		obj = new LoginTicket();
		obj.setId(SAMPLE_TICKET_ID);
		obj.setUser(user);
		obj.setTicket(SAMPLE_TICKET_STR);
		obj.setStatus(0);
		obj.setExpired(new Date());
		
		String sql = "INSERT INTO" + TABLE_NAME + "VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, obj.getId());
			ps.setInt(2, obj.getUser().getId());
			ps.setString(3, obj.getTicket());
			ps.setTimestamp(4, TimeUtils.d2t(obj.getExpired()));
			ps.setInt(5, obj.getStatus());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				obj.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@AfterEach
	void afterTest() {
		String sql = "DELETE FROM" + TABLE_NAME + "WHERE id = 1";
		try(Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void getTest() {
		LoginTicket ticket = dao.query(1);
		assertEquals(SAMPLE_TICKET_STR, ticket.getTicket());

		LoginTicket ticket2 = dao.getByTicketString(SAMPLE_TICKET_STR);
		assertEquals(SAMPLE_TICKET_ID, ticket2.getId());
	}
	
	@Test
	void addTest() {
		LoginTicket newTicket = new LoginTicket(); // Sample ticket.
		newTicket.setUser(user);
		newTicket.setTicket("THISISATESTFORADDTICKET");
		newTicket.setStatus(0);
		newTicket.setExpired(new Date());
		
		dao.add(newTicket);
		assertNotNull(dao.getByTicketString("THISISATESTFORADDTICKET"));
		dao.delete(newTicket.getId());
	}
	
	@Test
	void updateTest() {
		obj.setTicket("HOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		dao.update(obj);
		
		assertEquals("HOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO", dao.query(1).getTicket());
	}
	
	@Test
	void deleteTest() {
		dao.delete(obj.getId());
		assertNull(dao.query(SAMPLE_TICKET_ID));
	}
	
	@Test
	void updateStatusTest() {
		dao.updateStatus(1, SAMPLE_TICKET_STR);
		int newStatus = dao.getByTicketString(SAMPLE_TICKET_STR).getStatus();
		
		assertEquals(1, newStatus);
	}
	
	@Test
	void listAndGetCountTest() {
		int size = dao.getTotal();
		List<LoginTicket> tList = dao.list();
		
		assertEquals(size, tList.size());

		List<LoginTicket> tList2 = dao.list(0, size / 2);
		
		assertEquals(size / 2, tList2.size());
	}
}
