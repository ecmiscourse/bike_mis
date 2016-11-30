package cn.easybike.service;

import cn.easybike.entity.Resource;
import net.sf.json.JSONArray;


/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月18日下午9:56:30 马辉 新建
*/
public interface ResourceService extends BaseService<Resource> {
	//获取菜单
	public JSONArray getMenu(String personSn, String parentResourceSn);
	//获取权限
	public JSONArray getResource(String personSn,String parentResourceSn,String roleSn);
	//getBySn
	public Resource getBySn(String resourceSn);
}
