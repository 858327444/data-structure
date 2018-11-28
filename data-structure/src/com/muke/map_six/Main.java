package com.muke.map_six;

import java.util.ArrayList;
import java.util.List;

/**
 * Program Name: data-structure
 * Created by yanlp on 2018/11/24
 *
 * @author yanlp
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        test(list);

        System.out.println(list);
    }

    private static  List  test(List list ) {
        list.add(1);
        return list;
    }
}
