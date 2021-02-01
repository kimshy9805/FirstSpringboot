package com.kay.demo.dao;

import com.kay.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//link to qualifier
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(person.getName(), id));
        return 1;
    }

    @Override
    public List<Person> showAllPerson() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        int success = -1;
        for (int i=0;i<DB.size();i++) {
            if (DB.get(i).getId().equals(id)) {
                DB.remove(i);
                success = 1;
                break;
            }
        }
        return success;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        int success = -1;
        for (int i=0;i<DB.size();i++) {
            if (DB.get(i).getId().equals(id)) {
                DB.remove(i);
                DB.add(new Person(update.getName(), id));
                success = 1;
                break;
            }
        }
        return success;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }
}
