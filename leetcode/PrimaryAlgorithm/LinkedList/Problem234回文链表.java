package leetcode.PrimaryAlgorithm.LinkedList;


/**
 * 问题要求: O(n)时间复杂,O(1)空间复杂
 *
 * 思路:
  *     先遍历一遍,得到链表长度,此时所用时间O(n)
  *     根据链表长度,将链表的前一半和后一半断开,在这里用了O(n/2)
  *     用两个指针反转后一半链表
  *     遍历n/2遍,判断回文串
  *
  *     总时间复杂度:
  *         O(n)+O(n/2)+O(n)+O(n/2) 约等于 O(n)
  *     总空间复杂度:
  *         反转时用到的两个指针 约等于 O(1)
 */
public class Problem234回文链表 {
    public static void main(String[] args){
        Problem234回文链表 problem234 = new Problem234回文链表();
        problem234.Slove();
    }

    public void Slove(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);

        System.out.println(isPalindrome(head));
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private int GetLinkListLength(ListNode head){
        int length = 0;

        ListNode node = head;

        while (node!=null){
            length ++;
            node = node.next;
        }

        return length;
    }

    ListNode font;     // 链表前半段
    ListNode back;      // 链表后半段

    /**
     *
     * 将链表分为两段
     *
     * @param head 链表头指针
     * @param length 链表长度
     */
    private void DiviedLinkList(ListNode head,int length){
        ListNode pre = head;
        ListNode cur = head;
        // 将链表掰开两段来
        for(int i=0;i<length/2;i++){
            pre = cur;
            cur = cur.next;
        }
        // 此时pre指针为链表前半段的尾指针
        pre.next = null;        // 切开前半段和后半段
        font = head;           // 此时前半段即为以head为头指针,以pre为尾指针的链表

        // 此时cur为后半段的头指针,但是要判断一下链表长度是否为奇数
        // 为奇数的情况,cur要后移一位
        if(length%2!=0) cur = cur.next; // cur后移一位

        // 此时链表后半段的头指针是cur
        back = cur;
    }

    /**
     * 翻转一个链表
     * @param head
     * @return
     */
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

    public boolean isPalindrome(ListNode head) {

        // 0个元素的情况
        if(head==null) return true;
        // 1个元素的情况
        if(head.next == null) return true;


        // 第一步,获得链表长度
        int length = GetLinkListLength(head);

        // 第二步,把链表掰开两段
        DiviedLinkList(head,length);

        // 第三步,翻转链表后半段
        back = reverseList(back);

        // 对掰开来的两个链表进行回文判断
        ListNode tempFont = font;
        ListNode tempBack = back;
        while (tempFont!=null){
            if(tempFont.val != tempBack.val)
                return false;
            tempFont = tempFont.next;
            tempBack = tempBack.next;
        }

        return true;
    }

    private void Print(ListNode node){
        while (node!=null){
            System.out.println(node.val);
            node = node.next;
        }
    }
}
