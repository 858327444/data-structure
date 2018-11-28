package com.muke.bst_four;

public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
//        int[] nums = {5, 3, 6, 8, 4, 2};
        int[] nums = {28,16,30,13,22,29,42,12,15,21,24,35};
        for (int num : nums) {
            bst.add(num);
        }
        /*
             5
            / \
           3   6
           / \  \
          2   4  8
         */
//        bst_four.preOrder();
//        System.out.println("=========");
//        bst_four.preOrderNoneRecursive();
//        bst_four.inOrder();
//        bst_four.postOrder();

//        bst_four.postOrder();
//        System.out.println("============");
//        bst_four.levelOrder();

//        System.out.println(bst_four.minimum());
//        System.out.println(bst_four.maximum());

        System.out.println(bst.removeMin());
        System.out.println(bst);
        System.out.println("=====================");
        System.out.println(bst.removeMax());
        System.out.println(bst);

        System.out.println("=============");
        bst.remove(16);
        System.out.println(bst);

    }

//    public static void main(String[] args) {
//        int n = 1000;
//        Random random = new Random();
//        BST<Integer> bst_four = new BST<>();
//        for (int i = 0; i < n; i++) {
//            int ran = random.nextInt(10000);
//            bst_four.add(ran);
//        }
//        List<Integer> list = new ArrayList<>();
//        while (!bst_four.isEmpty()) {
//            list.add(bst_four.removeMin());
//        }
//        System.out.println(list);// 如果是removeMin方法,从小到大排列; 如果是removemax方法,从大到小排列
//        for (int i = 1; i < list.size(); i++) {
//            if (list.get(i - 1) > list.get(i)) {
//                throw new IllegalArgumentException("Removemin is error");
//            }
//        }
//        System.out.println("Removemin is success");
//
//
//    }
}
