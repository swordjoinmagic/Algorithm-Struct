package leetcode.IntermediateAlgorithm.LinkedList;

import sun.rmi.log.LogInputStream;

import java.util.List;

public class Problem2两数相加 {

    public static void main(String[] args){
        Problem2两数相加 problem2两数相加 = new Problem2两数相加();
        ListNode root1 = new ListNode(5);
//        root1.next = new ListNode(9);
//        root1.next.next = new ListNode(9);

        ListNode root2 = new ListNode(5);
//        root2.next = new ListNode(9);
//        root2.next.next = new ListNode(9);
//        root2.next.next.next = new ListNode(9);
//        root2.next.next.next.next = new ListNode(9);
//        root2.next.next.next.next.next = new ListNode(9);

        ListNode node = problem2两数相加.addTwoNumbers(root1,root2);
        while (node!=null){
            System.out.print(node.val+"->");
            node = node.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode tempL1Node = l1;
        ListNode tempL2Node = l2;

        // 头结点，Dummy Node
        ListNode root = new ListNode(0);

        // 用来移动DummyNode的节点
        ListNode node = root;

        while (tempL1Node!=null && tempL2Node!=null){
            int total = tempL1Node.val + tempL2Node.val;
            if(total>=10){
                total %= 10;

                if(tempL1Node.next!=null) tempL1Node.next.val += 1;
                else if(tempL2Node.next != null) tempL2Node.next.val += 1;
                else tempL1Node.next = new ListNode(1);
            }

            node.next = new ListNode(total);
            node = node.next;

            tempL1Node = tempL1Node.next;
            tempL2Node = tempL2Node.next;
        }

        while (tempL1Node==null && tempL2Node!=null){
            if(tempL2Node.val>=10){
                tempL2Node.val %= 10;
                if(tempL2Node.next==null)
                    tempL2Node.next = new ListNode(1);
                else
                    tempL2Node.next.val += 1;
            }
            node.next = new ListNode(tempL2Node.val);
            tempL2Node = tempL2Node.next;
            node = node.next;
        }

        while (tempL1Node!=null){

            if(tempL1Node.val>=10){
                tempL1Node.val %= 10;
                if(tempL1Node.next==null)
                    tempL1Node.next = new ListNode(1);
                else
                    tempL1Node.next.val += 1;
            }

            node.next = new ListNode(tempL1Node.val);
            tempL1Node = tempL1Node.next;
            node = node.next;
        }

        if(root.next == null) return new ListNode(0);
        return root.next;
    }

}
