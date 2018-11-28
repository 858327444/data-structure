package com.muke.heap_seven;

import java.util.*;
import java.util.PriorityQueue;

/**
 * 基于java 自带的优先队列实现的第四种方式
 * Program Name: data-structure
 * Created by yanlp on 2018/11/24
 *
 * @author yanlp
 * @version 1.0
 */
public class Solution_ByJava_4_347 {
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
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer  a, Integer b) {
                return  map.get(a) - map.get(b);
            }
        });
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;
    }


}
