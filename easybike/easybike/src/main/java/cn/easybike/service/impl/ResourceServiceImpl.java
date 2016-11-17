package cn.easybike.service.impl;

import org.springframework.stereotype.Service;

import cn.easybike.entity.Resource;
import cn.easybike.service.ResourceService;
import net.sf.json.JSONArray;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月17日下午9:01:37 马辉 新建
*/
@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService{

	//获取菜单
	@Override
	public JSONArray getMenu(String personSn, String parentResourceSn) {
		return resourceDao.getMenu(personSn, parentResourceSn);
	}

}
