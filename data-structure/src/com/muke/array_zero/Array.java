package com.muke.array_zero;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组
 * Program Name: Array
 * Created by yanlp on 2018/10/11
 *
 * @author yanlp
 * @version 1.0
 */
public class Array<E> {
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

    /**
     * 将索引为i位置的元素,和索引为j位置的元素进行交换
     *
     * @param i
     * @param j
     */
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