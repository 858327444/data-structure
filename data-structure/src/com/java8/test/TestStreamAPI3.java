package com.java8.test;

import com.java8.entity.Employee;
import com.java8.entity.Employee.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 终止操作
 * <p>
 * 查找与匹配
 * allMatch——检查是否匹配所有元素
 * anyMatch——检查是否至少匹配一个元素
 * noneMatch——检查是否没有匹配所有元素
 * findFirst——返回第一个元素
 * findAny——返回当前流中的任意元素
 * count——返回流中元素的总个数
 * max——返回流中最大值
 * min——返回流中最小值
 * <p>
 * <p>
 * <p>
 * 归约
 * reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
 */
public class TestStreamAPI3 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99, Status.FREE),
            new Employee("李四", 58, 5555.55, Status.BUSY),
            new Employee("王五", 26, 3333.33, Status.VOCATION),
            new Employee("赵六", 36, 6666.66, Status.FREE),
            new Employee("田七", 12, 8888.88, Status.FREE),
            new Employee("田七", 12, 8888.88, Status.BUSY)
    );

    @Test
    public void test8() {
        String collect = employees.stream()
                .map(Employee::getName)
                .distinct()
                .collect(Collectors.joining(","));
        System.out.println(collect);
    }

    @Test
    public void test7() {

        DoubleSummaryStatistics collect = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        double sum = collect.getSum();
        long count = collect.getCount();
        double average = collect.getAverage();
        double max = collect.getMax();
        double min = collect.getMin();

        System.out.println(sum);
        System.out.println(count);
        System.out.println(average);
        System.out.println(max);
        System.out.println(min);


    }

    @Test
    public void test6() {
        Map<Status, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        collect.keySet().forEach(item -> System.out.println(collect.get(item)));

        System.out.println("---------------------");
        Map<Status, Map<String, List<Employee>>> collect1 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (e.getAge() <= 30) {
                        return "青年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(collect1);
    }

    @Test
    public void test5() {
        Long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        System.out.println("-------------------------");

        Double average = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(average);

        System.out.println("-------------------------");

        Optional<Employee> maxSalary = employees.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println(maxSalary.get());

        System.out.println("------------------------");

        Optional<Employee> minAge = employees.stream()
                .collect(Collectors.minBy(Comparator.comparingInt(Employee::getAge)));
        System.out.println(minAge.get());

        System.out.println("------------------------");

        Optional<Double> minSalary2 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(minSalary2.get());


    }

    @Test
    public void test4() {
        List<String> collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(collect);

        System.out.println("----------------------");

        Set<String> set = employees.stream().map(employee -> employee.getName())
                .collect(Collectors.toSet());
        System.out.println(set);

        System.out.println("----------------------");

        List<Double> list3 = employees.stream().map(Employee::getSalary)
                .filter((e) -> e > 6000)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list3);

        System.out.println("--------------------");

        HashSet<Employee> collect1 = employees.stream()
                .collect(Collectors.toCollection(HashSet::new));
        collect1.forEach(System.out::println);


    }

    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        System.out.println("-----------------------");

        Optional<Double> optional = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .reduce(Double::sum);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
    }


    @Test
    public void test2() {
        long count = employees.stream().count();
        System.out.println(count);

        System.out.println("--------------------");

        Optional<Employee> optional1 = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        if (optional1.isPresent()) {
            System.out.println(optional1.get());
        }

        System.out.println("----------------");
        Optional<Employee> min = employees.stream()
                .filter((e) -> Status.BUSY.equals(e.getStatus()))
                .min(Comparator.comparingInt(Employee::getAge));
        if (min.isPresent()) {
            System.out.println(min.get());
        }


    }

    @Test
    public void test1() {
        boolean b1 = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b1);

        System.out.println("-----------------");

        boolean b2 = employees.stream()
                .anyMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println(b2);

        System.out.println("-----------------");

        boolean b3 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b3);

        System.out.println("-----------------");

        Optional<Employee> first = employees.stream()
                .filter((e) -> e.getAge() > 30)
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .findFirst();
        if (first.isPresent()) {
            System.out.println(first.get());
        }

        System.out.println("-----------------");
        Optional<Employee> any = employees.stream()
                .filter(e -> e.getSalary() > 66000)
                .findAny();
        System.out.println(any);
        if (any.isPresent()) {
            System.out.println(any.get());
        }
        System.out.println("1111");
    }


}
