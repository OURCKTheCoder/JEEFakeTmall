package top.ourck.dao;

import java.util.List;

public interface SimpleDao<T> {

	void add(T obj);
	void delete(int id);
	void update(T obj);
	T query(int id);
	
	List<T> list(int start, int count);
	List<T> list();
	int getTotal();
}
