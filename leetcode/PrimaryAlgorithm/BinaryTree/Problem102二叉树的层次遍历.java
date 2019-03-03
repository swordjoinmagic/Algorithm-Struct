package leetcode.PrimaryAlgorithm.BinaryTree;

import java.util.*;

public class Problem102二叉树的层次遍历 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> array = new ArrayList<>();

        if(root==null) return array;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()){

            int size = queue.size();

            List<Integer> tempArray = new ArrayList<>();

            for(int i=0;i<size;i++){

                TreeNode node = queue.poll();
                tempArray.add(node.val);

                if(node.left!=null)  queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }

            array.add(tempArray);
        }
        return array;
    }
}
