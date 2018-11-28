package com.muke.segmenttree_eight;

public class Main {
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> tree = new SegmentTree<Integer>(nums, (a, b) -> a + b);
//        System.out.println(tree);
        System.out.println(tree.query(3,5));
    }

}
