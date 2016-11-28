package cn.easybike.dao.impl;


import org.springframework.stereotype.Repository;

import cn.easybike.dao.RoleDao;
import cn.easybike.entity.Role;
/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:41:26 马辉 新建
*/
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public Role getBySn(String roleSn) {
		String hql="select r from Role r where r.roleSn=:roleSn";
		return (Role) getSession().createQuery(hql).setString("roleSn", roleSn).uniqueResult();
	}

	@Override
	public void deleteBySn(String roleSn) {
		String hql="delete Role r where r.roleSn=:roleSn";
		getSession().createQuery(hql).setString("roleSn", roleSn).executeUpdate();		
	}

	
}
