package com.muke.queue_two;

/**
 * 带有尾指针的链表,使用链表实现队列
 * Program Name: data-structure
 * Created by yanlp on 2018/10/15
 *
 * @author yanlp
 * @version 1.0
 */
public class LinkedListQueue<E> implements Queue<E> {
    /**
     * 头结点
     */
    private Node head;
    /**
     * 尾节点
     */
    private Node tail;
    /**
     * 元素个数
     */
    private int size;

    private class Node {
        /**
         * 数据
         */
        public E e;
        /**
         * 下一个节点
         */
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            // 如果该对列中,没有元素
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Dequeue failed.Cannot dequeue from an empty queue_two.");
        }
        Node ret = head;
        head = head.next;
        ret.next = null;
        if (head == null) {
            // 删除元素后,没有元素了
            tail = null;
        }
        size--;
        return ret.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("GetFront failed.Cannot getFront from an empty queue_two.");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue front: ");
        Node cur = head;
        while (cur != null) {
            res.append(cur + " -> ");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListQueue<>();
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
