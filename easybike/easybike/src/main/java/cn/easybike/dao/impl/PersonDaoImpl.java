package cn.easybike.dao.impl;

import org.springframework.stereotype.Repository;

import cn.easybike.dao.PersonDao;
import cn.easybike.entity.Person;
/**
* ������Ŀҵ�����ʵ����.ʵ��������Ŀ,ɾ����Ŀ�ȷ�����<br>
* @author  ���
* @since   JDK1.8
* @history 2016��11��18������5:48:16 ��� �½�
*/
@Repository("personDao")
public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao {

	@Override
	public Person getByPersonSn(String personSn) {
		String hql="select p from Person p Where p.personSn=:personSn";
		return (Person) getSession().createQuery(hql).setString("personSn", personSn).uniqueResult();
	}		
}
