package cn.springboot.jerry.demo.cache.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoujx
 * @date 2017-11-25 16:33
 */
public class RedisService {

    protected final StringRedisTemplate stringRedisTemplate;
    protected final RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "stringRedisTemplate")
    protected ValueOperations<String, String> valOpsStr;
    @Resource(name = "redisTemplate")
    protected ValueOperations<Object, Object> valOpsObj;

    @Autowired
    public RedisService(StringRedisTemplate stringRedisTemplate, RedisTemplate<Object, Object> redisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisTemplate = redisTemplate;
    }

    public List<String> range(String name, int begin, int end) {
        return stringRedisTemplate.opsForList().range(name, begin, end);
    }

    public Long rpush(String name, String value) {
        return stringRedisTemplate.opsForList().rightPush(name, value);
    }

    public int pageBegin(int pageNum, int pageSize) {
        pageNum = Math.max(1, pageNum);
        return (pageNum - 1) * pageSize;
    }

    public int pageEnd(int pageNum, int pageSize) {
        pageNum = Math.max(1, pageNum);
        return pageNum * pageSize - 1;
    }

    /**
     * 根据指定key获取String
     *
     * @param key
     * @return
     */
    public String getStr(String key) {
        return valOpsStr.get(key);
    }

    /**
     * 设置Str缓存
     *
     * @param key
     * @param val
     */
    public void setStr(String key, String val) {
        valOpsStr.set(key, val);
    }

    /**
     * 删除指定key
     *
     * @param key
     */
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public boolean contains(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 根据指定o获取Object
     *
     * @param key
     * @return
     */
    public Object getObj(Object key) {
        return valOpsObj.get(key);
    }

    /**
     * 设置obj缓存
     *
     * @param key
     * @param value
     */
    public void setObj(Object key, Object value) {
        valOpsObj.set(key, value);
    }

    /**
     * 删除Obj缓存
     *
     * @param o
     */
    public void delObj(Object o) {
        redisTemplate.delete(o);
    }

}
