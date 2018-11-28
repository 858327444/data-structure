package com.muke.stack_one;

import java.util.Random;

/**
 * Program Name: data-structure
 * Created by yanlp on 2018/10/12
 *
 * @author yanlp
 * @version 1.0
 */
public class Main {

    /**
     * 测试栈时间
     *
     * @param stack
     * @param opCount
     * @return
     */
    private static double testStackTime(Stack stack, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
//        ArrayStack<Integer> stack_one = new ArrayStack<>();
//        for (int i = 0; i < 5; i++) {
//            stack_one.push(i);
//            System.out.println(stack_one);
//        }
//        System.out.println(stack_one.pop());
//        System.out.println(stack_one);
//
//        System.out.println(stack_one.peek());
//
//        System.out.println(stack_one.getCapacity());
//
//        System.out.println(stack_one.getSize());
//
//        System.out.println(stack_one.isEmpty());

        int opCount = 100000;
        double arrayTime = testStackTime(new ArrayStack(), opCount);
        System.out.println("ArrayStack time: " + arrayTime + " 秒");// 5.838309776

        double linkListTime = testStackTime(new LinkedListStack<>(), opCount);
        System.out.println("LinkedListStack time: " + linkListTime + " 秒");// 0.019787879


    }
}
