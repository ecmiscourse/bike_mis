package cn.easybike.dao;



import cn.easybike.entity.Resource;
import net.sf.json.JSONArray;

/**
* Ȩ��<br>
* �ṩ�Ա��ֲ�Ľӿ�.
* @author  ���
* @since   JDK1.8
* @history 2016��11��17������8:55:30 ��� �½�
*/
public interface ResourceDao extends BaseDao<Resource> {
	//��ȡ�����˵�
	public JSONArray getMenu(String personSn, String parentResourceSn);
}
