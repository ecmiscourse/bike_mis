package cn.easybike.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import cn.easybike.dao.BaseDao;
import cn.easybike.dao.ResourceDao;
import cn.easybike.service.BaseService;

/**
* ������Ŀҵ�����ʵ����.ʵ��������Ŀ,ɾ����Ŀ�ȷ�����<br>
* �ṩ�Ա��ֲ�Ľӿ�.
* @author  ���
* @since   JDK1.8
* @history 2016��11��17������3:43:09 ��� �½�
*/
@SuppressWarnings("unchecked")
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {
	private Class clazz; // clazz�д洢�����൱ǰ����ʵ������
	public BaseServiceImpl() {
		// ���������õ�ǰ���췽��,this��������������
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
	}
	protected BaseDao baseDao; 
	
	@PostConstruct   // init�������ڹ��췽����setע��֮��ִ��, Ҳ����XML��: init-method=""
	public void init() throws Exception{
		// 1: ���ݾ���ķ���, ��ȡ��Ӧ��Field�ֶ�
		String clazzName=clazz.getSimpleName();
		String clazzDaoName=clazzName.substring(0,1).toLowerCase() + clazzName.substring(1) + "Dao";
		Field clazzField=this.getClass().getSuperclass().getDeclaredField(clazzDaoName);
		// 2: ��ȡbaseDao Filed�ֶ�
		Field baseField=this.getClass().getSuperclass().getDeclaredField("baseDao");
		// 3: ��categoryDao��ֵ��ֵ��baseDao
		baseField.set(this,clazzField.get(this));
	}
	//ע��dao
	
	@Resource(name="resourceDao")
	protected ResourceDao resourceDao;

	/**
	* getByID
	* @param id
	* @return ����ʵ��
	*/
	@Override
	public T get(int id) {
		return (T) baseDao.get(id);
	}

	/**
	* ����ʵ����Ϣ
	* @param entity
	* @return �޷���ֵ
	*/
	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	/**
	* �޸�ʵ��
	* @param entity
	* @return �޷���ֵ
	*/
	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	/**
	* ɾ��ʵ��
	* @param entity
	* @return �޷���ֵ
	*/
	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	/**
	* ����IDɾ��ʵ��
	* @param id
	* @return �޷���ֵ
	*/
	@Override
	public void delete(int id) {
		baseDao.delete(id);
	}
	/**
	* ��ȡ����ʵ��
	* @param entityClazzʵ��
	* @return ����ʵ��list����
	*/

	@Override
	public List<T> queryAll() {
		return baseDao.queryAll();
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
		return baseDao.queryByPage(hql, pageNo, pageSize);
	}

	/**
	* ��ȡʵ������
	* @param entityClazz
	* @return ����long
	*/
	@Override
	public long countAll() {
		return baseDao.countAll();
	}

	/**
	* ����������ѯ��¼��
	* @param hql���
	* @return ����long
	*/
	@Override
	public long countByHql(String hql) {
		return baseDao.countByHql(hql);
	}
}
