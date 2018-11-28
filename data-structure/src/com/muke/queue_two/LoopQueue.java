package com.muke.queue_two;

/**
 * 循环队列 (从队首移除元素,复杂度为O(1))
 * Program Name: data-structure
 * Created by yanlp on 2018/10/12
 *
 * @author yanlp
 * @version 1.0
 */
public class LoopQueue<E> implements Queue<E> {
    /**
     * 元素数组
     */
    private E[] data;
    /**
     * 队首元素的索引
     */
    private int front;
    /**
     * 下一个承载元素的索引(循环队列中,tail很有可能会比front小)
     */
    private int tail;
    /**
     * 队列长度
     */
    private int size;

    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    public LoopQueue(int capacity) {
        // 循环队列中,有一个空间是需要浪费的
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            // 队列满(其实有一个空间是浪费掉的),进行扩容
            resize(data.length * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    /**
     * 重置容量
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue_two");
        }
        E ret = data[front];
        data[front] = null;
        // 首个元素位置,进行向后移动一位
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            // 减容
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    /**
     * 获取容量
     *
     * @return
     */
    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
