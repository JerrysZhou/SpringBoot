package cn.springboot.jerry.demo.provider;

import cn.springboot.jerry.demo.domain.Person;
import cn.springboot.jerry.demo.utils.StrEx;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author zhoujx
 * @date 2017-11-21 13:12
 */
public class PersonProvider {

    public String selectBy(final Person p) {
        return new SQL() {
            {
                SELECT("id, name, age, sex");
                FROM("person");
                WHERE("1 = 1");
                if (StrEx.notEmpty(p.getName())) {
                    WHERE("name like CONCAT('%',#{name},'%')");
//                    WHERE("name = #{name} ");
                }
                if (p.getAge() != null) {
                    WHERE("age = #{age}");
                }
                if (p.getSex() != null) {
                    WHERE("sex = #{sex}");
                }
            }
        }.toString();
    }

    public String update(final Person p) {
        return new SQL() {
            {
                UPDATE("person");
                if (StrEx.notEmpty(p.getName())) {
                    SET("name = #{name}");
                }
                if (p.getAge() != null) {
                    SET("age = #{age}");
                }
                if (p.getSex() != null) {
                    SET("sex = #{sex}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }

}
