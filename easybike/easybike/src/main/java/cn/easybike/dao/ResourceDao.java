package cn.easybike.dao;



import cn.easybike.entity.Resource;
import net.sf.json.JSONArray;

/**
* 技改项目业务代表实现类.实现新增项目,删除项目等方法，<br>
* 提供对表现层的接口.
* @author  马辉
* @since   JDK1.8
* @history 2016年11月18日下午9:48:35 马辉 新建
*/
public interface ResourceDao extends BaseDao<Resource> {
	//��ȡ�����˵�
	public JSONArray getMenu(String personSn, String parentResourceSn);
}
