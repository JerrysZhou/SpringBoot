package cn.springboot.jerry.demo.controller;

import cn.springboot.jerry.demo.domain.Person;
import cn.springboot.jerry.demo.exception.MyBusinessException;
import cn.springboot.jerry.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhoujx
 * @date 2017-11-19 23:40
 */
@RestController
@ComponentScan("cn.springboot.jerry.demo.mapper")
public class PersonController {

    @Value(value = "${jerry.msg}")
    private String msg;

    private final PersonService service;

    @Autowired
    public PersonController(@Qualifier("personServiceImpl") PersonService service) {
        this.service = service;
    }

    @RequestMapping(value = "/person/add/{name}/{age}/{sex}", method = RequestMethod.GET)
    public String add(@PathVariable("name") String name, @PathVariable("age") int age, @PathVariable("sex") int sex) {
        final Person p = new Person(name, sex, age);
        service.insertPerson(p);
//        int n = 1 / 0;
        return "Hello," + "," + p.toString();
    }

    @RequestMapping(value = "/person/delete/{name}", method = RequestMethod.GET)
    public String delete(@PathVariable("name") String name) {
        throw new MyBusinessException(8700, "MyBusinessException:" + name);
//        return "Hello," + msg;
    }

    @RequestMapping(value = "/person/find/{name}")
    public Person getByName(@PathVariable("name") String name) {
        return service.selectByName(name);
    }

    @RequestMapping(value = "/person/find", method = RequestMethod.GET)
    public List<Person> find(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "age", required = false) Integer age,
                             @RequestParam(value = "sex", required = false) Integer sex) {
//        List<Person> rst = new ArrayList<>();
//        rst.add(service.selectByName(name));
//        return rst;
        final Person p = new Person(name, sex, age);
        return service.find(p);
    }

    @RequestMapping(value = "/person/list")
    public List<Person> getAll() {
        return service.selectAll();
    }

    @RequestMapping(value = "/person/get/{id}")
    public Person getById(@PathVariable("id") long id) {
        return service.selectById(id);
    }

}
