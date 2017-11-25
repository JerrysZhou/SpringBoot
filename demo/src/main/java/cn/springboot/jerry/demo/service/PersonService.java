package cn.springboot.jerry.demo.service;

import cn.springboot.jerry.demo.domain.Person;

import java.util.List;

/**
 * @author zhoujx
 * @date 2017-11-20 14:08
 */
public interface PersonService  {

    void insert(Person p);

    void inserts(List<Person> persons);

    List<Person> getBy(String name);

    List<Person> getBy(Person param);

    Person getById(long id);

    List<Person> getAll();

    void delete(long id);

    void update(Person p);

}
