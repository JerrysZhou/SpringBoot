package cn.springboot.jerry.demo.service;

import cn.springboot.jerry.demo.domain.Person;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoujx
 * @date 2017-11-20 14:08
 */
@Service
public interface PersonService  {

    void insert(Person p);

    Person selectByName(String name);

    List<Person> find(Person param);

    Person selectById(long id);

    List<Person> selectAll();

    void delete(long id);

    void update(Person p);
}
