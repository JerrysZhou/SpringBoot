package cn.springcloud.jerry.demo.service;

import cn.springcloud.jerry.demo.domain.Person;
import org.springframework.stereotype.Service;

/**
 * @author zhoujx
 * @date 2017-11-20 14:08
 */
@Service
public interface PersonService  {

    void createPerson(Person p);

    Person findByName(String name);
}
