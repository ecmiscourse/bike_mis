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
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月18日下午9:50:58 马辉 新建
*/
@Repository("baseDao")
@SuppressWarnings("unchecked")
@Lazy(true)
public class BaseDaoImpl<T> implements BaseDao<T> {
	private Class clazz; 
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
	* @return 
	*/
	
	@Override
	public T get(int id) {
		return(T)getSession().get(clazz, id);
	}
	/**
	* @param entity
	* @return ֵ
	*/
	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	/**
	* 
	* @param entity
	* @returnֵ
	*/
	@Override
	public void update(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	* 
	* @param entity
	* @return ֵ
	*/
	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	* 
	* @param id
	* @return ֵ
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
	* 
	* @param 
	* @return
	*/
	
	@Override
	public List<T> queryAll() {
		String hql=" SELECT en FROM "+ clazz.getSimpleName() + " en";
		return (List<T>)getSession()
				.createQuery(hql)
				.list();
	}

	/**
	*
	* @param hql
	* @param pageNo
	* @param pageSize 
	* @return
	*/
	@Override
	public List<T> queryByPage(String hql, int pageNo, int pageSize) {
		return getSession()
				.createQuery(hql)
				.setFirstResult((pageNo - 1) * pageSize)//����ÿҳ��ʼ�ļ�¼���
				.setMaxResults(pageSize)//������Ҫ��ѯ���������
				.list();
	}

	/**
	* 
	* @param entityClazz
	* @return long
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
	* 
	* @param hql
	* 
	* @return 
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
