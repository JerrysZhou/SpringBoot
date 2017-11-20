package cn.springcloud.jerry.demo.advice;

import cn.springcloud.jerry.demo.exception.MyBusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoujx
 * @date 2017-11-20 22:29
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String, Object> exceptionHandler(Exception e) {
        final Map<String, Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("msg", e.getMessage());
        return map;
    }

    @ResponseBody
    @ExceptionHandler(MyBusinessException.class)
    public Map<String, Object> myBusinessExceptionHandler(MyBusinessException e) {
        return e.toMap();
    }

}
