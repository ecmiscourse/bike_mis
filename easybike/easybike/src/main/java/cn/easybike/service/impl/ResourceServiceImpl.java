package cn.easybike.service.impl;

import org.springframework.stereotype.Service;

import cn.easybike.entity.Resource;
import cn.easybike.service.ResourceService;
import net.sf.json.JSONArray;

/**
* ������Ŀҵ�����ʵ����.ʵ��������Ŀ,ɾ����Ŀ�ȷ�����<br>
* �ṩ�Ա��ֲ�Ľӿ�.
* @author  ���
* @since   JDK1.8
* @history 2016��11��17������9:01:37 ��� �½�
*/
@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService{

	//��ȡ�˵�
	@Override
	public JSONArray getMenu(String personSn, String parentResourceSn) {
		return resourceDao.getMenu(personSn, parentResourceSn);
	}

}
