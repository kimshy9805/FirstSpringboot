package com.kay.demo.service;

import com.kay.demo.dao.PersonDao;
import com.kay.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    //dont get the actual fakePersonData
    //getting the interface personDAO
    //so that can use dependency injection to switch database (fake -> postgres)
    private final PersonDao personDao;

    //why qualifier?
    //interface에서 fakedao object를 여기서 해라 의미로 쓰임
    //그래서 실제 fakePerson class에도 똑같이해줘야함
    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> showAllPerson() {
        return personDao.showAllPerson();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int deletePersonById(UUID id) {
        return personDao.deletePersonById(id);
    }

    public int updatePersonById(UUID id, Person update) {
        return personDao.updatePersonById(id, update);
    }

}
