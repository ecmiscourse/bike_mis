package cn.easybike.dao;

import java.util.List;

public interface BaseDao<T>{
	/**
	* getByID
	* @param id

	*/
	T get(int id);
	/**
	* @param entityֵ
	*/
	void save(T entity);
	
	/**
	* @param entity
	* @return �޷���ֵ
	*/
	void update(T entity);
	
	/**
	* @param entityֵ
	*/
	void delete(T entity);
	
	/**
	* @param idֵ
	*/
	void delete(int id);
	/**
	*/
	List<T> queryAll();
	/**
	*
	* @param hql
	* @param pageNo
	* @param pageSize
	* @return 
	*/
	List<T> queryByPage(String hql , int pageNo, int pageSize);
	/**
	* @return ����long
	*/
	long countAll();
	
	/**
	* 
	* @param hql
	* @return long
	*/
	long countByHql(String hql);
	
}
