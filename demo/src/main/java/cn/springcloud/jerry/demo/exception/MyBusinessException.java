package cn.springcloud.jerry.demo.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoujx
 * @date 2017-11-20 22:32
 */
public class MyBusinessException extends RuntimeException {

    private int code;
    private String msg;

    public MyBusinessException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> toMap() {
        final Map<String, Object> rst = new HashMap<>();
        rst.put("code", getCode());
        rst.put("msg", getMsg());
        return rst;
    }
}
