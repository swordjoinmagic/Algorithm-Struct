package leetcode.PrimaryAlgorithm.LinkedList;

import java.util.List;

public class Problem206反转链表 {

    public static void main(String[] args){
        new Problem206反转链表().Slove();
    }

    public void Slove(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode node = reverseList(head);

        while (node!=null){
            System.out.println(node.val);
            node = node.next;
        }

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = head;

        // 链表空的情况
        if(pre == null) return null;

        ListNode middle = head.next;

        // 链表长度为1的情况
        if(middle==null) return head;

        ListNode after = middle.next;

        // 链表长度为2的情况
        if(after==null){
            middle.next = pre;
            pre.next = null;
            return middle;
        }

        // 链表长度大于2的情况
        while (after!=null){

            middle.next = pre;
            if(pre==head) pre.next = null;

            ListNode temp = middle;

            pre = middle;
            // 三个指针向下移动
            middle = after.next;

            // after是结尾的情况
            if(middle==null){
                after.next = pre;
                return after;
            }

            pre = after;

            after = middle.next;

            // pre指针反转
            pre.next = temp;
        }

        middle.next = pre;

        return middle;
    }
}
