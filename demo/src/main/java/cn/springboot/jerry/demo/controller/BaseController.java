package cn.springboot.jerry.demo.controller;

import cn.springboot.jerry.demo.vo.Pager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhoujx
 * @date 2017-11-28 13:59
 */
public class BaseController {

    @Autowired
    protected Pager pager;
}
