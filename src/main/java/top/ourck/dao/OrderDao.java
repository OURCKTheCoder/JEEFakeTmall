package top.ourck.dao;


import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import top.ourck.beans.Order;
import top.ourck.beans.User;
import top.ourck.utils.TimeUtils;
import top.ourck.utils.JDBCConnectionFactory;

/**
 * 订单DAO类
 * （样板代码实在是受不了了就直接用了原版）
 * <a href="http://how2j.cn/k/tmall-j2ee/tmall-j2ee-1009/1009.html#nowhere">出处</a>
 */
public class OrderDao implements SimpleDao<Order>{
	public static final String WAIT_PAY = "waitPay";
	public static final String WAIT_DELIVERY = "waitDelivery";
	public static final String WAIT_CONFIRM = "waitConfirm";
	public static final String WAIT_REVIEW = "waitReview";
	public static final String FINISH = "finish";
	public static final String DELETE = "delete";
	
    public int getTotal() {
        int total = 0;
        String sql = "select count(*) from order_";
        try (Connection c = JDBCConnectionFactory.getConnection();
        		Statement s = c.createStatement();) {
        	
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) 
            	total = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
 
    public void add(Order bean) {

        try (Connection c = JDBCConnectionFactory.getConnection();
        		Statement ps = c.createStatement();) {
        	Timestamp createDate = TimeUtils.d2t(bean.getCreateDate());
        	Timestamp payDate = TimeUtils.d2t(bean.getPayDate());
        	Timestamp deliveryDate = TimeUtils.d2t(bean.getDeliveryDate());
        	Timestamp confirmDate = TimeUtils.d2t(bean.getConfirmDate());
        	
        	String sql = "insert into order_ values("
        			+ "null, "
        			+ "\'" + bean.getOrderCode() + "\', "
        			+ "\'" + bean.getAddress() + "\', "
        			+ "\'" + bean.getPost() + "\', "
        			+ "\'" + bean.getReceiver() + "\', "
        			+ "\'" + bean.getMobile() + "\', "
        			+ "\'" + bean.getUserMessage() + "\', "
//        			+ "\'" + DBUtils.d2t(bean.getCreateDate()) + "\', "
//        			+ "\'" + DBUtils.d2t(bean.getPayDate()) + "\', "
//        			+ "\'" + DBUtils.d2t(bean.getDeliveryDate()) + "\', "
//        			+ "\'" + DBUtils.d2t(bean.getConfirmDate()) + "\', "
        			+ (createDate == null ? "null" : "\'" + createDate + "\', ")
        			+ (payDate == null ? "null" : "\'" + payDate + "\', ")
        			+ (deliveryDate == null ? "null" : "\'" + deliveryDate + "\', ")
        			+ (confirmDate == null ? "null" : "\'" + confirmDate + "\', ")
        			+ "\'" + bean.getUser().getId() + "\', "
        			+ "\'" + bean.getStatus() + "\')";
        	
            ps.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
 
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public void update(Order bean) {
        String sql = "update order_ "
        		+ "set address= ?, post=?, receiver=?,mobile=?,userMessage=? ,createDate = ?,"
        		+ " payDate =? , deliveryDate =?, confirmDate = ? , orderCode =?, uid=?, status=? "
        		+ "where id = ?";
        try (Connection c = JDBCConnectionFactory.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql);) {
        	
            ps.setString(1, bean.getAddress());
            ps.setString(2, bean.getPost());
            ps.setString(3, bean.getReceiver());
            ps.setString(4, bean.getMobile());
            ps.setString(5, bean.getUserMessage());
            ps.setTimestamp(6, TimeUtils.d2t(bean.getCreateDate()));
            ps.setTimestamp(7, TimeUtils.d2t(bean.getPayDate()));
            ps.setTimestamp(8, TimeUtils.d2t(bean.getDeliveryDate()));
            ps.setTimestamp(9, TimeUtils.d2t(bean.getConfirmDate()));
            ps.setString(10, bean.getOrderCode());
            ps.setInt(11, bean.getUser().getId());
            ps.setString(12, bean.getStatus());
            ps.setInt(13, bean.getId());
            
            ps.execute();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
    }
 
    public void delete(int id) {
    	String sql = "delete from order_ where id = " + id;
        try (Connection c = JDBCConnectionFactory.getConnection();
        		Statement s = c.createStatement();) {
        	
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    public Order query(int id) {
        Order bean = new Order();
        String sql = "select * from order_ where id = " + id;
        try (Connection c = JDBCConnectionFactory.getConnection();
        		Statement s = c.createStatement();) {
 
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
            	String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String post = rs.getString("post");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                int uid =rs.getInt("uid");
                Date createDate = TimeUtils.t2d(rs.getTimestamp("createDate"));
                Date payDate = TimeUtils.t2d(rs.getTimestamp("payDate"));
                Date deliveryDate = TimeUtils.t2d(rs.getTimestamp("deliveryDate"));
                Date confirmDate = TimeUtils.t2d(rs.getTimestamp("confirmDate"));

                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setPost(post);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setUserMessage(userMessage);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                bean.setDeliveryDate(deliveryDate);
                bean.setConfirmDate(confirmDate);
                User user = new UserDao().query(uid);
                bean.setUser(user);
                bean.setStatus(status);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
 
    public List<Order> list() {
        return list(0, Short.MAX_VALUE);
    }
 
    public List<Order> list(int start, int count) {
        List<Order> beans = new ArrayList<Order>();
        String sql = "select * from order_ order by id desc limit ?,? ";
 
        try (Connection c = JDBCConnectionFactory.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order bean = new Order();
            	String orderCode =rs.getString("orderCode");
                String address = rs.getString("address");
                String post = rs.getString("post");
                String receiver = rs.getString("receiver");
                String mobile = rs.getString("mobile");
                String userMessage = rs.getString("userMessage");
                String status = rs.getString("status");
                Date createDate = TimeUtils.t2d(rs.getTimestamp("createDate"));
                Date payDate = TimeUtils.t2d(rs.getTimestamp("payDate"));
                Date deliveryDate = TimeUtils.t2d(rs.getTimestamp("deliveryDate"));
                Date confirmDate = TimeUtils.t2d(rs.getTimestamp("confirmDate"));
                int uid = rs.getInt("uid");                
                
                int id = rs.getInt("id");
                bean.setId(id);
                bean.setOrderCode(orderCode);
                bean.setAddress(address);
                bean.setPost(post);
                bean.setReceiver(receiver);
                bean.setMobile(mobile);
                bean.setUserMessage(userMessage);
                bean.setCreateDate(createDate);
                bean.setPayDate(payDate);
                bean.setDeliveryDate(deliveryDate);
                bean.setConfirmDate(confirmDate);
                User user = new UserDao().query(uid);
                bean.setUser(user);
                bean.setStatus(status);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }
    
    public List<Order> list(int uid) {
        return list(uid, 0, Short.MAX_VALUE);
    }
     
    public List<Order> list(int uid, int start, int count) {
    	List<Order> beans = new ArrayList<Order>();
    	String sql = "select * from order_ where uid = ? order by id desc limit ?,? ";

    	try (Connection c = JDBCConnectionFactory.getConnection();
    			PreparedStatement ps = c.prepareStatement(sql);) {
    		ps.setInt(1, uid);
    		ps.setInt(2, start);
    		ps.setInt(3, count);
    		
    		ResultSet rs = ps.executeQuery();
    		
    		while (rs.next()) {
    			Order bean = new Order();
    			String orderCode =rs.getString("orderCode");
    			String address = rs.getString("address");
    			String post = rs.getString("post");
    			String receiver = rs.getString("receiver");
    			String mobile = rs.getString("mobile");
    			String userMessage = rs.getString("userMessage");
    			String status = rs.getString("status");
    			Date createDate = TimeUtils.t2d(rs.getTimestamp("createDate"));
    			Date payDate = TimeUtils.t2d(rs.getTimestamp("payDate"));
    			Date deliveryDate = TimeUtils.t2d(rs.getTimestamp("deliveryDate"));
    			Date confirmDate = TimeUtils.t2d(rs.getTimestamp("confirmDate"));
               
    			int id = rs.getInt("id");
    			bean.setId(id);
    			bean.setOrderCode(orderCode);
    			bean.setAddress(address);
    			bean.setPost(post);
    			bean.setReceiver(receiver);
    			bean.setMobile(mobile);
    			bean.setUserMessage(userMessage);
    			bean.setCreateDate(createDate);
    			bean.setPayDate(payDate);
    			bean.setDeliveryDate(deliveryDate);
    			bean.setConfirmDate(confirmDate);
    			User user = new UserDao().query(uid);
    			bean.setStatus(status);
    			bean.setUser(user);
    			beans.add(bean);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return beans;
    }

}