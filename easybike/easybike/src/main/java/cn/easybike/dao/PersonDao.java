package cn.easybike.dao;

import java.util.HashMap;

import cn.easybike.entity.Person;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月18日下午9:48:25 马辉 新建
*/
public interface PersonDao extends BaseDao<Person> {
	//getBySn
	public Person getByPersonSn(String personSn);
	//根据编号删除人员
	public void deleteBySn(String personSn);
	//获取员工角色
	public HashMap<String,String> getRoles(String personSn);
	//获取人员权限
	public HashMap<String,String> getResources(String personSn);
}
