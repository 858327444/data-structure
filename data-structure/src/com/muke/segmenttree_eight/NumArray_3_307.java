package com.muke.segmenttree_eight;

/**
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * <p>
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 * <p>
 * 示例:
 * <p>
 * Given nums = [1, 3, 5]
 * <p>
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 说明:
 * <p>
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 */
public class NumArray_3_307 {

    private SegmentTree<Integer> segmentTree;
    public NumArray_3_307(int[] nums) {
        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < data.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data,(a,b) -> a + b);
        }
    }

    public void update(int i, int val) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Array is empty");
        }
        segmentTree.set(i,val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Array is empty");
        }
        return segmentTree.query(i,j);
    }

    private interface Merger<E> {
        E merge(E a, E b);
    }
    private class SegmentTree<E> {
        /**
         * 线段树
         */
        private E[] tree;
        private E[] data;
        private Merger<E> merger;

        public SegmentTree(E[] arr, Merger<E> merger) {
            this.merger = merger;
            data = (E[]) new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                data[i] = arr[i];
            }
            // 需要开辟4倍空间,进行存储
            tree = (E[]) new Object[4 * arr.length];
            //创建线段树
            buildSegmentTree(0, 0, arr.length - 1);
        }


        /**
         * 将index位置的值，更新为e
         *
         * @param index
         * @param e
         */
        public void set(int index, E e) {
            if (index < 0 || index >= data.length) {
                throw new IllegalArgumentException("Index is illegal");
            }
            data[index] = e;
            set(0, 0, data.length - 1, index, e);
        }

        /**
         * 在以treeIndex为根的线段树中,更新index的值为e
         *
         * @param treeIndex
         * @param l
         * @param r
         * @param index
         * @param e
         */
        private void set(int treeIndex, int l, int r, int index, E e) {
            if (l == r) {
                tree[treeIndex] = e;
                return;
            }
            int mid = l + (r - l) / 2;
            int leftChildIndex = leftChild(treeIndex);
            int rightChildIndex = rightChild(treeIndex);
            if (index >= mid + 1) {
                // 右边
                set(rightChildIndex, mid + 1, r, index, e);
            } else {
                // 左边
                set(leftChildIndex, l, mid, index, e);
            }
            tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
        }
        /**
         * 在treeIndex的位置创建表示区间 [l,r] 的线段树
         *
         * @param treeIndex 当前要创建线段树所在的根节点索对应的索引
         * @param l         左端点
         * @param r         右端点
         */
        private void buildSegmentTree(int treeIndex, int l, int r) {
            if (l == r) {
                tree[treeIndex] = data[l];
                return;
            }
            int leftChildIndex = leftChild(treeIndex);
            int rightChildIndex = rightChild(treeIndex);
            // 其实与 (l + r) / 2 一样,考虑到 如果 l + r  的值超出Integer最大值,会报错
            int mid = l + (r - l) / 2;
            buildSegmentTree(leftChildIndex, l, mid);
            buildSegmentTree(rightChildIndex, mid + 1, r);
            // 根据业务需求,自己定义Merger接口,例如求和
            tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);

        }

        /**
         * 返回区间[queryL, queryR]的值
         *
         * @param queryL
         * @param queryR
         * @return
         */
        public E query(int queryL, int queryR) {

            if (queryL < 0 || queryL >= data.length ||
                    queryR < 0 || queryR >= data.length || queryL > queryR)
                throw new IllegalArgumentException("Index is illegal.");

            return query(0, 0, data.length - 1, queryL, queryR);
        }

        /**
         * 在以treeIndex为根的线段树中[l,r]的范围里,搜索区间[queryl,queryR]的值
         *
         * @param treeIndex
         * @param l
         * @param r
         * @param queryL
         * @param queryR
         * @return
         */
        private E query(int treeIndex, int l, int r, int queryL, int queryR) {
            if (l == queryL && r == queryR)
                return tree[treeIndex];

            // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
            int mid = l + (r - l) / 2;

            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            if (queryL >= mid + 1) {
                // 如果要查询的在右半侧
                return query(rightTreeIndex, mid + 1, r, queryL, queryR);
            } else if (queryR <= mid) {
                // 如果要查询的在左半侧
                return query(leftTreeIndex, l, mid, queryL, queryR);
            }

            // 一部分在左半侧,一部分在右半侧
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftResult, rightResult);
        }

        public int getSize() {
            return data.length;
        }

        public E get(int index) {
            if (index < 0 || index > data.length) {
                throw new IllegalArgumentException("Index is illegal");
            }
            return data[index];
        }

        /**
         * 返回完全二叉树的数组表示中,一个索引所表示的元素的左孩子节点所在的索引
         *
         * @param index
         * @return
         */
        private int leftChild(int index) {
            return 2 * index + 1;
        }

        /**
         * 返回完全二叉树的数组表示中,一个索引所表示的元素的右孩子节点所在的索引
         *
         * @param index
         * @return
         */
        private int rightChild(int index) {
            return 2 * index + 2;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append('[');
            for (int i = 0; i < tree.length; i++) {
                if (tree[i] != null)
                    res.append(tree[i]);
                else
                    res.append("null");

                if (i != tree.length - 1)
                    res.append(", ");
            }
            res.append(']');
            return res.toString();
        }
    }



}
