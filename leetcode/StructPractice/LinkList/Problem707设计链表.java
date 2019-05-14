package leetcode.StructPractice.LinkList;

public class Problem707设计链表 {

    public static void main(String[] args){
        Problem707设计链表 linkedList = new Problem707设计链表();
        linkedList.MyLinkedList();
        linkedList.get(0);
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);
        linkedList.get(-1);

    }

    class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }

    // 链表头指针
    Node head;
    // 链表长度
    int size = 0;

    /** Initialize your data structure here. */
    public void MyLinkedList() {
        // 创建无意义的头指针
        head = new Node(0);
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index>=size || index<0) return -1;
        Node root = head.next;
        for(int i=0;i<index;i++){
            root = root.next;
        }
        return root.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node headNext = head.next;
        Node node = new Node(val);
        head.next = node;
        node.next = headNext;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node tailNode = head;
        Node preTailNode = head;
        while (tailNode!=null){
            preTailNode = tailNode;
            tailNode = tailNode.next;
        }

        preTailNode.next = new Node(val);
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index>size || index<0) return;
        else if(index==size) addAtTail(val);
        else if(index==0) addAtHead(val);
        else {
            // 可以将无意义的头指针看成是-1下标
            Node root = head;
            for(int i=0;i<index;i++){
                root = root.next;
            }
            // 待插入点的下一个节点元素
            Node nextNode = root.next;
            Node node = new Node(val);
            root.next = node;
            node.next = nextNode;
            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index>=size || index<0) return;
        // 可以将无意义的头指针看成是-1下标
        Node root = head;
        for(int i=0;i<index;i++){
            root = root.next;
        }
        // 待删除节点的下一个节点元素
        root.next = root.next!=null ? root.next.next : null;
        size--;
    }
}
