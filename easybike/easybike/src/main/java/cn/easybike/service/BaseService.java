package cn.easybike.service;

import java.util.List;


/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月18日下午9:55:40 马辉 新建
*/
public interface BaseService<T> {
	
	T get(int id);
	
	void save(T entity);
	
	
	void update(T entity);
	
	
	void delete(T entity);
	
	
	void delete(int id);
	
	List<T> queryAll();
	
	List<T> queryByPage(String hql , int pageNo, int pageSize);
	
	long countAll();
	
	long countByHql(String hql);
}
