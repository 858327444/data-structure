package com.java8.test;

import com.java8.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TestLambda2 {

    List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 20, 5555.55),
            new Employee("李四", 37, 7777.77),
            new Employee("王五", 39, 4444.44),
            new Employee("赵六", 20, 8888.88),
            new Employee("田七", 56, 9999.99)
    );

    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("进行比较");
            return Integer.compare(x, y);
        };
        int compare = com.compare(1, 1);
        System.out.println(compare);
    }

    /**
     * 有一个参数  没有返回值
     */
    @Test
    public void test2() {
        Consumer<String> consumer = (str) -> System.out.println(str);
        consumer.accept("abcde");
    }

    /**
     * 没有参数 没有返回值
     */
    @Test
    public void test1() {
        Runnable r1 = () -> System.out.println("Hello labmda");

        r1.run();
    }


}
