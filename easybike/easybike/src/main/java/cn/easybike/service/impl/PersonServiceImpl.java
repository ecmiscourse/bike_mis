package cn.easybike.service.impl;

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

}
