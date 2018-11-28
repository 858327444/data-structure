package com.muke.linkedlist_three;

/**
 * 递归调用解决 删除链表中等于给定值 val 的所有节点。
 * 示例:
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 * <p>
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 * Program Name: data-structure
 * Created by yanlp on 2018/10/15
 *
 * @author yanlp
 * @version 1.0
 */
public class SolutionWithRecursiveCall203 {

    public ListNode removeElements(ListNode head, int val) {
//        if (head == null) {
//            return null;
//        }
//        ListNode res = removeElements(head.next, val);
//        if (head.next.val == val) {
//            return res;
//        } else {
//            head.next = res;
//            return head;
//        }
        // 上面所有代码合一
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 6, 4, 5, 6};

        ListNode listNode1 = new ListNode(nums);
        System.out.println("原:     " + listNode1);

        ListNode listNode2 = new SolutionWithRecursiveCall203().removeElements(listNode1, 6);
        System.out.println("改变后: " + listNode2);
    }

}
