package com.java8.test;

import com.java8.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 筛选与切片
 * filter——接收 Lambda ， 从流中排除某些元素。
 * limit——截断流，使其元素不超过给定数量。
 * skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
 * distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
 * <p>
 * 映射
 * map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
 * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
 * <p>
 * 排序
 * sorted()——自然排序(Comparable)
 * sorted(Comparator com)——定制排序(Comparator)
 */
public class TestStreamAPI2 {
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 58, 5555.55),
            new Employee("王五", 26, 3333.33),
            new Employee("赵六", 36, 6666.66),
            new Employee("田七", 12, 8888.88),
            new Employee("田七", 12, 8888.88),
            new Employee("田七", 12, 8888.88)
    );

    @Test
    public void test6() {
        employees.stream()
                .sorted((e1, e2) -> {
                    if (e2.getAge().equals(e2.getAge())) {
                        return Double.compare(e1.getSalary(), e2.getSalary());
                    } else {
                        return Integer.compare(e1.getAge(), e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }

    /**
     * 映射
     * map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流\
     */
    @Test
    public void test5() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("------------------------");

//        Stream<List<Character>> listStream = list.stream()
//                .map(TestStreamAPI2::filterCharacter);
//        listStream.forEach(item -> item.forEach(System.out::println));
        Stream<Character> rStream = list.stream().flatMap(TestStreamAPI2::filterCharacter);
        rStream.forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    // 外部迭代
    @Test
    public void test3() {
        employees.stream()
                .skip(2)
                .distinct()
                .forEach(item -> System.out.println(item));
    }

    // 外部迭代
    @Test
    public void test2() {
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    // 内部迭代 迭代操作由StreamAPI 完成
    @Test
    public void test1() {
        employees.stream()
                .filter((e) -> {
                    System.out.println("进来了");
                    return e.getAge() > 30;
                })
                .forEach(employee -> System.out.println(employee));
    }

}
