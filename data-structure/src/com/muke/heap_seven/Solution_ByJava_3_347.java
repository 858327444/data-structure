package com.muke.heap_seven;

import java.util.*;
import java.util.PriorityQueue;

/**
 * 基于java 自带的优先队列实现的第三种方式
 * Program Name: data-structure
 * Created by yanlp on 2018/11/24
 *
 * @author yanlp
 * @version 1.0
 */
public class Solution_ByJava_3_347 {
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
        PriorityQueue<Freq> pq = new PriorityQueue<>(new Comparator<Freq>() {
            @Override
            public int compare(Freq a, Freq b) {
                return a.freq - b.freq;
            }
        });
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(new Freq(key, map.get(key)));
            } else if (map.get(key) > pq.peek().freq) {
                pq.remove();
                pq.add(new Freq(key, map.get(key)));
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove().e);
        }
        return res;
    }


    private class Freq {
        // 元素
        public int e;
        // 频次
        public int freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

    }
}
