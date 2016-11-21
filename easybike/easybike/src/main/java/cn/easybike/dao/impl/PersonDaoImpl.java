package cn.easybike.dao.impl;

import org.springframework.stereotype.Repository;

import cn.easybike.dao.PersonDao;
import cn.easybike.entity.Person;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月18日下午9:51:47 马辉 新建
*/
@Repository("personDao")
public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao {

	@Override
	public Person getByPersonSn(String personSn) {
		String hql="select p from Person p Where p.personSn=:personSn";
		return (Person) getSession().createQuery(hql).setString("personSn", personSn).uniqueResult();
	}

	//根据编号删除人员
	@Override
	public void deleteBySn(String personSn) {
		String hql="delete Person p where p.personSn=:personSn";
		getSession().createQuery(hql).setString("personSn", personSn).executeUpdate();
	}		
}
