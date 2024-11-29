package dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T extends Serializable> {
	
	public T findOne(long id);
	public List<T> findAll();
	void create(T entity);
	void update(T entity);
	void delete(T entity);
	void deleteById(T entity);
}
