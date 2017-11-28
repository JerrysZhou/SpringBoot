package cn.springboot.jerry.demo.cache.impl;

import cn.springboot.jerry.demo.cache.PersonCacheService;
import cn.springboot.jerry.demo.cache.base.RedisService;
import cn.springboot.jerry.demo.domain.Person;
import cn.springboot.jerry.demo.mapper.PersonMapper;
import cn.springboot.jerry.demo.utils.JsonEx;
import cn.springboot.jerry.demo.utils.ListEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Person缓存服务
 *
 * @author zhoujx
 * @date 2017-11-25 10:13
 */
@Service
public class PersonCacheServiceImpl extends RedisService implements PersonCacheService {

    private final PersonMapper mapper;

    @Autowired
    public PersonCacheServiceImpl(StringRedisTemplate stringRedisTemplate, RedisTemplate<Object, Object> redisTemplate,
                                  PersonMapper mapper) {
        super(stringRedisTemplate, redisTemplate);
        this.mapper = mapper;
    }

    @Override
    public List<Person> getBy(String name, int pageNum, int pageSize) {
        final int end = pageEnd(pageNum, pageSize);
        final int begin = pageBegin(pageNum, pageSize);

        final List<String> cache = range(name, begin, end);
        if (ListEx.isEmpty(cache)) {
            return ListEx.emptyList();
        }
        final List<Person> rst = new ArrayList<>();
        for (String value : cache) {
            final List<Person> p = JsonEx.toList(value, Person.class);
            if (ListEx.isEmpty(p))
                continue;
            rst.addAll(p);
        }
        return rst;
    }

    @Override
    public Long put(String name, List<Person> p) {
        final String value = JsonEx.toJson(p);
        return rpush(name, value);
    }
}
