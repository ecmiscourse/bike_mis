package cn.easybike.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import cn.easybike.dao.BaseDao;
import cn.easybike.dao.BikeDao;
import cn.easybike.dao.LendAndReturnRecordDao;
import cn.easybike.dao.MaintenanceDao;
import cn.easybike.dao.PersonDao;
import cn.easybike.dao.ResourceDao;
import cn.easybike.dao.RoleDao;
import cn.easybike.dao.StationDao;
import cn.easybike.service.BaseService;


/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午3:07:12 马辉 新建
*/
@SuppressWarnings("unchecked")
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {
	private Class clazz; //
	public BaseServiceImpl() {
		
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
	}
	protected BaseDao baseDao; 
	
	@PostConstruct   
	public void init() throws Exception{
		
		String clazzName=clazz.getSimpleName();
		String clazzDaoName=clazzName.substring(0,1).toLowerCase() + clazzName.substring(1) + "Dao";
		Field clazzField=this.getClass().getSuperclass().getDeclaredField(clazzDaoName);
		
		Field baseField=this.getClass().getSuperclass().getDeclaredField("baseDao");
		
		baseField.set(this,clazzField.get(this));
	}
	//注入dao
	
	@Resource(name="resourceDao")
	protected ResourceDao resourceDao;
	@Resource(name="personDao")
	protected PersonDao personDao;
	@Resource(name="bikeDao")
	protected BikeDao bikeDao;
	@Resource(name="lendAndReturnRecordDao")
	protected LendAndReturnRecordDao lendAndReturnRecordDao;
	@Resource(name="maintenanceDao")
	protected MaintenanceDao maintenanceDao;
	@Resource(name="roleDao")
	protected RoleDao roleDao;
	@Resource(name="stationDao")
	protected StationDao stationDao;
	
	
	@Override
	public T get(int id) {
		return (T) baseDao.get(id);
	}

	
	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	
	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	
	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	
	@Override
	public void delete(int id) {
		baseDao.delete(id);
	}
	

	@Override
	public List<T> queryAll() {
		return baseDao.queryAll();
	}

	
	@Override
	public List<T> queryByPage(String hql, int pageNo, int pageSize) {
		return baseDao.queryByPage(hql, pageNo, pageSize);
	}

	
	@Override
	public long countAll() {
		return baseDao.countAll();
	}

	
	@Override
	public long countByHql(String hql) {
		return baseDao.countByHql(hql);
	}
}
