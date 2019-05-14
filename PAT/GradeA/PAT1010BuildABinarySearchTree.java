package PAT.GradeA;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 题目描述：
 *      1. 给定一棵二叉搜索树的结构，和一系列的整数，
 *      有且仅有一种方案可以将这些整数填充进二叉搜索树，
 *      求该二叉树的层次遍历
 * 思路：
 *      1. 先根据给定的结构建树，最后根据树形结构，进行DFS，
 *      遍历整型序列，选定其中的array[i]作为根，每次填充判断
 *      是否填充完毕，填充完毕即为要找的二叉树
 *
 *      思路一错误
 *
 *      2. 参考题解.基于二叉搜索树中序遍历有序的原则,先将数组排序,
 *      并根据中序遍历建树
 */
public class PAT1010BuildABinarySearchTree {

    public static void main(String[] args){
        PAT1010BuildABinarySearchTree pat1010 = new PAT1010BuildABinarySearchTree();
        pat1010.Input();
    }

    class TreeNode{
        int leftChild,rightChild;
        int val;
    }

    // 使用数组建树
    TreeNode[] tree;

    // 二叉树节点数
    int n;

    // 各节点的数
    int[] array;

    // 标记
    boolean[] visited;

    boolean result;

    public void Input(){
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        tree = new TreeNode[n+1];
        array = new int[n];

        for(int i=0;i<n;i++){
            int left = in.nextInt();
            int right = in.nextInt();

            TreeNode node = new TreeNode();
            node.leftChild = left;
            node.rightChild = right;

            tree[i] = node;
        }

        for(int i=0;i<n;i++){
            array[i] = in.nextInt();
        }

        Slove();
    }

    public void Slove(){
        Arrays.sort(array);
        MiddlePrint(tree[0]);
        LevelPrint();
    }

    // 层次遍历二叉树
    public void LevelPrint(){
        Queue<TreeNode> queue = new LinkedList<>();
        // 加入根节点
        queue.add(tree[0]);

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();

            if(node.leftChild!=-1)
                queue.add(tree[node.leftChild]);
            if(node.rightChild!=-1)
                queue.add(tree[node.rightChild]);

            System.out.print(node.val+" ");
        }
    }

    int index = 0;

    // 中序遍历建树
    public void MiddlePrint(TreeNode node){
        if(node!=null){
            // 左子树
            if(node.leftChild!=-1)
                MiddlePrint(tree[node.leftChild]);
            // 根
            node.val = array[index++];
            // 右子树
            if(node.rightChild!=-1)
                MiddlePrint(tree[node.rightChild]);
        }
    }
}
