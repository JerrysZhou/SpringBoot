package cn.springboot.jerry.demo.mapper;

import cn.springboot.jerry.demo.domain.Person;
import cn.springboot.jerry.demo.provider.PersonProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

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
    @Results(value = {
            @Result(id = true, property = "id", column = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.NVARCHAR),
            @Result(property = "age", column = "age", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "sex", column = "sex", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
    })
    List<Person> find(Person p);

    Person selectById(@Param("id") long id);

    List<Person> selectAll();
}
