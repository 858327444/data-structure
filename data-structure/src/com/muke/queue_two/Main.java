package com.muke.queue_two;

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
     * 测试队列时间
     *
     * @param q
     * @param opCount
     * @return
     */
    private static double testQueueTime(Queue q, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        double arrayTime = testQueueTime(new ArrayQueue(), opCount);
        System.out.println("ArrayQueue time: " + arrayTime + " 秒");// 5.838309776

        double loopTime = testQueueTime(new LoopQueue(), opCount);
        System.out.println("LoopQueue time: " + loopTime + " 秒");// 0.019787879

        double linkTime = testQueueTime(new LinkedListQueue(), opCount);
        System.out.println("LoopQueue time: " + linkTime + " 秒");// 0.019787879

    }


}
