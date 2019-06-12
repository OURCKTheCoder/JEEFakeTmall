package top.ourck.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import top.ourck.beans.Order;
import top.ourck.beans.OrderItem;
import top.ourck.beans.Product;
import top.ourck.beans.User;
import top.ourck.utils.JDBCConnectionFactory;

/**
 * 单个订单项的DAO类
 * （样板代码实在是受不了了就直接用了原版）
 * <a href="http://how2j.cn/k/tmall-j2ee/tmall-j2ee-1009/1009.html#nowhere">出处</a>
 */
public class OrderItemDao implements SimpleDao<OrderItem> {
 
	// orderitem (id, pid, oid, uid, number)
	
	@Override
    public int getTotal() {
        int total = 0;
        String sql = "select count(*) from orderitem";
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
	
	@Override
    public void add(OrderItem bean) {
		StringBuilder stb = new StringBuilder();
		stb.append("insert into orderitem values("); // orderitem (id, pid, oid, uid, number)
        try (Connection c = JDBCConnectionFactory.getConnection();
        		Statement s = c.createStatement();) {
        	stb.append("null, ");
        	stb.append(bean.getProduct().getId() + ", ");
        	// TODO 订单项在创建的时候，是没有订单信息（oid）的！！
        	if(null == bean.getOrder())	stb.append("null, ");
        	else						stb.append(bean.getOrder().getId() + ", ");  
        	stb.append(bean.getUser().getId() + ", ");
        	stb.append(bean.getNumber() + ") ");
            
            s.executeUpdate(stb.toString(), Statement.RETURN_GENERATED_KEYS);
 
            ResultSet rs = s.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
	@Override
    public void update(OrderItem bean) {

        String sql = "update orderitem set pid= ?, oid=?, uid=?,number=?  where id = ?";
        try (Connection c = JDBCConnectionFactory.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, bean.getProduct().getId());
            if(null==bean.getOrder())	ps.setInt(2, -1);
            else						ps.setInt(2, bean.getOrder().getId());            	
            ps.setInt(3, bean.getUser().getId());
            ps.setInt(4, bean.getNumber());
            ps.setInt(5, bean.getId());
            
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
    }
 
	@Override
    public void delete(int id) {
    	String sql = "delete from orderitem where id = " + id;
        try (Connection c = JDBCConnectionFactory.getConnection();
        		Statement s = c.createStatement();) {
 
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
	@Override
    public OrderItem query(int id) {
        OrderItem bean = new OrderItem();
        String sql = "select * from orderitem where id = " + id;
        try (Connection c = JDBCConnectionFactory.getConnection();
        		Statement s = c.createStatement();) {
 
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                int pid = rs.getInt("pid");
                int oid = rs.getInt("oid");
                int uid = rs.getInt("uid");
                int number = rs.getInt("number");
                Product product = new ProductDao().query(pid);
                User user = new UserDao().query(uid);
                bean.setProduct(product);
                bean.setUser(user);
                bean.setNumber(number);
                
                // If oid exists, inject the Order object into OrderItem!
                if(-1 != oid){
                    Order order = new OrderDao().query(oid);
                    bean.setOrder(order);               	
                }
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }
 
    public List<OrderItem> listByUser(int uid) {
        return listByUser(uid, 0, Short.MAX_VALUE);
    }
 
    public List<OrderItem> listByUser(int uid, int start, int count) {
        List<OrderItem> beans = new ArrayList<OrderItem>();
        String sql = "select * from orderitem where uid = ? and oid=-1 order by id desc limit ?,? "; // TODO oid = -1 ?
        try (Connection c = JDBCConnectionFactory.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, uid);
            ps.setInt(2, start);
            ps.setInt(3, count);
 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);
                int pid = rs.getInt("pid");
                int oid = rs.getInt("oid");
                int number = rs.getInt("number");
              
                Product product = new ProductDao().query(pid);
                if(-1 != oid){
                    Order order= new OrderDao().query(oid);
                    bean.setOrder(order);               	
                }

                User user = new UserDao().query(uid);
                bean.setProduct(product);

                bean.setUser(user);
                bean.setNumber(number);
                bean.setId(id);                
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }
    public List<OrderItem> listByOrder(int oid) {
    	return listByOrder(oid, 0, Short.MAX_VALUE);
    }
    
    public List<OrderItem> listByOrder(int oid, int start, int count) {
    	List<OrderItem> beans = new ArrayList<OrderItem>();
    	String sql = "select * from orderitem where oid = ? order by id desc limit ?,? ";
    	try (Connection c = JDBCConnectionFactory.getConnection();
    			PreparedStatement ps = c.prepareStatement(sql);) {
    		
    		ps.setInt(1, oid);
    		ps.setInt(2, start);
    		ps.setInt(3, count);
    		
    		ResultSet rs = ps.executeQuery();
    		while (rs.next()) {
    			OrderItem bean = new OrderItem();
    			int id = rs.getInt(1);
    			
    			int pid = rs.getInt("pid");
    			int uid = rs.getInt("uid");
    			int number = rs.getInt("number");
    			
    			Product product = new ProductDao().query(pid);
    			if(-1 != oid){
    				Order order= new OrderDao().query(oid);
    				bean.setOrder(order);               	
    			}
    			
    			User user = new UserDao().query(uid);
    			bean.setProduct(product);
    			
    			bean.setUser(user);
    			bean.setNumber(number);
    			bean.setId(id);                
    			beans.add(bean);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return beans;
    }
    
	/**
	 * "为订单设置订单项集合"<a href="http://how2j.cn/k/tmall-j2ee/tmall-j2ee-993/993.html#nowhere">here</a><br>
	 * 十有八九成是用在购物车结算上面的。
	 * @param os 订单组成的List
	 */
	public void fill(List<Order> os) {
		for (Order o : os) {
			List<OrderItem> ois = listByOrder(o.getId());
			double total = 0; // TODO Why float instead of double?
			int totalNumber = 0;
			for (OrderItem oi : ois) {
				 total += oi.getNumber() * oi.getProduct().getPromotePrice();
				 totalNumber += oi.getNumber();
			}
			o.setTotalAmount(total);
			o.setItemList(ois);
			o.setTotalCount(totalNumber);
		}
	}

	/**
	 * "为订单设置订单项集合"<a href="http://how2j.cn/k/tmall-j2ee/tmall-j2ee-993/993.html#nowhere">here</a>
	 * TODO  做什么用的？
	 * @param o 单项订单
	 */
	public void fill(Order o) {
		List<OrderItem> ois = listByOrder(o.getId());
		double total = 0;
		for (OrderItem oi : ois) {
			 total += oi.getNumber() * oi.getProduct().getPromotePrice();
		}
		o.setTotalAmount(total);
		o.setItemList(ois);
		// TODO 原文没有o.setTotalCount(totalNumber);？
	}

    public List<OrderItem> listByProduct(int pid) {
        return listByProduct(pid, 0, Short.MAX_VALUE);
    }
 
    public List<OrderItem> listByProduct(int pid, int start, int count) {
        List<OrderItem> beans = new ArrayList<OrderItem>();
        String sql = "select * from orderitem where pid = ? order by id desc limit ?,? ";
        try (Connection c = JDBCConnectionFactory.getConnection();
        		PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, pid);
            ps.setInt(2, start);
            ps.setInt(3, count);
 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderItem bean = new OrderItem();
                int id = rs.getInt(1);
                int uid = rs.getInt("uid");
                int oid = rs.getInt("oid");
                int number = rs.getInt("number");
                
                Product product = new ProductDao().query(pid);
                if(-1 != oid){
                    Order order= new OrderDao().query(oid);
                    bean.setOrder(order);               	
                }

                User user = new UserDao().query(uid);
                bean.setProduct(product);
                bean.setUser(user);
                bean.setNumber(number);
                bean.setId(id);                
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

	public int getSaleCount(int pid) {
		 int total = 0;
		 String sql = "select sum(number) from orderitem where pid = " + pid;
		 try (Connection c = JDBCConnectionFactory.getConnection();
				 Statement s = c.createStatement();) {
	 
				ResultSet rs = s.executeQuery(sql);
				while (rs.next()) {
				    total = rs.getInt(1);
				}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return total;
	}

	@Override
	public List<OrderItem> list(int start, int count) {
		List<OrderItem> oiList = new LinkedList<>();
		String sql = "SELECT * FROM orderitem ORDER BY id DESC LIMIT ?, ?";
		try (Connection conn = JDBCConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
		
			stmt.setInt(1, start);
			stmt.setInt(2, count);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) { // orderitem (id, pid, oid, uid, number)
				OrderItem oi = new OrderItem();
				oi.setId(rs.getInt("id"));
				oi.setProduct(new ProductDao().query(rs.getInt("pid")));
				oi.setOrder(new OrderDao().query(rs.getInt("oid")));
				oi.setUser(new UserDao().query(rs.getInt("uid")));
				oi.setNumber(rs.getInt("number"));
				oiList.add(oi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return oiList;
	}

	@Override
	public List<OrderItem> list() {
		return list(0, Short.MAX_VALUE);
	}
	
}