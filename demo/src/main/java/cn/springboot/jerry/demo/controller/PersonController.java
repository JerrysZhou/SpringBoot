package cn.springboot.jerry.demo.controller;

import cn.springboot.jerry.demo.domain.Person;
import cn.springboot.jerry.demo.service.PersonService;
import cn.springboot.jerry.demo.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujx
 * @date 2017-11-19 23:40
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService service;
    @Value(value = "${jerry.msg}")
    private String msg;

    private static final Logger log = Logger.getLogger(PersonController.class);

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getById(@PathVariable("id") long id) {
        final Person p = service.getById(id);
        return Result.successGet(p);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(@RequestParam(value = "name", required = false) String name) {
        log.info("List Req Param: name=" + name);
        final List<Person> data = service.getBy(name);
        return Result.successGet(data);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result add(@RequestBody Person p) {
        service.insert(p);
        return Result.successPost(p);
//      int n = 1 / 0;
//        return "Hello," + "," + p.toString();
    }

    @RequestMapping(value = "/add/{count}", method = RequestMethod.GET)
    public Result add(@PathVariable("count") int count) {
        final List<Person> persons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int sex = i % 2;
            final Person p = new Person(String.format("张三李四王二%s", i), sex, i);
            persons.add(p);
        }
        service.inserts(persons);
        return Result.successPost(String.format("success batchAdd! Count : %s !", count));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result update(@RequestBody Person p) {
        if (p.getId() == null) {
            return Result.invalidParam("id = null");
        }
        final Long id = p.getId();
        final Person oldPerson = service.getById(id);
        if (oldPerson == null) {
            return Result.invalidParam(String.format("person id = %s is not exists!", id));
        }
        service.update(p);
        return Result.successPut();
//      int n = 1 / 0;
//      return "Hello," + "," + p.toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") long id) {
        final Person p = service.getById(id);
        if (p == null) {
            return Result.invalidParam(String.format("person id = %s is not exists!", id));
        }
        service.delete(id);
        return Result.successDelete();
//        throw new MyBusinessException(8700, "MyBusinessException:" + name);
//        return "Hello," + msg;
    }

}
