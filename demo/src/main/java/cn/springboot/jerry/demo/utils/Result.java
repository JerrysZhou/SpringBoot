package cn.springboot.jerry.demo.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @author zhoujx
 * @date 2017-11-21 20:25
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    private int status;
    private String errorMsg;
    private Object data;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date serverTime;

    public Result(int status, String errorMsg, Object data, Date serverTime) {
        this.status = status;
        this.errorMsg = errorMsg;
        this.data = data;
        this.serverTime = serverTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    public static Result successGet(Object data) {
        return new Result(200, "", data, new Date());
    }

    public static Result successPost(Object data) {
        return new Result(200, "", data, new Date());
    }

    public static Result successPut() {
        return new Result(200, "", null, new Date());
    }

    public static Result successDelete() {
        return new Result(200, "", null, new Date());
    }

    public static Result invalidParam(String msg) {
        return new Result(400, msg, null, new Date());
    }

}
