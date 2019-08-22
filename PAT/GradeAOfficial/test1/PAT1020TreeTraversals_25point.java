package PAT.GradeAOfficial.test1;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 题意：
 *      1. 给定后序和中序,求二叉树层次遍历
 *
 * 思路:
 *      1. 二叉树套路题.
 *      在后序序列中找到树根,在中序序列中通过后序找到的根,
 *      确定这棵树的左右子树范围,再根据这个左右子树继续建立二叉树
 */
public class PAT1020TreeTraversals_25point {

    public static void main(String[] args){
        PAT1020TreeTraversals_25point pat1020 = new PAT1020TreeTraversals_25point();
        pat1020.Input();

    }

    int N;
    int[] PostOrder;
    int[] InOrder;
    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        PostOrder = new int[N];
        InOrder = new int[N];

        for(int i=0;i<N;i++){
            PostOrder[i] = in.nextInt();
        }
        for(int i=0;i<N;i++){
            InOrder[i] = in.nextInt();
        }

        Slove();
    }

    public void Slove(){
        TreeNode root = MakeTree(PostOrder,InOrder,0,N,0,N);
        LevelOrderTraversal(root);
    }

    class TreeNode{
        // 当前节点表示的值
        int num;
        // 左右子树的节点
        TreeNode left;
        TreeNode right;
        public TreeNode(int num){
            this.num = num;
        }
    }

    public TreeNode MakeTree(int[] postOrder,int[] inOrder,
                             // 该二叉树的后序数组的开始和结束下标,其中结束下标不包括
                             int postOrderStart,int postOrderEnd,
                             // 该二叉树的中序数组的开始和结束下标,其中结束下标不包括
                             int inOrderStart,int inOrderEnd){
        // 后序序列中最后一个数为树根
        int root = postOrder[postOrderEnd-1];
        TreeNode node = new TreeNode(root);

        // 根在中序序列中的下标
        int rootIndex = -1;
        // 在中序序列中找到当前二叉树的树根
        for(int i=inOrderStart;i<inOrderEnd;i++){
            if(inOrder[i]==root){
                rootIndex = i;
                break;
            }
        }

        if(rootIndex==-1) return null;

        // 右子树的中序序列
        int rightTreeInOrderStart = rootIndex+1; int rightTreeInOrderEnd = inOrderEnd;
        int rightTreeLength = rightTreeInOrderEnd - rightTreeInOrderStart;
        // 右子树的后序序列
        int rightTreePostOrderEnd = postOrderEnd-1; int rightTreePostOrderStart = rightTreePostOrderEnd-rightTreeLength;

        if(rightTreeInOrderEnd - rightTreeInOrderStart > 0 && rightTreePostOrderEnd - rightTreePostOrderStart> 0)
            node.right = MakeTree(postOrder,inOrder,rightTreePostOrderStart,rightTreePostOrderEnd,rightTreeInOrderStart,rightTreeInOrderEnd);

        // 左子树的长度
        int leftTreeLength = rootIndex - inOrderStart;
        // 左子树的中序
        int leftTreeInOrderStart = inOrderStart; int leftTreeInOrderEnd = rootIndex;
        // 左子树的后序
        int leftTreePostOrderStart = postOrderStart; int leftTreePostOrderEnd = postOrderStart+leftTreeLength;

        if(leftTreeInOrderEnd - leftTreeInOrderStart > 0 && leftTreePostOrderEnd - leftTreePostOrderStart > 0)
            node.left = MakeTree(postOrder,inOrder,leftTreePostOrderStart,leftTreePostOrderEnd,leftTreeInOrderStart,leftTreeInOrderEnd);

        return node;
    }

    public void  LevelOrderTraversal(TreeNode node){
        Queue<TreeNode> queue = new LinkedList<>();
        System.out.print(node.num);
        if(node.left!=null) queue.add(node.left);
        if(node.right!=null) queue.add(node.right);
        while (!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            System.out.print(" "+treeNode.num);
            if(treeNode.left!=null) queue.add(treeNode.left);
            if(treeNode.right!=null) queue.add(treeNode.right);
        }
    }
}
