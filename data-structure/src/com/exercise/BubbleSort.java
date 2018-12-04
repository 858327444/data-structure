package com.exercise;

/**
 * 冒泡排序
 * Program Name: data-structure
 * Created by yanlp on 2018/12/4
 *
 * @author yanlp
 * @version 1.0
 */
public class BubbleSort {
    // 没有优化
    public static void sortNoOptimize(int[] arr) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("没有优化耗时为: " + (endTime - startTime) + " 毫秒");
    }

    // 有优化
    public static void sortWithOptimize(int[] arr) {
        // 监控一趟过程有没有进行数据交换
        boolean exchange;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            exchange = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    // 进行了数据交换
                    exchange = true;
                }
            }
            // 如果发现在外层循环的某一层循环中,没有进行数据交换,说明内层循环 已经将数组排序好,,那么外层循环可以直接break掉
            if (!exchange) {
                long endTime = System.currentTimeMillis();
                System.out.println("拥有优化耗时为: " + (endTime - startTime) + " 毫秒");
                return;
            }
        }

    }


//    @Test
//    public void test1() {
//        int[] arr = new int[10000];
//        int[] tmp = new int[10000];
//
//        Random random = new Random();
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = random.nextInt(Integer.MAX_VALUE);
//            tmp[i] = random.nextInt(Integer.MAX_VALUE);
//        }
////        for (int i : arr) {
////            System.out.print(i + " ");
////        }
////        System.out.println();
////        // 有优化
////        sortWithOptimize(arr);
////        for (int i : arr) {
////            System.out.print(i + " ");
////        }
//        ExecutorService exec = Executors.newCachedThreadPool();
//        for(int i = 0 ; i < 2 ; i++) {
//            if (i == 1) {
//                exec.submit(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println(arr.length);
//                        sortWithOptimize(arr);
//                    }
//                });
//            } else {
//                exec.submit(new Runnable() {
//                    @Override
//                    public void run() {
//                        sortNoOptimize(arr);
//                    }
//                });
//            }
//        }
//        exec.shutdown();
//
//        // 没有优化
////            System.out.print(i + " ");
////        }
////        System.out.println();
//
//
////        for (int i : copy) {
////            System.out.print(i + " ");
////        }
//    }
//
}
