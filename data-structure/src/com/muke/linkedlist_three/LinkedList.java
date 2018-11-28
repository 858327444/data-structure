package com.muke.linkedlist_three;

/**
 * 链表
 * Program Name: data-structure
 * Created by yanlp on 2018/10/13
 *
 * @author yanlp
 * @version 1.0
 */
public class LinkedList<E> {

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

    /**
     * 头部虚拟节点
     */
    private Node dummyHead;
    /**
     * 链表元素个数
     */
    private int size;

    /**
     * 构造函数
     */
    public LinkedList() {
        // 虚拟头结点,索引为"0"前其实还有个元素,是null
        dummyHead = new Node(null, null);
        size = 0;
    }

    /**
     * 向头部添加元素
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 向链表末尾添加元素
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 向索引为index的位置,添加元素 ;在链表中不是一个常用的操作，练习用
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed,Illegal index");
        }
        Node prev = dummyHead;
        // 找到要添加元素位置的前一个元素 prev
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // prev的后一个元素赋值给node的后一个元素 ; node复制给prev的后一个元素,实现功能
//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;
        prev.next = new Node(e, prev.next); // 上面三行代码合一
        size++;
    }

    /**
     * 链表元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取索引为index的元素, 链表中不是一个常用操作,练习用
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }
        // 当前元素
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
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
     * 更新元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        cur.e = e;
    }

    /**
     * 链表中是否有该元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 删除索引为index的元素,链表中不常用该操作,练习用
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.e;
    }

    /**
     * 删除第一个元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从链表中删除元素e
     *
     * @param e
     */
    public void removeElement(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + " -> ");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }

}
