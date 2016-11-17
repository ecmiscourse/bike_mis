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
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月17日下午3:43:09 马辉 新建
*/
@SuppressWarnings("unchecked")
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {
	private Class clazz; // clazz中存储了子类当前操作实体类型
	public BaseServiceImpl() {
		// 如果子类调用当前构造方法,this代表的是子类对象
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
	}
	protected BaseDao baseDao; 
	
	@PostConstruct   // init方法是在构造方法与set注入之后执行, 也就是XML的: init-method=""
	public void init() throws Exception{
		// 1: 根据具体的泛型, 获取相应的Field字段
		String clazzName=clazz.getSimpleName();
		String clazzDaoName=clazzName.substring(0,1).toLowerCase() + clazzName.substring(1) + "Dao";
		Field clazzField=this.getClass().getSuperclass().getDeclaredField(clazzDaoName);
		// 2: 获取baseDao Filed字段
		Field baseField=this.getClass().getSuperclass().getDeclaredField("baseDao");
		// 3: 把categoryDao的值赋值给baseDao
		baseField.set(this,clazzField.get(this));
	}
	//注入dao
	
	@Resource(name="resourceDao")
	protected ResourceDao resourceDao;

	/**
	* getByID
	* @param id
	* @return 返回实体
	*/
	@Override
	public T get(int id) {
		return (T) baseDao.get(id);
	}

	/**
	* 保存实体信息
	* @param entity
	* @return 无返回值
	*/
	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	/**
	* 修改实体
	* @param entity
	* @return 无返回值
	*/
	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	/**
	* 删除实体
	* @param entity
	* @return 无返回值
	*/
	@Override
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	/**
	* 根据ID删除实体
	* @param id
	* @return 无返回值
	*/
	@Override
	public void delete(int id) {
		baseDao.delete(id);
	}
	/**
	* 获取所有实体
	* @param entityClazz实体
	* @return 返回实体list集合
	*/

	@Override
	public List<T> queryAll() {
		return baseDao.queryAll();
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
		return baseDao.queryByPage(hql, pageNo, pageSize);
	}

	/**
	* 获取实体总数
	* @param entityClazz
	* @return 返回long
	*/
	@Override
	public long countAll() {
		return baseDao.countAll();
	}

	/**
	* 根据条件查询记录数
	* @param hql语句
	* @return 返回long
	*/
	@Override
	public long countByHql(String hql) {
		return baseDao.countByHql(hql);
	}
}
