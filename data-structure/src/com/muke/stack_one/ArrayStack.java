package com.muke.stack_one;

import com.muke.array_zero.Array;

/**
 * 基于数组实现的栈
 * Program Name: data-structure
 * Created by yanlp on 2018/10/12
 *
 * @author yanlp
 * @version 1.0
 */
public class ArrayStack<E> implements Stack<E> {
    /**
     * 数组
     */
    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array(capacity);
    }

    public ArrayStack() {
        array = new Array();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");

        return res.toString();
    }
}
