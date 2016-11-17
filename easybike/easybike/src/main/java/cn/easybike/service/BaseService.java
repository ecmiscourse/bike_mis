package cn.easybike.service;

import java.util.List;

/**
* 技改项目服务层类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月17日下午3:39:17 马辉 新建
*/
public interface BaseService<T> {
	/**
	* getByID
	* @param id
	* @return 返回实体
	*/
	T get(int id);
	/**
	* 保存实体信息
	* @param entity
	* @return 无返回值
	*/
	void save(T entity);
	
	/**
	* 修改实体
	* @param entity
	* @return 无返回值
	*/
	void update(T entity);
	
	/**
	* 删除实体
	* @param entity
	* @return 无返回值
	*/
	void delete(T entity);
	
	/**
	* 根据ID删除实体
	* @param id
	* @return 无返回值
	*/
	void delete(int id);
	/**
	* 获取所有实体
	* @param entityClazz实体
	* @return 返回实体list集合
	*/
	List<T> queryAll();
	/**
	* 根据分页获取数据
	* @param hql语句
	* @param pageNo 当前页
	* @param pageSize 一页显示行数
	* @return 返回实体list集合
	*/
	List<T> queryByPage(String hql , int pageNo, int pageSize);
	/**
	* 获取实体总数
	* @param entityClazz
	* @return 返回long
	*/
	long countAll();
	
	/**
	* 根据条件查询记录数
	* @param hql语句
	* @return 返回long
	*/
	long countByHql(String hql);
}
