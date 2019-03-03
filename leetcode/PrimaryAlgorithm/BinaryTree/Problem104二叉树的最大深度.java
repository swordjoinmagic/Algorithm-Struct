package leetcode.PrimaryAlgorithm.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 思路:
 *      用一个队列进行层次遍历,就能获得最大深度
 */
public class Problem104二叉树的最大深度 {

    public static void main(String[] args){

    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int maxDepth(TreeNode root) {
        if(root==null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int height = 0;

        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode treeNode = queue.poll();
                if(treeNode.left!=null) queue.add(treeNode.left);
                if(treeNode.right!=null) queue.add(treeNode.right);
            }
            height ++;
        }

        return height;
    }
}
