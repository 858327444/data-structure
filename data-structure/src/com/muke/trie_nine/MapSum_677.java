package com.muke.trie_nine;

import java.util.TreeMap;

/**
 * 实现一个 MapSum 类里的两个方法，insert 和 sum。
 * 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
 * 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 * 示例 1:
 * 输入: insert("apple", 3), 输出: 0
 * 输入: sum("ap"), 输出: 3
 * 输入: insert("app", 2), 输出: 0
 * 输入: sum("ap"), 输出: 5
 * Program Name: data-structure
 * Created by yanlp on 2018/12/5
 *
 * @author yanlp
 * @version 1.0
 */
public class MapSum_677 {
    private class Node {
        public int value;
        public TreeMap<Character, Node> next;

        public Node() {
            this(0);
        }

        public Node(int value) {
            this.value = value;
            next = new TreeMap<>();
        }
    }

    private Node root;

    /**
     * Initialize your data structure here.
     */
    public MapSum_677() {
        root = new Node();

    }

    public void insert(String word, int val) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    private int sum(Node node) {
        int res = node.value;
        for (char c : node.next.keySet()) {
            res += sum(node.next.get(c));
        }
        return res;
    }
}
