package com.muke.queue_two;

/**
 * 队列 先进先出 FIFO
 * Program Name: data-structure
 * Created by yanlp on 2018/10/12
 *
 * @author yanlp
 * @version 1.0
 */
public interface Queue<E> {
    /**
     * 向队尾添加元素
     */
    void enqueue(E e);

    /**
     * 移除队首元素
     */
    E dequeue();

    /**
     * 获取队首元素
     *
     * @return
     */
    E getFront();

    /**
     * 获取队列大小
     *
     * @return
     */
    int getSize();

    /**
     * 队列是否为空
     *
     * @return
     */
    boolean isEmpty();


}
