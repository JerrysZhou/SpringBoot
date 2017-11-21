package cn.springboot.jerry.demo.service.impl;

import cn.springboot.jerry.demo.domain.Person;
import cn.springboot.jerry.demo.mapper.PersonMapper;
import cn.springboot.jerry.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhoujx
 * @date 2017-11-20 23:17
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final JdbcTemplate jdbcTemplate;

    private final PersonMapper mapper;

    //这里会出现Mapper Type Not Found。是由于IDEA工具原因,将错误修改为警告即可正常启动并允许.
    @Autowired
    public PersonServiceImpl(JdbcTemplate jdbcTemplate, PersonMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void insert(Person p) {
//        jdbcTemplate.update("INSERT INTO person VALUES (null, ?, ?, ?)", p.getName(), p.getAge(), p.getSex());
        mapper.insert(p);
    }

    @Override
    public Person selectByName(String name) {
        return mapper.findByName(name);
    }

    @Override
    public List<Person> find(Person p) {
        return mapper.find(p);
    }

    @Override
    public Person selectById(long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Person> selectAll() {
        return mapper.selectAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        mapper.delete(id);
    }

    @Override
    @Transactional
    public void update(Person p) {
        mapper.update(p);
    }
}
