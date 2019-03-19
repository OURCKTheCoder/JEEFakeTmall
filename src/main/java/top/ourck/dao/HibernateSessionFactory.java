package top.ourck.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import top.ourck.beans.User;

public class HibernateSessionFactory {
	
	private static Configuration CONF;
	private static ServiceRegistry SERV_REG;
	private static SessionFactory SESSION_FACTORY;
	private static final ThreadLocal<Session> THREAD_LOCAL_BUF = new ThreadLocal<Session>(); // Session is thread-unsafe!
	
	static {
		SERV_REG = new StandardServiceRegistryBuilder().configure().build();
		CONF = new Configuration().configure();
		try {
			SESSION_FACTORY = new MetadataSources(SERV_REG).buildMetadata().buildSessionFactory();
		} catch(HibernateException e) {
			System.err.println("[!] Something happened with Hibernate's initialization!");
			throw e;
		}
	}
	
	private HibernateSessionFactory() {}
	
	public static Session getSession() {
		Session session = THREAD_LOCAL_BUF.get();
		if(session == null || !session.isOpen()) {
			session = SESSION_FACTORY.openSession();
		}
		return session;
	}
	
	public static void closeSession() {
		Session session = THREAD_LOCAL_BUF.get();
		if(session != null) session.close();
	}
	
	
	public static SessionFactory getFactory() { 
		return SESSION_FACTORY; 
	}
	
	public static Configuration getConfiguration() {
		return CONF;
	}
	
	public static void main(String[] args) {
		Configuration conf = new Configuration().configure(); //HibernateSessionFactory.getConfiguration();
		conf.addClass(User.class);
		SessionFactory factory = conf.buildSessionFactory();
		
		Session session = factory.openSession();
		
		// The show goes on!
		User user = new User("Jack", "123456");
		
		// Update current transaction.
		session.save(user);
		
		// Submit transaction.
		session.beginTransaction().commit();
		
		// --Finished! Check if success.
		Query<User> query = session.createQuery("FROM User", User.class);
		List<User> userToQueryFor = query.list();
		for(User u : userToQueryFor) System.out.println(u.getName());
	}
}
