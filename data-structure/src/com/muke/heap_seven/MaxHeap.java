package com.muke.heap_seven;

import com.muke.array_zero.Array;

/**
 * 用数组存储最大堆
 * Program Name: data-structure
 * Created by yanlp on 2018/11/24
 *
 * @author yanlp
 * @version 1.0
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * heapity,将任意数组整理成堆的形状
     *
     * @param arr
     */
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        // 从最后一个节点的父节点 ,进行下沉
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 堆中个数
     *
     * @return
     */
    public int getSize() {
        return data.getSize();
    }

    /**
     * 堆中是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 根据给出索引,找出其父索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index is <= 0 ,doesn't have parent");
        }
        return (index - 1) / 2;
    }

    /**
     * 根据给出索引,找出其左孩子索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 根据给出索引,找出其右孩子索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        // 新添加的元素上浮
        this.siftUp(data.getSize() - 1);
    }

    /**
     * 上浮
     *
     * @param k 要上浮的元素的索引,即新添加的元素所在的索引
     */
    private void siftUp(int k) {
        // 当k > 0 , 并且 当前的父元素小于新添加的元素 ,新添加的元素进行上浮
        while (k > 0 && (data.get(parent(k)).compareTo(data.get(k)) < 0)) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 取出堆中最大元素
     *
     * @return
     */
    public E extractMax() {
        E ret = findMax();
        // 把最后一个元素放到索引为0的位置,即堆顶
        data.swap(0, data.getSize() - 1);
        // 将最后一个元素删除
        data.removeLast();
        // 将之前的最后一个元素(即现在的堆顶)下沉,从0位置开始
        this.siftDown(0);
        return ret;
    }

    /**
     * 下沉
     *
     * @param k 索引
     */
    private void siftDown(int k) {
        // 当元素左孩子的索引 还小于 数组的个数时,进行逻辑
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            // 如果有右孩子,而且右孩子 比 比左孩子大, 则使用右孩子的索引
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) >= 0) {
                j = rightChild(k);
            }
            // data.get(j) 为 左右孩子中较大值 , 如果当前元素值 大于等于 左右较大值的话, 循环终止
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            // 交换
            data.swap(k, j);
            k = j;
        }

    }

    /**
     * 找出最大元素,即索引为0的元素
     *
     * @return
     */
    public E findMax() {
        if (data.getSize() < 0) {
            throw new IllegalArgumentException("cannot find max,data is empty");
        }
        return data.get(0);
    }

    /**
     * 取出最大元素,并将其替换成新元素 ,一次 O(logn)
     *
     * @param e 新元素
     * @return
     */
    public E replace(E e) {
        E ret = findMax();
        // 将堆顶元素进行替换
        data.set(0, e);
        // 下沉
        siftDown(0);
        return ret;
    }
}
