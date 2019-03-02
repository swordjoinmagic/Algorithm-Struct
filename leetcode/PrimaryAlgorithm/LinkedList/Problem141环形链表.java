package leetcode.PrimaryAlgorithm.LinkedList;

/**
 * 要求: O(1)的内存
 * 思路:
 *      使用快慢指针,当两指针相遇时,说明有环
 */
public class Problem141环形链表 {
    public static void main(String[] args){

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                // 快慢指针相遇,有环
                return true;
            }
        }
        return false;
    }
}
