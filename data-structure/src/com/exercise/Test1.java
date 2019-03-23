package com.exercise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * Program Name: data-structure 
 * Created by yanlp on 2019/2/12
 * @author yanlp
 * @version 1.0
 */
public class Test1 {

    @Test
    public void test1() {
        Map<Object, Object> map = new HashMap<>();
        map.put(null, 1);
        map.put(null, 2);
        System.out.println(map);
    }

    @Test
    public void test2() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");

        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");

        System.out.println(list1.contains(list2));
    }

    @Test
    public void test3() {
        Integer a = 1;
        BigDecimal bigDecimal = BigDecimal.valueOf(a);
        System.out.println(bigDecimal.divide(new BigDecimal("100")));
    }

}
