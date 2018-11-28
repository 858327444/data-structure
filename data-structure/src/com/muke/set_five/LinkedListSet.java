package com.muke.set_five;

import com.muke.linkedlist_three.LinkedList;

import java.util.ArrayList;

/**
 * 基于链表的集合类
 * Program Name: data-structure
 * Created by yanlp on 2018/10/18
 *
 * @author yanlp
 * @version 1.0
 */
public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }


    @Override
    public void add(E e) {
        if (!contains(e)) {
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if (FileOperation.readFile("D:\\ideaWorkSpaces\\data-structure\\data-structure\\src\\com\\muke\\set_five\\pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            LinkedListSet<String> set1 = new LinkedListSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        if (FileOperation.readFile("D:\\ideaWorkSpaces\\data-structure\\data-structure\\src\\com\\muke\\set_five\\a-tale-of-two-cities.txt", words2)) {
            System.out.println("Total words: " + words2.size());

            LinkedListSet<String> set2 = new LinkedListSet<>();
            for (String word : words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间为: " + (endTime - startTime) + " 毫秒");
    }
}
