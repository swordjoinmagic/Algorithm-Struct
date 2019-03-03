package review.Tree;

/**
 * 二叉树的节点
 */
public class BinaryNode<T> {
    public BinaryNode<T> left,right;
    public BinaryNode<T> parent;
    public T data;  // 该节点存的值
    public BinaryNode(T data){
        this.data = data;
    }
    public BinaryNode(T data,BinaryNode parent){
        this.data = data;
        this.parent = parent;
    }

    /**
     * 中序遍历此二叉树
     */
    public static void MiddlePrint(BinaryNode node){
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
    public static void FrontPrint(BinaryNode node){
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
