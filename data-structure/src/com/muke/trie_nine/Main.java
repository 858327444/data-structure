package com.muke.trie_nine;

import com.muke.set_five.BSTSet;
import com.muke.set_five.FileOperation;

import java.util.ArrayList;

/**
 * 测试下Trie和BSTSet的性能
 */
public class Main {
    public static void main(String[] args) {
        test(true);
        test(false);
    }

    /**
     * 测试时间
     * @param isTrie 是否为trie
     */
    private static void test(boolean isTrie) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        String fileName = "D:\\ideaWorkSpace\\data-structure\\data-structure\\src\\com\\muke\\set_five\\pride-and-prejudice.txt";
        if (FileOperation.readFile(fileName, words)) {
            long startTime = 0;
            long endTime = 0;
            double runTime = 0;
            if (!isTrie) {
                startTime = System.nanoTime();
                BSTSet<String> set = new BSTSet<>();
                for (String word : words) {
                    set.add(word);
                }
                for (String word : words) {
                    set.contains(word);
                }
                endTime = System.nanoTime();
                runTime = (endTime - startTime) / 1000000000.0;
                System.out.println("BSTSet different words: " + set.getSize());
                System.out.println("BSTSet time: " + runTime + " 秒");

            } else {
                startTime = System.nanoTime();
                Trie trie = new Trie();
                for (String word : words) {
                    trie.add(word);
                }
                for (String word : words) {
                    trie.contains(word);
                }
                endTime = System.nanoTime();
                System.out.println("Trie different words: " + trie.getSize());
                System.out.println("Trie time: " + runTime + " 秒");
            }
        }
    }
}
