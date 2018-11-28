package com.muke.linkedlist_three;

/**
 * 递归求和
 * Program Name: data-structure
 * Created by yanlp on 2018/10/16
 *
 * @author yanlp
 * @version 1.0
 */
public class Sum {

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    /**
     * 递归求和,计算arr[left...n)范围里的数字和
     *
     * @param arr
     * @param left 左索引
     * @return
     */
    private static int sum(int[] arr, int left) {
        // 遍历到了最后
        if (left == arr.length) {
            return 0; // 求解最基本问题
        }
        return arr[left] + sum(arr, left + 1); // 把原问题转化为最小问题
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(sum(arr));
    }
}
