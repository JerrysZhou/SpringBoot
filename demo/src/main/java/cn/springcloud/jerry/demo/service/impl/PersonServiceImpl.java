package cn.springcloud.jerry.demo.service.impl;

import cn.springcloud.jerry.demo.domain.Person;
import cn.springcloud.jerry.demo.mapper.PersonMapper;
import cn.springcloud.jerry.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoujx
 * @date 2017-11-20 23:17
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PersonMapper mapper;//这里会出现Mapper Type Not Found。是由于IDEA工具原因,将错误修改为警告即可正常启动并允许.

    @Override
    public void createPerson(Person p) {
        jdbcTemplate.update("INSERT INTO person VALUES (null, ?, ?, ?)", p.getName(), p.getAge(), p.getSex());
    }

    @Override
    public Person findByName(String name) {
        return mapper.findByName(name);
    }

    @Override
    public List<Person> find(Person p) {
        return mapper.find(p);
    }
}
