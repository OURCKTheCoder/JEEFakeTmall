package top.ourck.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCconnectionFactory {
	
	private static final String IP_ADDR = "127.0.0.1"; 
	private static final String PORT = "3306";
	private static final String USER_NAME = "root";
	private static final String PASSWD = "voidPwd039";
	private static final String PARAMS = "?serverTimezone=UTC";
	private static final String CONN_URL = "jdbc:mysql://" + IP_ADDR + ":" + PORT + PARAMS;
	
	static { // DAO�ĳ�ʼ����÷����������ڵ���ǰ
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException { // �׳��ƺ��Ǹ����õ�ѡ���õ�����Ԥ�����������
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
