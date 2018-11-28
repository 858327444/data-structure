package com.muke.heap_seven;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * 基于自己写的Priority实现
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * Program Name: data-structure
 * Created by yanlp on 2018/11/24
 *
 * @author yanlp
 * @version 1.0
 */
public class Solution_ByMine_347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // key是元素,value是频次
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        PriorityQueue<Freq> pq = new PriorityQueue<Freq>();
        for (int key : map.keySet()) {
            if (pq.getSize() < k) {
                pq.enqueue(new Freq(key, map.get(key)));
            } else if (map.get(key) > pq.getFront().freq) {
                pq.dequeue();
                pq.enqueue(new Freq(key, map.get(key)));
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.dequeue().e);
        }
        return res;
    }

    private class Freq implements Comparable<Freq> {
        // 元素
        public int e;
        // 频次
        public int freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq) {
                return 1;
            } else if (this.freq > another.freq) {
                return -1;
            }
            return 0;
        }
    }

    private class MaxHeap<E extends Comparable<E>> {
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
            // 从最后一个节点的非叶子节点 ,进行下沉
            for (int i = parent(arr.length - 1); i >= 0; i--) {
                siftDown(i);
            }
        }

        // 堆中个数
        public int getSize() {
            return data.getSize();
        }

        // 堆中是否为空
        public boolean isEmpty() {
            return data.isEmpty();
        }

        // 根据给出索引,找出其父索引
        private int parent(int index) {
            if (index <= 0) {
                throw new IllegalArgumentException("index is <= 0 ,doesn't have parent");
            }
            return (index - 1) / 2;
        }

        // 根据给出索引,找出其左孩子索引
        private int leftChild(int index) {
            return index * 2 + 1;
        }

        // 根据给出索引,找出其右孩子索引
        private int rightChild(int index) {
            return index * 2 + 2;
        }

        // 向堆中添加元素
        public void add(E e) {
            data.addLast(e);
            // 新添加的元素上浮
            this.siftUp(data.getSize() - 1);
        }

        // k为要上浮的元素的索引,即新添加的元素所在的索引
        private void siftUp(int k) {
            // 当k > 0 , 并且 当前的父元素小于新添加的元素 ,新添加的元素进行上浮
            while (k > 0 && (data.get(parent(k)).compareTo(data.get(k)) < 0)) {
                data.swap(k, parent(k));
                k = parent(k);
            }
        }

        // 取出堆中最大元素
        public E extractMax() {
            E ret = findMax();
            // 把最后一个元素放到索引为0的位置
            data.swap(0, data.getSize() - 1);
            // 将最后一个元素删除
            data.removeLast();
            // 将最后一个元素下沉
            this.siftDown(0);
            return ret;
        }

        // 下沉
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

        public E findMax() {
            if (data.getSize() < 0) {
                throw new IllegalArgumentException("cannot find max,data is empty");
            }
            return data.get(0);
        }

        // 取出最大元素,并将其替换成新元素 ,一次 O(logn)
        public E replace(E e) {
            E ret = findMax();
            // 将堆顶元素进行替换
            data.set(0, e);
            // 下沉
            siftDown(0);
            return ret;
        }


    }

    private class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
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

    private class Array<E> {
        /**
         * 元素数据
         */
        private E[] data;
        /**
         * 元素个数
         */
        private int size;

        /**
         * 默认容量为10
         */
        private static final int DEFAULT_CAPACITY = 10;

        /**
         * 构造函数,传入容量为capacity,进行构造数组
         *
         * @param capacity
         */
        public Array(int capacity) {
            data = (E[]) new Object[capacity];
            size = 0;
        }

        public Array(E[] arr) {
            data = (E[]) new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                data[i] = arr[i];
            }
            size = arr.length;
        }

        /**
         * 构造函数,默认容量为10的数组
         */
        public Array() {
            this(DEFAULT_CAPACITY);
        }

        /**
         * 获取数组的容量
         *
         * @return
         */
        public int getCapacity() {
            return data.length;
        }

        /**
         * 获取数组的元素个数
         *
         * @return
         */
        public int getSize() {
            return size;
        }

        /**
         * 数组是否为空
         *
         * @return
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 向数组中末尾增加元素
         *
         * @param e
         */
        public void addLast(E e) {
//        if (size == data.length) {
//            throw new IllegalArgumentException("AddLast failed. Array is full.");
//        }
//        data[size] = e;
//        size++;
            this.add(size, e);
        }

        /**
         * 向数组第一个位置增加元素
         *
         * @param e
         */
        public void addFirst(E e) {
            this.add(0, e);
        }

        /**
         * 在索引为index的位置,新添加元素
         *
         * @param index
         * @param e
         */
        public void add(int index, E e) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Add failed,required index >= 0 and index <= size");
            }
            if (size == data.length) {
                // 扩容
                resize(2 * data.length);
            }
            // 将index后的元素依次向后移动
            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
            // 将index的位置值设置为e
            data[index] = e;
            size++;
        }

        /**
         * 重置容量
         *
         * @param newCapacity
         */
        private void resize(int newCapacity) {
            E[] newData = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }

        /**
         * 数组中是否包含该值
         *
         * @param e
         * @return
         */
        public boolean contains(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 查找数组中元素e的第一个索引,如果没有该元素,返回-1
         *
         * @param e
         * @return
         */
        public int findIndex(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e)) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * 查找数组中值为e的所有索引
         *
         * @param e
         * @return
         */
        public List<Integer> findAllIndex(E e) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e)) {
                    res.add(i);
                }
            }
            if (res.size() > 0) {
                return res;
            }
            return null;
        }

        /**
         * 删除索引为index位置的元素,返回该元素
         *
         * @param index
         * @return
         */
        public E remove(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Get failed,index is illegal");
            }
            // 要删除的元素
            E res = data[index];
            for (int i = index + 1; i < size; i++) {
                data[i - 1] = data[i];
            }
            size--;
            // 使用4 是懒减容,后面会继续讲
            if (size == data.length / 4 && data.length / 2 != 0) {
                // 减容
                resize(data.length / 2);
            }
            return res;
        }

        /**
         * 删除第一个元素,返回该元素
         *
         * @return
         */
        public E removeFirst() {
            return remove(0);
        }

        /**
         * 删除最后一个元素,返回该元素
         *
         * @return
         */
        public E removeLast() {
            return remove(size - 1);
        }

        /**
         * 删除值为e的第一个元素
         *
         * @param e
         */
        public void removeElement(E e) {
            int index = findIndex(e);
            if (index != -1) {
                remove(index);
            }
        }

        /**
         * 删除数组中值为e的所有元素
         *
         * @param e
         */
        public void removeAllElement(E e) {
            for (int i = 0; i < size; i++) {
                int index = findIndex(e);
                if (index != -1) {
                    remove(index);
                }
            }
        }


        /**
         * 获取索引为index的元素
         *
         * @param index
         */
        public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Get failed,index is illegal");
            }
            return data[index];
        }

        /**
         * 获取第一个元素
         *
         * @return
         */
        public E getFirst() {
            return get(0);
        }

        /**
         * 获取最后一个元素
         *
         * @return
         */
        public E getLast() {
            return get(size - 1);
        }

        /**
         * 设置索引为index的元素值为e
         *
         * @param index
         * @param e
         */
        public void set(int index, E e) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Get failed,index is illegal");
            }
            data[index] = e;
        }

        // 将索引为i位置的元素,和索引为j位置的元素进行交换
        public void swap(int i, int j) {
            if (i < 0 || j < 0 || i >= size || j >= size)
                throw new IllegalArgumentException("Index is illegal");
            E tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }

        @Override
        public String toString() {
            StringBuilder sbf = new StringBuilder();
            sbf.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
            sbf.append("[");
            for (int i = 0; i < size; i++) {
                sbf.append(data[i]);
                if (i != size - 1) {
                    sbf.append(", ");
                }
            }
            sbf.append("]");
            return sbf.toString();
        }


    }

    private interface Queue<E> {
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

    @Test
    public void test1() {
        int[] nums = {1, 1, 1, 2, 2, 3};

        List<Integer> integers = this.topKFrequent(nums, 2);
        System.out.println(integers);
    }
}
