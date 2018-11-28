package com.muke.stack_one;

/**
 * 栈 后进先出  FILO
 * Program Name: data-structure
 * Created by yanlp on 2018/10/12
 *
 * @author yanlp
 * @version 1.0
 */
public interface Stack<E> {
    /**
     * 向栈尾添加元素
     *
     * @param e
     */
    void push(E e);

    /**
     * 移除栈尾的元素
     */
    E pop();

    /**
     * 获取栈尾的最后一个元素
     */
    E peek();

    /**
     * 栈的大小
     *
     * @return
     */
    int getSize();

    /**
     * 栈是否为空
     *
     * @return
     */
    boolean isEmpty();

}
