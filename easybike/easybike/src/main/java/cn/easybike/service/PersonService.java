package cn.easybike.service;

import cn.easybike.entity.Person;

/**
* ������Ŀҵ�����ʵ����.ʵ��������Ŀ,ɾ����Ŀ�ȷ�����<br>
* �ṩ�Ա��ֲ�Ľӿ�.
* @author  ���
* @since   JDK1.8
* @history 2016��11��18������5:48:13 ��� �½�
*/
public interface PersonService extends BaseService<Person> {
	
	//getBySn
	public Person getByPersonSn(String personSn);

}
