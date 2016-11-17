package cn.easybike.dao;

import java.util.List;

public interface BaseDao<T>{
	/**
	* getByID
	* @param id
	* @return ����ʵ��
	*/
	T get(int id);
	/**
	* ����ʵ����Ϣ
	* @param entity
	* @return �޷���ֵ
	*/
	void save(T entity);
	
	/**
	* �޸�ʵ��
	* @param entity
	* @return �޷���ֵ
	*/
	void update(T entity);
	
	/**
	* ɾ��ʵ��
	* @param entity
	* @return �޷���ֵ
	*/
	void delete(T entity);
	
	/**
	* ����IDɾ��ʵ��
	* @param id
	* @return �޷���ֵ
	*/
	void delete(int id);
	/**
	* ��ȡ����ʵ��
	* @param entityClazzʵ��
	* @return ����ʵ��list����
	*/
	List<T> queryAll();
	/**
	* ���ݷ�ҳ��ȡ����
	* @param hql���
	* @param pageNo ��ǰҳ
	* @param pageSize һҳ��ʾ����
	* @return ����ʵ��list����
	*/
	List<T> queryByPage(String hql , int pageNo, int pageSize);
	/**
	* ��ȡʵ������
	* @param entityClazz
	* @return ����long
	*/
	long countAll();
	
	/**
	* ����������ѯ��¼��
	* @param hql���
	* @return ����long
	*/
	long countByHql(String hql);
	
}
