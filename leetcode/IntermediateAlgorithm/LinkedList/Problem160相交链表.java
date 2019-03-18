package leetcode.IntermediateAlgorithm.LinkedList;

/**
 * 思路:
 *      1. 节点值相同且链表后续长度相同的是交点,
 *      遍历N*M次,找到节点值相同的点,再判断他们的长度
 *      时间复杂度(N2 * X),X是判断长度耗时
 *      2.思路类似链表判环那一题，
 */
public class Problem160相交链表 {

    public static void main(String[] args){
        ListNode node1 = new ListNode(4);
        node1.next = new ListNode(1);
        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(0);
        node2.next.next = new ListNode(1);
        ListNode center = new ListNode(8);
        center.next = new ListNode(4);
        center.next.next = new ListNode(5);
        node1.next.next = center;
        node2.next.next.next = center;


//        Problem160相交链表 problem160 = new Problem160相交链表();
//        ListNode node = problem160.getIntersectionNode(node1,node2);
//        System.out.println(node.val);
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA==null || headB == null) return null;

        ListNode tempA = headA;
        ListNode tempB = headB;

        while (tempA!=tempB){
            tempA = tempA == null ?  headB : tempA.next;
            tempB = tempB == null ? headA : tempB.next;
        }

        return tempA;
    }
}
