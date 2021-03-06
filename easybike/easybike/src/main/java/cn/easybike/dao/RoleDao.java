package cn.easybike.dao;

import cn.easybike.entity.Role;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:40:20 马辉 新建
*/
public interface RoleDao extends BaseDao<Role> {
	public Role getBySn(String roleSn);
	
	public void deleteBySn(String roleSn);
}
