package top.ourck.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * TODO Maybe use a connection pool?
 * @author ourck
 */
public class JDBCConnectionFactory {
	
	private static final String IP_ADDR = "ourck.top"; 
	private static final String PORT = "3306";
	private static final String USER_NAME = "tmaller";
	private static final String PASSWD = "tmallisgreat!";
	private static final String PARAMS = "?serverTimezone=UTC&characterEncoding=utf-8";
	private static final String DEFAULT_DB = "tmall";
	private static final String CONN_URL = "jdbc:mysql://" + IP_ADDR + ":" + PORT + "/" + DEFAULT_DB + PARAMS;
	
	static { // Initialize at static loading.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException { // TODO Singleton maybe?
		return DriverManager.getConnection(CONN_URL, USER_NAME, PASSWD);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
