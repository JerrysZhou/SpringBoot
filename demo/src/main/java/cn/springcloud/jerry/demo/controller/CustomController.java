package cn.springcloud.jerry.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoujx
 * @date 2017-11-19 23:40
 */
@RestController
@RequestMapping("/")
public class CustomController {

    @RequestMapping(value = "/add/{name}", method = RequestMethod.GET)
    public String add(@PathVariable("name") String name) {
        return "Hello, " + name;
    }

}
