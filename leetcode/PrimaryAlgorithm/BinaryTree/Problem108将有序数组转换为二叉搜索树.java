package leetcode.PrimaryAlgorithm.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 思路1:
 *      写个AVL树,树的高度差不会超过1
 * 思路2:
 *      二叉平衡树的根节点是有序数组的中点,
 *      其右节点是前面一半有序数组的中点,
 *      左节点是后面一半有序数组的中点
 */
public class Problem108将有序数组转换为二叉搜索树 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args){
        Problem108将有序数组转换为二叉搜索树 problem108 = new Problem108将有序数组转换为二叉搜索树();
        problem108.slove();
    }

    public void slove(){
        int[] nums = {-10,-3,0,5,9};
        TreeNode root = sortedArrayToBST(nums);
        FrontPrint(root);
        System.out.println();
        MiddlePrint(root);
    }

    public void FrontPrint(TreeNode node){
        if(node!=null){
            System.out.print(node.val+" ");
            FrontPrint(node.left);
            FrontPrint(node.right);
        }
    }

    public void MiddlePrint(TreeNode node){
        if(node!=null){
            MiddlePrint(node.left);
            System.out.print(node.val+" ");
            MiddlePrint(node.right);
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int data:nums) list.add(data);
        return GetSortedArray(list);
    }

    public TreeNode GetSortedArray(List<Integer> nums){
        if(nums==null || nums.size()==0) return null;
        if(nums.size()==1) return new TreeNode(nums.get(0));
        TreeNode root;

        // 有序数组的中点
        int middleIndex = nums.size()/2;
        root = new TreeNode(nums.get(middleIndex));

        root.left = GetSortedArray(nums.subList(0,middleIndex));
        root.right = GetSortedArray(nums.subList(middleIndex+1,nums.size()));

        return root;
    }

    public TreeNode insertNode(TreeNode root,int data){
        if(root==null) return new TreeNode(data);

        // 第一步找到要插入的
        return null;
    }
}
