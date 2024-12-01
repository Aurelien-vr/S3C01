package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	
	public T findOne(long id);
	public List<T> findAll();
	void create(T entity);
	void update(T entity);
	void delete(T entity);
	void deleteById(T entity);
	T createEntities(ResultSet result) throws SQLException;
}
