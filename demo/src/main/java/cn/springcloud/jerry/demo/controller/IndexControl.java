package cn.springcloud.jerry.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhoujx
 * @date 2017-11-20 22:57
 */
@Controller
public class IndexControl {

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("name", "Jerry");
        return "index";
    }
}
