package leetcode.IntermediateAlgorithm.TreeAndGraph;

/**
 * 题目要求,空间复杂度O(1)
 *
 * 思路:
 *      1. 宽搜,但是要使用到队列,保存每一行节点,空间复杂度O(N),
 *      所以不行
 *      2. 带有Parent参数的先序遍历,每次将左子节点指向右子节点
 *      右子节点指向parent->next->left or right
 */
public class Problem116填充每个节点的下一个右侧节点指针 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
        public Node(int val){
            this.val = val;
        }
    }

    public static void main(String[] args){
        new Problem116填充每个节点的下一个右侧节点指针().Slove();
    }

    public void Slove(){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4 );
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        connect(root);

        Print(root);
    }

    public void Print(Node node){
        if(node!=null){
            Node node1 = node;
            while (node1!=null){
                System.out.print(node1.val+" ");
                node1 = node1.next;
            }
            System.out.println();
            Print(node.left);
        }
    }

    /**
     * 带Parent参数的先序遍历
     * @param node
     * @param parent
     */
    public void PrePrint(Node node){
        if(node==null) return;

        // 左子节点指向右子节点
        if(node.left!=null)
            node.left.next = node.right;
        // 右子节点指向parent->next->left or right
        if(node.right!=null && node.next!=null){
            if(node.next.left!=null)
                node.right.next = node.next.left;
            else
                node.right.next = node.next.right;
        }

//        System.out.println("nowNode:"+node.val+" nowNext:"+(node.next==null ? null : node.next.val)+" left:"+(node.left==null? null : node.left.val)+" right:"+(node.right==null ? null : node.right.val));


        // 左子树
        if(node.left!=null)
            PrePrint(node.left);
        // 右子树
        if(node.right!=null)
            PrePrint(node.right);
    }

    public Node connect(Node root) {
        PrePrint(root);
        return root;
    }

    //
}
