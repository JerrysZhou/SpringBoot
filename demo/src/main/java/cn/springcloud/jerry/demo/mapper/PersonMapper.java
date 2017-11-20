package cn.springcloud.jerry.demo.mapper;

import cn.springcloud.jerry.demo.domain.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhoujx
 * @date 2017-11-20 14:34
 */
@Mapper
public interface PersonMapper {

    @Select("select * from person where name = #{name}")
    Person findByName(String name) ;

}
