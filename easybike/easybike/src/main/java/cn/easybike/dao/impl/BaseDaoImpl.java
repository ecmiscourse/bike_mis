package cn.easybike.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import cn.easybike.dao.BaseDao;


/**
* baseDaoImpl项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月17日下午3:30:01 马辉 新建
*/
@Repository("baseDao")
@SuppressWarnings("unchecked")
@Lazy(true)
public class BaseDaoImpl<T> implements BaseDao<T> {
	private Class clazz; // clazz中存储了子类当前操作实体类型
	//获取连接
	@Resource(name="sessionFactory")
	protected SessionFactory sessionFactory;
	
	public BaseDaoImpl(){
		ParameterizedType type=(ParameterizedType)this.getClass().getGenericSuperclass();
		clazz=(Class)type.getActualTypeArguments()[0];
	}
	protected Session getSession(){
		Session session;
		try {
		    session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		}
		return session;
	}
	
	/**
	* getByID
	* @param id
	* @return 返回实体
	*/
	
	@Override
	public T get(int id) {
		return(T)getSession().get(clazz, id);
	}
	/**
	* 保存实体信息
	* @param entity
	* @return 无返回值
	*/
	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	/**
	* 修改实体
	* @param entity
	* @return 无返回值
	*/
	@Override
	public void update(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	* 删除实体
	* @param entity
	* @return 无返回值
	*/
	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	* 根据ID删除实体
	* @param id
	* @return 无返回值
	*/
	@Override
	public void delete(int id) {
		getSession()
		.createQuery("delete " + clazz.getSimpleName()
			+ " en where en.id = ?0")
		.setParameter("0" , id)
		.executeUpdate();
	}

	/**
	* 获取所有实体
	* @param 
	* @return 返回实体list集合
	*/
	
	@Override
	public List<T> queryAll() {
		String hql=" SELECT en FROM "+ clazz.getSimpleName() + " en";
		return (List<T>)getSession()
				.createQuery(hql)
				.list();
	}

	/**
	* 根据分页获取数据
	* @param hql语句
	* @param pageNo 当前页
	* @param pageSize 一页显示行数
	* @return 返回实体list集合
	*/
	@Override
	public List<T> queryByPage(String hql, int pageNo, int pageSize) {
		return getSession()
				.createQuery(hql)
				.setFirstResult((pageNo - 1) * pageSize)//设置每页起始的记录编号
				.setMaxResults(pageSize)//设置需要查询的最大结果集
				.list();
	}

	/**
	* 获取实体总数
	* @param entityClazz
	* @return 返回long
	*/
	@Override
	public long countAll() {
		List<?> l = getSession().createQuery("select count(*) from "
				+ clazz.getSimpleName()).list();
		if (l != null && l.size() == 1 )
		{
			return (Long)l.get(0);
		}
		return 0;
	}

	/**
	* 根据条件查询记录数
	* @param hql语句
	* @return 返回long
	*/
	@Override
	public long countByHql(String hql) {
		List<?> l = getSession().createQuery(hql).list();
		if (l != null && l.size() == 1 )
		{
			return (Long)l.get(0);
		}
		return 0;
	}
}
