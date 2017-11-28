package cn.springboot.jerry.demo.cache;

import cn.springboot.jerry.demo.domain.Person;

import java.util.List;

/**
 * @author zhoujx
 * @date 2017-11-25 10:12
 */
public interface PersonCacheService {

    List<Person> getBy(String name, int pageNum, int pageSize);

    Long put(String name, List<Person> p);

}
