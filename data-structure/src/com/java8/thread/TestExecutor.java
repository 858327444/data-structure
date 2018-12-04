package com.java8.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * Program Name: myExercise
 * Created by yanlp on 2018/12/3
 *
 * @author yanlp
 * @version 1.0
 */
public class TestExecutor {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()  + " doing task");
                }
            });
        }
        // 关闭线程池
        exec.shutdown();
    }
}
