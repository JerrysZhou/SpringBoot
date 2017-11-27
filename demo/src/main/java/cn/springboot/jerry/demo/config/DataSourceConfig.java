package cn.springboot.jerry.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @author zhoujx
 * @date 2017-11-25 18:05
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(Environment environment) {
        return DruidDataSourceBuilder
                .create()
                .build(environment, "spring.datasource.druid.");
    }

}
