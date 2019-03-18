package leetcode.IntermediateAlgorithm.LinkedList;

import java.io.PrintStream;

/**
 * 题目要求时间复杂度为O（n），空间复杂度为O(1)
 *
 * 思路：
 *      1. 一个偶数链表的尾节点串上一个奇数链表
 */
public class Problem328奇偶链表 {

    public static void main(String[] args){
        Problem328奇偶链表 problem328 = new Problem328奇偶链表();
        ListNode node = new ListNode(2);
        node.next = new ListNode(1);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(5);
        node.next.next.next.next = new ListNode(6);
        node.next.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next.next = new ListNode(7);

        System.out.println();
        ListNode root = problem328.oddEvenList(node);
        problem328.print(root);
    }

    private void print(ListNode node){
        ListNode temp = node;
        while (temp!=null){
            System.out.print(temp.val+" ");
            temp = temp.next;
        }
        System.out.println();
    }

    public ListNode oddEvenList(ListNode head) {

        if(head==null) return null;

        // 奇数链表
        ListNode odd = head.next;
        // 偶数链表
        ListNode even = head;

        ListNode oddHead = odd;

        while (even.next!=null && odd.next!=null){
            even.next = odd.next;
            odd.next = odd.next.next;

            even = even.next;
            odd = odd.next;
        }

        // 偶数链表尾节点接上奇数链表头结点
        even.next = oddHead;

        return head;
    }
}

