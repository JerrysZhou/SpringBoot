package cn.springboot.jerry.demo.utils;

import java.util.Collections;
import java.util.List;

/**
 * @author zhoujx
 * @date 2017-11-25 16:43
 */
public class ListEx {

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean notEmpty(List list) {
        return !isEmpty(list);
    }

    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }
}
