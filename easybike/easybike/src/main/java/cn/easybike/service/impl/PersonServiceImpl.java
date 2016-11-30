package cn.easybike.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import cn.easybike.entity.Person;
import cn.easybike.service.PersonService;
@Service("personService")
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {

	//getBySn
	@Override
	public Person getByPersonSn(String personSn) {
		return personDao.getByPersonSn(personSn);
	}

	//根据编号删除人员
	@Override
	public void deleteBySn(String personSn) {
		personDao.deleteBySn(personSn);
	}

	@Override
	public HashMap<String, String> getRoles(String personSn) {
		return personDao.getRoles(personSn);
	}

	@Override
	public HashMap<String, String> getResources(String personSn) {
		return personDao.getResources(personSn);
	}

}
