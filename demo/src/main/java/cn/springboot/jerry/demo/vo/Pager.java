package cn.springboot.jerry.demo.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhoujx
 * @date 2017-11-28 13:56
 */
@ConfigurationProperties(prefix = "pager")
public class Pager {

    public int pageSize;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
