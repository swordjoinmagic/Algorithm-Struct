package leetcode.IntermediateAlgorithm.TreeAndGraph;

import java.util.*;

public class Problem94中序遍历二叉树 {

    public List<Integer> list;

    public List<Integer> inorderTraversal(TreeNode root) {
        list = new ArrayList<>();
        Slove(root);
        return list;
    }

    public void Slove(TreeNode root){
        if(root==null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node.left==node && node.right==node) list.add(node.val);
            else {
                if (node.right != null) stack.add(node.right);
                TreeNode centerNode = new TreeNode(node.val);
                centerNode.left = centerNode;
                centerNode.right = centerNode;
                stack.add(centerNode);
                if (node.left != null) stack.add(node.left);
            }
        }
    }
}
