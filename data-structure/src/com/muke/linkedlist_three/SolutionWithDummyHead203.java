package com.muke.linkedlist_three;

/**
 * 使用虚拟头节点
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
public class SolutionWithDummyHead203 {
    public ListNode removeElements(ListNode head, int val) {
        // 参数随便写,反正也不用
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        // 没有遍历到最后一个节点
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 6, 4, 5, 6};

        ListNode listNode1 = new ListNode(nums);
        System.out.println("原:     " + listNode1);

        ListNode listNode2 = new SolutionWithDummyHead203().removeElements(listNode1, 6);
        System.out.println("改变后: " + listNode2);


    }
}
