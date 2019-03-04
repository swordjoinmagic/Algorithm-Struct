package review.Tree;

/**
 * 二叉树的节点
 */
public class BinaryTreeNode<T> {
    public BinaryTreeNode<T> left,right;
    public BinaryTreeNode<T> parent;
    public T data;  // 该节点存的值
    public BinaryTreeNode(T data){
        this.data = data;
    }
    public BinaryTreeNode(T data, BinaryTreeNode parent){
        this.data = data;
        this.parent = parent;
    }

    /**
     * 中序遍历此二叉树
     */
    public static void MiddlePrint(BinaryTreeNode node){
        if(node!=null){
            // 左
            MiddlePrint(node.left);
            // 中
            System.out.print(node.data+" ");
            // 右
            MiddlePrint(node.right);
        }
    }

    /**
     * 先序遍历此二叉树
     */
    public static void FrontPrint(BinaryTreeNode node){
        if(node!=null){
            // 根
            System.out.print(node.data+" ");
            // 左
            FrontPrint(node.left);
            // 右
            FrontPrint(node.right);
        }
    }
}
