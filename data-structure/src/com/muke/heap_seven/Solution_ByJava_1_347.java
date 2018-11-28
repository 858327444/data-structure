package com.muke.heap_seven;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.PriorityQueue;

/**
 * 基于java 自带的优先队列实现的第一种方式
 * Program Name: data-structure
 * Created by yanlp on 2018/11/24
 *
 * @author yanlp
 * @version 1.0
 */
public class Solution_ByJava_1_347 {
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

    private class Freq implements Comparable<Freq> {
        // 元素
        public int e;
        // 频次
        public int freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        //TODO java自身的Priority.是基于最小堆实现的,与自己写的不一样,所以下面返回值也修改了
        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq) {
                return -1;
            } else if (this.freq > another.freq) {
                return 1;
            }
            return 0;
        }
    }
}
