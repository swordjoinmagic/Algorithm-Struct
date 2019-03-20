package leetcode.IntermediateAlgorithm.TreeAndGraph;

/*
    思路：
        1. 利用两个栈
        在根节点的时候，将左节点先放入栈、右节点后放入
        在第二层节点时，将右节点放入栈、左节点后放入
        以此类推
 */

import java.util.*;

public class Problem103二叉树的锯齿形层次遍历 {

    public static void main(String[] args){
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);

        Problem103二叉树的锯齿形层次遍历 problem103 = new Problem103二叉树的锯齿形层次遍历();

        List<List<Integer>> lists = problem103.zigzagLevelOrder(node);

        System.out.println(lists);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if(root==null) return lists;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.add(root);
        boolean isLeftFirst = true;

        while (!stack1.isEmpty() || !stack2.isEmpty()){
            List<Integer> list = new ArrayList<>();
            if(!stack1.isEmpty()){
                int len = stack1.size();
                for(int i=0;i<len;i++){
                    TreeNode node = stack1.pop();
                    list.add(node.val);
                    if(isLeftFirst){
                        if(node.left!=null) stack2.add(node.left);
                        if(node.right!=null) stack2.add(node.right);
                    }else {
                        if(node.right!=null) stack2.add(node.right);
                        if(node.left!=null) stack2.add(node.left);
                    }
                }
            }else {
                int len = stack2.size();
                for(int i=0;i<len;i++){
                    TreeNode node = stack2.pop();
                    list.add(node.val);
                    if(isLeftFirst){
                        if(node.left!=null) stack1.add(node.left);
                        if(node.right!=null) stack1.add(node.right);
                    }else {
                        if(node.right!=null) stack1.add(node.right);
                        if(node.left!=null) stack1.add(node.left);
                    }
                }
            }
            lists.add(list);
            if(isLeftFirst)
                isLeftFirst = false;
            else
                isLeftFirst = true;
        }

        return lists;
    }
}
