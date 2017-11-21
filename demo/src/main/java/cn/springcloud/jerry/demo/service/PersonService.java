package cn.springcloud.jerry.demo.service;

import cn.springcloud.jerry.demo.domain.Person;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoujx
 * @date 2017-11-20 14:08
 */
@Service
public interface PersonService  {

    void createPerson(Person p);

    Person findByName(String name);

    List<Person> find(Person param);

}
