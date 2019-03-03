package leetcode.PrimaryAlgorithm.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路:
 *      先将二叉树翻转,
 *      比较原二叉树和翻转后的二叉树的中序遍历
 *      如果相等,那么镜像对称
 */
public class Problem101对称二叉树 {

    public static void main(String[] args){
        new Problem101对称二叉树().slove();
    }

    public void slove(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(2);
        System.out.println(isSymmetric(root));
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private List<Integer> src = new ArrayList<>();
    private List<Integer> dest = new ArrayList<>();
    private List<Integer> srcFront = new ArrayList<>();
    private List<Integer> destFront = new ArrayList<>();

    public boolean isSymmetric(TreeNode root) {
        MiddlePrint(root,src);
        FrontPrint(root,srcFront);
        reverse(root);
        MiddlePrint(root,dest);
        FrontPrint(root,destFront);

        if(src.size()!=dest.size()) return false;
        if(srcFront.size()!=destFront.size()) return false;

        for(int i=0;i<src.size();i++){
            if(!dest.get(i).equals(src.get(i)))
                return false;
            if(!destFront.get(i).equals(srcFront.get(i)))
                return false;
        }

        return true;
    }

    private void MiddlePrint(TreeNode node,List<Integer> list){
        if(node!=null){
            MiddlePrint(node.left,list);
            list.add(node.val);
            MiddlePrint(node.right,list);
        }
    }

    private void FrontPrint(TreeNode node,List<Integer> list){
        if(node!=null){
            list.add(node.val);
            FrontPrint(node.left,list);
            FrontPrint(node.right,list);
        }
    }

    // 翻转一棵二叉树
    private void reverse(TreeNode node){
        if(node!=null){
            // 左右节点交换
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // 进入左子树和右子树继续
            reverse(node.left);
            reverse(node.right);
        }
    }
}
