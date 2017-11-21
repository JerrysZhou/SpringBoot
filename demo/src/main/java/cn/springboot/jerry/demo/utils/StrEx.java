package cn.springboot.jerry.demo.utils;

/**
 * @author zhoujx
 * @date 2017-11-21 13:14
 */
public class StrEx {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

}
