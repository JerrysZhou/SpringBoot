package cn.springcloud.jerry.demo.provider;

import cn.springcloud.jerry.demo.domain.Person;
import cn.springcloud.jerry.demo.utils.StrEx;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author zhoujx
 * @date 2017-11-21 13:12
 */
public class PersonProvider {

    public String find(final Person p) {
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

}
