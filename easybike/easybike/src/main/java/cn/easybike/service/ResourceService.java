package cn.easybike.service;

import cn.easybike.entity.Resource;
import net.sf.json.JSONArray;

/**
* 权限<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月17日下午8:59:04 马辉 新建
*/
public interface ResourceService extends BaseService<Resource> {
	//获取菜单
	public JSONArray getMenu(String personSn, String parentResourceSn);
}
