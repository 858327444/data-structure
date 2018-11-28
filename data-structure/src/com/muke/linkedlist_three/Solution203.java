package com.muke.linkedlist_three;


/**
 * 不使用虚拟头节点  leetcode中203号问题
 * 删除链表中等于给定值 val 的所有节点。
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
public class Solution203 {
    public ListNode removeElements(ListNode head, int val) {
        // 1.如果开头元素依然是要删除的元素
        while (head != null && head.val == val) {
//            ListNode delNode = head;
//            head = head.next;
//            delNode.next = null;
            head = head.next; // 上面三行代码合一
        }
        if (head == null) {
            return null;
        }
        // 2.如果中间有待删除的元素,进行以下逻辑
        ListNode prev = head;
        // 没有遍历到最后一个节点
        while (prev.next != null) {
            if (prev.next.val == val) {
//                ListNode delNode = prev.next;
//                prev.next = delNode.next;
//                delNode.next = null;
                prev.next = prev.next.next;// 上面三行代码合一
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 6, 4, 5, 6};

        ListNode listNode1 = new ListNode(nums);
        System.out.println("原:     " + listNode1);

        ListNode listNode2 = new Solution203().removeElements(listNode1, 6);
        System.out.println("改变后: " + listNode2);
    }
}
