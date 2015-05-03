package com.connsec.db.persistence;

import java.util.List;

/**
 * @author Crystal.sea
 * @param <T>
 */
public interface IBaseMapper<T> {
	
	//TODO follow function for Query
	public List<T> grid(T entity);

	public Integer count(T entity);

	public List<T> select(T entity);
	
	public List<T> query(T entity);
	
	public List<T> findAll();

	/**
	 *  query by id
	 * @param id
	 * @return one 
	 */
	public T get(String id);
	
	/**
	 * query by entity
	 * @param entity
	 * @return one
	 */
	public T load(T entity);
	
	//TODO follow function for insert update and delete
	public Integer insert(T entity);
	
	public Integer update(T entity);

	/**
	 * delete by entity parameter
	 * @param entity
	 * @return
	 */
	public Integer delete(T entity);
	/**
	 * delete by id
	 * @param id
	 * @return
	 */
	public Integer remove(String id);
	
	//TODO follow function for complex insert and delete
	public Integer batchInsert(List<T> listEntity);

	public Integer batchDelete(List<String> ids);
	
	public Integer logisticDelete(T entity);
	
	public Integer logisticBatchDelete(List<String> ids);
	
}
