package cn.easybike.service;

import cn.easybike.entity.Resource;
import net.sf.json.JSONArray;

/**
* Ȩ��<br>
* �ṩ�Ա��ֲ�Ľӿ�.
* @author  ���
* @since   JDK1.8
* @history 2016��11��17������8:59:04 ��� �½�
*/
public interface ResourceService extends BaseService<Resource> {
	//��ȡ�˵�
	public JSONArray getMenu(String personSn, String parentResourceSn);
}
