package cn.springboot.jerry.demo.service.impl;

import cn.springboot.jerry.demo.cache.PersonCacheService;
import cn.springboot.jerry.demo.domain.Person;
import cn.springboot.jerry.demo.mapper.PersonMapper;
import cn.springboot.jerry.demo.service.PersonService;
import cn.springboot.jerry.demo.utils.ListEx;
import cn.springboot.jerry.demo.utils.StrEx;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
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

    private final SqlSessionTemplate sqlSessionTemplate;

    private final PersonCacheService cacheService;

    //这里会出现Mapper Type Not Found。是由于IDEA工具原因,将错误修改为警告即可正常启动并允许.
    @Autowired
    public PersonServiceImpl(JdbcTemplate jdbcTemplate, PersonMapper mapper, SqlSessionTemplate sqlSessionTemplate,
                             PersonCacheService cacheService) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.cacheService = cacheService;
    }

    @Override
    @Transactional
    public void insert(Person p) {
//      jdbcTemplate.update("INSERT INTO person VALUES (null, ?, ?, ?)", p.getName(), p.getAge(), p.getSex());
        mapper.insert(p);
    }

    @Override
    @Transactional
    public void inserts(List<Person> persons) {
        final SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
        session.getConfiguration().setDefaultExecutorType(ExecutorType.BATCH);
        try {
            final PersonMapper mapper = session.getMapper(PersonMapper.class);
            for (int i = 0; i < persons.size(); i++) {
                final Person p = persons.get(i);
                mapper.insert(p);
                if (i % 1000 == 0) {
                    session.commit();
                    session.clearCache();
                }
            }
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.commit();
            session.clearCache();
        }
    }

    @Override
    public List<Person> getBy(String name) {
        if (StrEx.isEmpty(name)) {
            return mapper.selectAll();
        }
        final List<Person> cache = cacheService.getBy(name);
        if (ListEx.notEmpty(cache)) {
            return cache;
        }
        final List<Person> persons = mapper.selectByName(name);
        cacheService.put(name, persons);
        return persons;
    }

    @Override
    public List<Person> getBy(Person p) {
        return mapper.selectBy(p);
    }

    @Override
    public Person getById(long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Person> getAll() {
        return mapper.selectAll();
    }

    @Override
    @Transactional
    public void delete(long id) {
        final Person person = mapper.selectById(id);
        if (person == null)
            return;
        mapper.delete(id);
    }

    @Override
    @Transactional
    public void update(Person p) {
        mapper.update(p);
    }

}
