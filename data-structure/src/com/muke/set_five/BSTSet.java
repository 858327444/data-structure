package com.muke.set_five;

import com.muke.bst_four.BST;

import java.util.ArrayList;

/**
 * 基于二分搜索树的集合类   时间复杂度为 O(logn)
 * Program Name: data-structure
 * Created by yanlp on 2018/10/18
 *
 * @author yanlp
 * @version 1.0
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if(FileOperation.readFile("D:\\ideaWorkSpaces\\data-structure\\data-structure\\src\\com\\muke\\set_five\\pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            BSTSet<String> set1 = new BSTSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();

        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        if(FileOperation.readFile("D:\\ideaWorkSpaces\\data-structure\\data-structure\\src\\com\\muke\\set_five\\a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());

            BSTSet<String> set2 = new BSTSet<>();
            for(String word: words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间为: " + (endTime - startTime) + " 毫秒");
    }
}
