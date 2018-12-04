package com.java8.test;

import com.java8.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

public class TestLambda1 {
    List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 20, 5555.55),
            new Employee("李四", 37, 7777.77),
            new Employee("王五", 39, 4444.44),
            new Employee("赵六", 20, 8888.88),
            new Employee("田七", 56, 9999.99)
    );

    @Test
    public void test1() {
        employeeList.stream()
                .filter((e) -> {
                    System.out.println("短路.");
                    return e.getSalary() > 6000;
                })
                .limit(2)
                .map(Employee::getName)
                .forEach(employee -> System.out.println(employee));

    }

    @Test
    public void test2() {
        Optional<Double> sum = employeeList.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(sum.get());
    }

    @Test
    public void test3() {
        long reduce = LongStream.rangeClosed(0L, 50000000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(reduce);
    }


}
