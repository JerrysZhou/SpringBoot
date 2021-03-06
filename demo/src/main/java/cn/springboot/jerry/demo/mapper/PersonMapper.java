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
    List<Person> selectByName(String name);

    @SelectProvider(type = PersonProvider.class, method = "selectBy")
    @Results(value = {
            @Result(id = true, property = "id", column = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
            @Result(property = "name", column = "name", javaType = String.class, jdbcType = JdbcType.NVARCHAR),
            @Result(property = "age", column = "age", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "sex", column = "sex", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
    })
    List<Person> selectBy(Person p);

    Person selectById(@Param("id") long id);

    List<Person> selectAll();

    @Delete("delete from person where id = #{id}")
    void delete(long id);

    @UpdateProvider(type = PersonProvider.class, method = "update")
    void update(Person p);

    @Insert("insert into person(name, age, sex) values (#{name}, #{age}, #{sex})")
    @Options(useGeneratedKeys = true)
    void insert(Person p);

}
