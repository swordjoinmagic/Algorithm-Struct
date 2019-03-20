package leetcode.IntermediateAlgorithm.TreeAndGraph;

import java.util.Arrays;

/**
 * 思路:
 *      1. 常规数据结构题,根据二叉树的先序和中序构造树,
 *      再通过先序找到当前二叉树的根节点,以及左子树和右子树
 *      以此类推
 */
public class Problem105从前序与中序遍历序列构造二叉树 {

    public static void main(String[] args){
        int[] pre = new int[]{3,9,20,15,7};
        int[] middle = new int[]{9,3,15,20,7};

        Problem105从前序与中序遍历序列构造二叉树 problem105 = new Problem105从前序与中序遍历序列构造二叉树();
        TreeNode root = problem105.buildTree(pre, middle);
        problem105.backPrint(root);
    }

    // 后序遍历
    public void backPrint(TreeNode node){
        if(node!=null){
            backPrint(node.left);
            backPrint(node.right);
            System.out.print(node.val+" ");
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        // 边界判断
        if(preorder.length==1) return new TreeNode(preorder[0]);

        // 前序遍历的第一个元素是当前的根节点
        TreeNode node = new TreeNode(preorder[0]);

        // 在中序遍历中找根节点,在根节点左边的是左子树,右边是右子树
        int rootIndex = -1;
        for(int i=0;i<inorder.length;i++){
            if(inorder[i]==preorder[0]){
                rootIndex = i;
                break;
            }
        }

        // 左子树的先序遍历数组
        int[] leftTreePreOrder = Arrays.copyOfRange(preorder,1,rootIndex+1);
        // 左子树的中序遍历数组
        int[] leftTreeInOrder = Arrays.copyOfRange(inorder,0,rootIndex);
        // 右子树的先序遍历数组
        int[] rightTreePreOrder = Arrays.copyOfRange(preorder,rootIndex+1,preorder.length);
        // 右子树的中序遍历数组
        int[] rightTreeInOrder = Arrays.copyOfRange(inorder,rootIndex+1,inorder.length);

        node.left = buildTree(leftTreePreOrder,leftTreeInOrder);
        node.right = buildTree(rightTreePreOrder,rightTreeInOrder);

        return node;
    }
}
