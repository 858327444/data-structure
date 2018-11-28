package com.muke.bst_four;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * BinarySearchTree
 * 二分搜索树,因为此树元素必须具有可比较性,所以继承Comparable
 * 二分搜索树的每个节点值,必须大于其左子树所有节点的值 ; 必须小于右子树所有节点的值
 * Program Name: data-structure
 * Created by yanlp on 2018/10/13
 *
 * @author yanlp
 * @version 1.0
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        /**
         * 元素
         */
        public E e;
        /**
         * 左节点
         */
        public Node left;
        /**
         * 右节点
         */
        public Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 根节点
     */
    private Node root;
    /**
     * 元素个数
     */
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加元素
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的二分搜索树插入元素E,没有考虑新添加节点与原节点相等时,递归算法
     * 返回新插入节点后元素的根
     *
     * @param node
     * @param e
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;

        // 以下代码是之前返回值为void时较臃肿的代码,上述代码进行了改进
//        if (node.e.equals(e)) {
//            return;
//        } else if (e.compareTo(node.e) < 0 && node.left == null) { // 小于 并且 没有左节点
//            node.left = new Node(e);
//            size++;
//            return;
//        } else if (e.compareTo(node.e) > 0 && node.right == null) { // 大于 并且 没有右节点
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//        if (e.compareTo(node.e) < 0) { // 小于 并且 有左节点
//            add(node.left, e);
//        } else { // 大于 并且 有右节点
//            add(node.right, e);
//        }
    }

    /**
     * 是否包含元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 看以node为根的二分搜索树是否包含元素,递归算法
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.equals(node.e)) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }


    /**
     * 前序遍历,先访问节点,再访问左右子树
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历以node为根的二分搜索树,递归算法
     *
     * @param node
     */
    private void preOrder(Node node) {
        // 递归终止条件
        if (node == null) {
            return;
        }
        System.out.println(node.e);//28  16 13 22  30 29 42
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 前序遍历,非递归的写法
     */
    public void preOrderWithoutRecursive() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }


    /**
     * 中序遍历,先访问左子树,再访问节点,再访问右子树 .
     * 得到的结果是从小到大排序好的
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历以node为根的二叉搜索树,递归算法
     *
     * @param node
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);// 13 16  22 28  29 30 42
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历,先访问左子树 再访问右子树 再访问该节点
     * 后序遍历的一个应用:为二分搜索树释放内存
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 后序遍历以node为根的二叉搜索树,递归算法
     *
     * @param node
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);// 13 22 16 29 42 30 28
    }

    /**
     * 层序遍历  从根节点,从上到下,一层一层,从左向右遍历
     */
    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();// 队首元素
            System.out.println(cur.e);

            // 先添加左孩子,再添加右孩子
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    /**
     * 查找最小值 , 最底层没有左孩子,该节点肯定是最小值
     *
     * @return
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return minimum(root).e;
    }

    /**
     * 找以node为根的二分搜索树的最小值所在的节点,递归方法
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 查找最大值, 最底层没有右孩子,该节点肯定是最大值
     *
     * @return
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).e;
    }

    /**
     * 查找以node为根的二分搜索树的最大值所在的节点,递归方法
     *
     * @param node
     * @return
     */
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 删除最小元素所在的节点
     *
     * @return
     */
    public E removeMin() {
        E ret = minimum();
        // 删除节点后,返回新的节点 作为根, 因为很有可能删除的节点是有右孩子的,所以把该右孩子 作为之前 左孩子的根的左孩子
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除以node为根节点的二分树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            // 删除元素
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除最大元素所在的节点
     *
     * @return
     */
    public E removeMax() {
        E ret = maximum();
        // 删除节点后,返回新的节点 作为根, 因为很有可能删除的节点是有左孩子的,所以把该左孩子作为 之前右孩子的根的左孩子
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除以node为根节点的二分搜索树中的最大节点
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 从二分搜索树中删除元素为e的节点
     *
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除掉以node为根的二分搜索树中值为e的节点,递归算法
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else { // 找到了待删除的节点
            // 待删除节点没有左子树
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点没有右子树
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空,找到比待删除节点大的最小节点,即待删除节点右子树的最小节点,用这个节点顶替待删除节点的位置(也可以找左子树的最大值 都可以维持二分搜索树的平衡)
            // 这里的successor 在专业术语中叫后继
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }

    /**
     * 生成以node为根,深度为depth的描述二叉树的字符串
     *
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null \n");
        } else {
            res.append(generateDepthString(depth) + node.e + "\n");
            generateBSTString(node.left, depth + 1, res);
            generateBSTString(node.right, depth + 1, res);
        }
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

}
