package cn.easybike.dao;



import cn.easybike.entity.Resource;
import net.sf.json.JSONArray;

/**
* 权限<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月17日下午8:55:30 马辉 新建
*/
public interface ResourceDao extends BaseDao<Resource> {
	//获取导航菜单
	public JSONArray getMenu(String personSn, String parentResourceSn);
}
