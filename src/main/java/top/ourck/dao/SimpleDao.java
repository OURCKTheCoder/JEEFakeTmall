package top.ourck.dao;

import java.util.List;

public interface SimpleDao<T> {

	void add(T obj);
	void delete(T obj);
	void update(T obj);
	T query(int id);
	
	List<T> list(T obj, int start, int count);
	List<T> list();
	int getTotal(T obj);
}
