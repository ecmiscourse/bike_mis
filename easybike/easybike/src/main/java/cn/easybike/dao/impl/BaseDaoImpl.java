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
* baseDaoImpl��Ŀҵ�����ʵ����.ʵ��������Ŀ,ɾ����Ŀ�ȷ�����<br>
* �ṩ�Ա��ֲ�Ľӿ�.
* @author  ���
* @since   JDK1.8
* @history 2016��11��17������3:30:01 ��� �½�
*/
@Repository("baseDao")
@SuppressWarnings("unchecked")
@Lazy(true)
public class BaseDaoImpl<T> implements BaseDao<T> {
	private Class clazz; // clazz�д洢�����൱ǰ����ʵ������
	//��ȡ����
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
	* @return ����ʵ��
	*/
	
	@Override
	public T get(int id) {
		return(T)getSession().get(clazz, id);
	}
	/**
	* ����ʵ����Ϣ
	* @param entity
	* @return �޷���ֵ
	*/
	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	/**
	* �޸�ʵ��
	* @param entity
	* @return �޷���ֵ
	*/
	@Override
	public void update(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	* ɾ��ʵ��
	* @param entity
	* @return �޷���ֵ
	*/
	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	* ����IDɾ��ʵ��
	* @param id
	* @return �޷���ֵ
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
	* ��ȡ����ʵ��
	* @param 
	* @return ����ʵ��list����
	*/
	
	@Override
	public List<T> queryAll() {
		String hql=" SELECT en FROM "+ clazz.getSimpleName() + " en";
		return (List<T>)getSession()
				.createQuery(hql)
				.list();
	}

	/**
	* ���ݷ�ҳ��ȡ����
	* @param hql���
	* @param pageNo ��ǰҳ
	* @param pageSize һҳ��ʾ����
	* @return ����ʵ��list����
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
	* ��ȡʵ������
	* @param entityClazz
	* @return ����long
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
	* ����������ѯ��¼��
	* @param hql���
	* @return ����long
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
