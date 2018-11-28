package com.muke.array_zero;

import java.util.List;

/**
 * Program Name: Array
 * Created by yanlp on 2018/10/11
 *
 * @author yanlp
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>(10);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.add(2, 300);
        System.out.println(array);
//
//        array_zero.addFirst(-2);
//        System.out.println(array_zero); // [-2, 0, 1, 300, 2, 3, 4, 5, 6, 7, 8, 9]

        array.remove(2);
        System.out.println(array);// [-2, 0, 300, 2, 3, 4, 5, 6, 7, 8, 9]

        boolean contains = array.contains(-999);
        System.out.println(contains);

        array.add(4, 0);
        System.out.println(array);// [-2, 0, 300, 2, 0, 3, 4, 5, 6, 7, 8, 9]

        int index = array.findIndex(0);
        System.out.println(index);

        List<Integer> list = array.findAllIndex(0);
        System.out.println(list);

        array.removeElement(0);
        System.out.println(array);// [-2, 300, 2, 0, 3, 4, 5, 6, 7, 8, 9]

        array.removeAllElement(0);
        System.out.println(array);// [-2, 300, 2, 3, 4, 5, 6, 7, 8, 9]

        int element = array.get(2);
        System.out.println(element);

        array.set(2, 90);
        System.out.println(array);// [-2, 300, 90, 3, 4, 5, 6, 7, 8, 9]


    }
}
