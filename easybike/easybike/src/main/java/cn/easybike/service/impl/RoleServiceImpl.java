package cn.easybike.service.impl;

import org.springframework.stereotype.Service;
import cn.easybike.entity.Role;
import cn.easybike.service.RoleService;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月21日下午5:43:48 马辉 新建
*/
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Override
	public Role getBySn(String roleSn) {
		// TODO Auto-generated method stub
		return roleDao.getBySn(roleSn);
	}

	@Override
	public void deleteBySn(String roleSn) {
		roleDao.deleteBySn(roleSn);
	}

	
}
