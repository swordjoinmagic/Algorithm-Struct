package leetcode.PrimaryAlgorithm.LinkedList;

public class Problem19删除链表的倒数第N个节点 {

    public static void main(String[] args){
        new Problem19删除链表的倒数第N个节点().Slove();
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public void Slove(){
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);

        ListNode head = removeNthFromEnd(node1,2);

        while (head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        ListNode preNode = head;

        while (node!=null){

            // 判断当前节点是不是倒数第n个节点
            if(isDeleteNode(node,n))
                break;

            // 节点向下移动
            preNode = node;
            node = node.next;

        }
        if(node == head) return node.next;
        preNode.next = node.next;

        return head;
    }

    // 判断当前节点是不是倒数第n个节点
    private boolean isDeleteNode(ListNode node,int n){
          for(int i=0;i<n-1;i++){
              if(node!=null)
                node = node.next;
              else
                  return false;
          }
        return node.next == null;
    }
}
