package cn.springcloud.jerry.demo.controller;

import cn.springcloud.jerry.demo.domain.Person;
import cn.springcloud.jerry.demo.exception.MyBusinessException;
import cn.springcloud.jerry.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhoujx
 * @date 2017-11-19 23:40
 */
@RestController
@ComponentScan("cn.springcloud.jerry.demo.mapper")
public class PersonController {

    @Value(value = "${jerry.msg}")
    private String msg;

    private final PersonService service;

    @Autowired
    public PersonController(@Qualifier("personServiceImpl") PersonService service) {
        this.service = service;
    }

    @RequestMapping(value = "/add/{name}/{age}/{sex}", method = RequestMethod.GET)
    public String add(@PathVariable("name") String name, @PathVariable("age") int age, @PathVariable("sex") int sex) {
        final Person p = new Person(name, sex, age);
        service.createPerson(p);
//        int n = 1 / 0;
        return "Hello," + "," + p.toString();
    }

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.GET)
    public String delete(@PathVariable("name") String name) {
        throw new MyBusinessException(8700, "MyBusinessException:" + name);
//        return "Hello," + msg;
    }

    @RequestMapping(value = "/get/{name}")
    public Person getByName(@PathVariable("name") String name) {
        return service.findByName(name);
    }

}
