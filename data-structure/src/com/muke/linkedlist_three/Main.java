package com.muke.linkedlist_three;

/**
 * Program Name: data-structure
 * Created by yanlp on 2018/10/13
 *
 * @author yanlp
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2, 666);
        System.out.println(linkedList);

        boolean contains = linkedList.contains(55555);
        System.out.println(contains);

        Integer a = linkedList.get(2);
        System.out.println(a);

        linkedList.set(3,11111);
        System.out.println(linkedList);

        linkedList.remove(3);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);

    }
}
