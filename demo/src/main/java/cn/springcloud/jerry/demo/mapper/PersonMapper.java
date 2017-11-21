package cn.springcloud.jerry.demo.mapper;

import cn.springcloud.jerry.demo.domain.Person;
import cn.springcloud.jerry.demo.provider.PersonProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author zhoujx
 * @date 2017-11-20 14:34
 */
@Mapper
public interface PersonMapper {

    @Select("select * from person where name = #{name}")
    Person findByName(String name);

    @SelectProvider(type = PersonProvider.class, method = "find")
    List<Person> find(Person p);
}
