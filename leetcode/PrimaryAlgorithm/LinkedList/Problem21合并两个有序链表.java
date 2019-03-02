package leetcode.PrimaryAlgorithm.LinkedList;


import java.time.temporal.ValueRange;

public class Problem21合并两个有序链表 {

    public static void main(String[] args){
        Problem21合并两个有序链表 problem21 = new Problem21合并两个有序链表();
        problem21.Slove();
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public void Slove(){

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(5);
        l2.next.next.next = new ListNode(6);

        ListNode node = mergeTwoLists(l1,l2);
        Print(node);
    }

    /*
        **自闭思路**，该思路失败

        合并两个有序链表为一个有序链表
        思路和数组类似,用四个指针
     */
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {

        // 头指针是否是l1
        boolean isL1 = true;

        ListNode cur1 = l1;
        ListNode cur2 = l2;

        if(l1==null)
            return l2;
        else if(l2==null)
            return l1;

        if(l1.val>l2.val)
            isL1 = false;

        ListNode next1 = l1.next;
        ListNode next2 = l2.next;

        while (cur1.next!=null && cur2.next!=null){

            // 比较当前指针两者的大小
            if(cur1.val>cur2.val){

                // cur2后面接cur1
                cur2.next = cur1;

                // 更新cur2/next2
                cur2 = next2;
                next2 = next2.next;
                cur1 = cur1.next;
                next1 = next1.next;
            }else {

                // cur1后面接cur2
                cur1.next = cur2;

                // 更新cur1/next1
                cur1 = next1;
                next1 = next1.next;
                cur2 = cur2.next;
                next2 = next2.next;
            }
        }

        // 从未结束的链表开始,
        // 找到第一个大于等于已结束链表尾指针的节点node1
        // 然后将该尾指针接到node1后面,同时将尾指针的next=node1.next
        if(next1==null && next2!=null){
            // 链表1已结束,链表2未结束

            ListNode pre = cur2;
            // 在未结束节点找到第一个大于等于目标值的节点
            while (cur2.val < cur1.val && cur2.next!=null){
                pre = cur2;
                cur2 = cur2.next;
            }

            // cur2.next后面接cu1,cur1.next后面接next2
//            if(cur2.next!=null) {
//                next2 = cur2.next;
//                cur2.next = cur1;
//                cur1.next = next2;
//            }else {
//                cur2.next = cur1;
//            }

            System.out.println("pre:"+pre.val);
            System.out.println("cur1.val:"+cur1.val);
            System.out.println("cur2:"+cur2.val);

            pre.next = cur1;
            cur1.next = cur2;

        }else if(next1!=null && next2==null){

            // 链表2已结束,链表1未结束

            // 在未结束节点找到第一个大于等于目标值的节点
            while (cur1.val < cur2.val && cur1.next!=null){
                cur1 = cur1.next;
            }

            // cur1.next后面接cu2,cur2.next后面接next1
            if(cur1.next!=null) {
                next1 = cur1.next;
                cur1.next = cur2;
                cur2.next = next1;
            }else {
                cur1.next = cur2;
            }
        }


        if(isL1) return l1; else return l2;

    }


    /*
        思路和归并排序类似，创建一个头指针head用于返回
     */
    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        ListNode head = new ListNode(0);

        ListNode tempHead = head;

        while (l1!=null && l2!=null){

            if(l1.val>l2.val){
                tempHead.next = l2;

                l2 = l2.next;
                tempHead = tempHead.next;
            }else {
                tempHead.next = l1;

                l1 = l1.next;
                tempHead = tempHead.next;
            }

        }

        if(l1==null && l2!=null){
            tempHead.next = l2;
        }else if(l1!=null){
            tempHead.next = l1;
        }

        return head.next;
    }

    private void Print(ListNode node){
        while (node!=null){
            System.out.println(node.val);
            node = node.next;
        }
    }

}
