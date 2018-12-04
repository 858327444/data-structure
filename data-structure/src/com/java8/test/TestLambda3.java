package com.java8.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestLambda3 {

    /**
     * Predicate<T> 断言型接口
     */
    @Test
    public void test4() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Boolean result = compareList(list, (pre) -> pre.size() > 8);
        System.out.println(result);
    }

    public Boolean compareList(List<Integer> list, Predicate<List> pre) {
        return pre.test(list);
    }

    /**
     * Function<T,R> 函数型接口
     */
    @Test
    public void test3() {
        String result = strHandler("abcde", (str) -> str.toUpperCase());
        System.out.println(result);
    }

    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    /**
     * Supplier<T> 供给型接口
     */
    @Test
    public void test2() {
        List<Integer> list = getNumber(20, () -> (int) (Math.random() * 101));
        list.forEach(System.out::println);
    }

    private List<Integer> getNumber(int n, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    /**
     * Consumer<T> : 消费型接口消费型接口
     */
    @Test
    public void test1() {
        happy(1000, con -> System.out.println("此次行程花费了" + con + "元"));
    }

    public void happy(double money, Consumer con) {
        con.accept(money);

    }
}
