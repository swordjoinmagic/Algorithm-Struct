package leetcode.PrimaryAlgorithm.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路1:
 *      对这棵二叉搜索树进行中序遍历,
 *      如果是有序的,那么是一棵有效的二叉搜索树
 *
 *      中序遍历一次O(n),判断是否有序O(N)
 *      最终时间复杂度O(n)+O(n)
 *
 * 思路2:
 *       对这棵二叉搜索树进行递归的判断,
 *       首先判断当前节点是否符合二叉搜索树的标准
 *       (即根节点的值大于左节点的值且小于右节点的值)
 *       再依次判断左子树和右子树是否符合二叉搜索树的标准
 *
 *       时间复杂度,相当于遍历一遍二叉搜索树,
 *       O(N)
 */
public class Problem98验证二叉搜索树 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isValidBST(TreeNode root) {
        MiddlePrint(root);
        for(int i=1;i<array.size();i++){
            if(array.get(i)<=array.get(i-1))
                return false;
        }
        return true;
    }

    int i=0;
    List<Integer>array = new ArrayList<>();
    // 中序遍历
    private void MiddlePrint(TreeNode root){
        if(root!=null){
            MiddlePrint(root.left);
            array.add(root.val);
            MiddlePrint(root.right);
        }
    }
}
