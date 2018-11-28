package com.muke.heap_seven;

import org.junit.Test;

import java.util.Random;

/**
 * Program Name: data-structure
 * Created by yanlp on 2018/11/24
 *
 * @author yanlp
 * @version 1.0
 */
public class Main {

    @Test
    public void test1() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        int n = 100000;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("extractMax method is error");
            }
        }
        System.out.println("Complete");
    }

    @Test
    public void test2() {
        int n = 1000000;
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        double time1 = testHeap(testData, false);
        System.out.println("Without heapify time1: " + time1);
        double time2 = testHeap(testData, true);
        System.out.println("With heapify time2: " + time2);
    }

    /**
     * 测试
     *
     * @param testData  测试数组
     * @param isHeapify 是否为heapify
     * @return
     */
    public double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>();
            for (Integer num : testData) {
                maxHeap.add(num);
            }
        }
        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("extractMax method is error");
            }
        }
        System.out.println("Complete");
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

}
