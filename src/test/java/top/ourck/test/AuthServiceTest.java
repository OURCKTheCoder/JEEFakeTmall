package top.ourck.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import top.ourck.beans.User;
import top.ourck.dao.LoginTicketDao;
import top.ourck.dao.UserDao;
import top.ourck.service.AuthService;
import top.ourck.utils.JDBCConnectionFactory;

public class AuthServiceTest {

	private static final int SAMPLE_USER_ID = 1;
	private static final String SAMPLE_USER_NAME = "Jack";
	private static final String SAMPLE_USER_PWD = "233";
	
	private LoginTicketDao tDao = new LoginTicketDao();
	private UserDao dao = new UserDao();
	private User obj; // Sample user.
	private AuthService authService = new AuthService();
	private String ticket;
	
	@BeforeEach
	void beforeTest() {
		obj = new User();
		obj.setId(SAMPLE_USER_ID);
		obj.setName(SAMPLE_USER_NAME);
		obj.setPassword(SAMPLE_USER_PWD);
		
		String sql = "INSERT INTO user values(?, ?, ?)"; 
		try(PreparedStatement stmt = JDBCConnectionFactory.getConnection()
				.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, obj.getId());
			stmt.setString(2, obj.getName());
			stmt.setString(3, obj.getPassword());
			
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
	
	@AfterEach
	void afterTest() {
		dao.delete(SAMPLE_USER_ID);
	}
	
	@Test
	void getAuthTest() {
		Map<String, String> info = authService.getAuth(SAMPLE_USER_NAME, "XXX");
		assertEquals("false", info.get("success"));
		
		Map<String, String> info2 = authService.getAuth(SAMPLE_USER_NAME, SAMPLE_USER_PWD);
		assertEquals("true", info2.get("success"));
		ticket = info2.get("ticket");
		assertNotNull(ticket);
		assertNotNull(tDao.getByTicketString(ticket));
		
		assertEquals(0, tDao.getByTicketString(ticket).getStatus());
		authService.logout(ticket);
		assertEquals(1, tDao.getByTicketString(ticket).getStatus());
	}
}
