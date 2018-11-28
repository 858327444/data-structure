package com.muke.heap_seven;

import com.muke.queue_two.Queue;

/**
 * 基于最大堆实现的优先队列
 * Program Name: data-structure
 * Created by yanlp on 2018/11/24
 *
 * @author yanlp
 * @version 1.0
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
