package leetcode.PrimaryAlgorithm.LinkedList;

import leetcode.PrimaryAlgorithm.string.Problem125验证回文字符串;

public class Problem237删除链表中的节点 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args){
        Problem237删除链表中的节点 problem237 = new Problem237删除链表中的节点();

        problem237.Slove();
    }

    public void Slove(){
        ListNode node = new ListNode(4);
        node.next = new ListNode(5);
        node.next.next = new ListNode(1);
        node.next.next.next = new ListNode(9);
        deleteNode(node.next.next);

        while (node!=null){
            System.out.println(node.val);
            node = node.next;
        }

    }

    /**
     * 思路，将当前节点向后交换，一直到倒数第二个节点，
     * 令倒数第二个节点的Next为null，即变相删除了这个节点
     * @param node
     */
    public void deleteNode(ListNode node) {

        // node.next == null 表示此时node是倒数第一个节点，node.next.next==null表示此时node是倒数第二个节点
//        while (node.next.next != null){
//
//            // 获得下一个节点
//            ListNode nextNode = node.next;
//
//            // 交换两个节点
//            int temp = node.val;
//            node.val = nextNode.val;
//            nextNode.val = temp;
//
//            // node节点向后移动
//            node = node.next;
//        }
//
//        // 在做一次交换
//        //======================================
//        // 获得下一个节点
//        ListNode nextNode = node.next;
//
//        // 交换两个节点
//        int temp = node.val;
//        node.val = nextNode.val;
//        nextNode.val = temp;
//        //========================================
//
//        // 此时node是倒数第二个节点,直接置空，
//        // 丢掉被置换到最后去的待删除节点
//        node.next = null;
        Fast(node);
    }

    public void Fast(ListNode node){
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
