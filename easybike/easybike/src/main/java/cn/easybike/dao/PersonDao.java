package cn.easybike.dao;

import cn.easybike.entity.Person;

/**
* ������Ŀҵ�����ʵ����.ʵ��������Ŀ,ɾ����Ŀ�ȷ�����<br>
* �ṩ�Ա��ֲ�Ľӿ�.
* @author  ���
* @since   JDK1.8
* @history 2016��11��18������5:48:18 ��� �½�
*/
public interface PersonDao extends BaseDao<Person> {
	//getBySn
	public Person getByPersonSn(String personSn);
}
