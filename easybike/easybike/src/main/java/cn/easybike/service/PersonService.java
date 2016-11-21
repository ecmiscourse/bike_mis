package cn.easybike.service;

import cn.easybike.entity.Person;


/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月18日下午9:56:24 马辉 新建
*/
public interface PersonService extends BaseService<Person> {
	
	//getBySn
	public Person getByPersonSn(String personSn);
	//根据编号删除人员
	public void deleteBySn(String personSn);

}
