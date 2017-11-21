package cn.springboot.jerry.demo.controller;

import cn.springboot.jerry.demo.domain.Person;
import cn.springboot.jerry.demo.service.PersonService;
import cn.springboot.jerry.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public PersonController(@Qualifier("personServiceImpl") PersonService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getById(@PathVariable("id") long id) {
        final Person p = service.selectById(id);
        return Result.successGet(p);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result list(@RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "age", required = false) Integer age,
                       @RequestParam(value = "sex", required = false) Integer sex) {
        final Person p = new Person(name, sex, age);
        final List<Person> data = service.find(p);
        return Result.successGet(data);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result add(@RequestBody Person p) {
        service.insert(p);
        return Result.successPost(p);
//      int n = 1 / 0;
//        return "Hello," + "," + p.toString();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Result update(@RequestBody Person p) {
        if (p.getId() == null) {
            return Result.invalidParam("id = null");
        }
        final Long id = p.getId();
        final Person oldPerson = service.selectById(id);
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
        final Person p = service.selectById(id);
        if (p == null) {
            return Result.invalidParam(String.format("person id = %s is not exists!", id));
        }
        service.delete(id);
        return Result.successDelete();
//        throw new MyBusinessException(8700, "MyBusinessException:" + name);
//        return "Hello," + msg;
    }

}
