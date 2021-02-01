package com.kay.demo.api;

import com.kay.demo.model.Person;
import com.kay.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


//여기서 뭔가가 일어나진 않음
//personService에서 di 를 이용해 instantiation을 하고 해당하는 method를 call하는 형식임.

//http get, post, put~~
@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    //dependency injection
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //assign this method to be post
    //@RequestBody means -> take the info from client and instantiate Person object.
    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> showAllPerson() {
        return personService.showAllPerson();
    }

    //path directory
    //person/UUID id
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deletePersonById(@PathVariable("id") UUID id) {
        return personService.deletePersonById(id);
    }

    @PutMapping(path = "{id}")
    public int updatePersonById(@PathVariable("id") UUID id, @RequestBody Person person) { return  personService.updatePersonById(id, person); }

}
