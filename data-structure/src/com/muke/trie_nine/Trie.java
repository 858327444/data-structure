package com.muke.trie_nine;

import java.util.TreeMap;

/**
 * 字典树
 * Trie相对于字符串来说,会比BSTSet性能会好些
 */
public class Trie {
    private class Node {
        // 当前这个节点,是否代表这个单词的结尾,即是否访问到一个单词了
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    // 获取Trie中存储的单词数量
    public int getSize() {
        return size;
    }

    // 向Trie中添加一个新的单词
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 没有存储过该字符
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        // 循环结束后,最后一个节点的isWord需要设置为true
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    // Trie中是否精确包含某个单词
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        // 这里需要注意,返回是cur.isWord, 例如 Trie中有panda这个单词,但是要查询的是pan,但是不回到Trie中有没有添加过pan,所以要返回的是cur.isWord
        return cur.isWord;
    }

    // 查找传递过来的参数是否是Trie中某个单词的前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                // 不是某个单词的前缀
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
