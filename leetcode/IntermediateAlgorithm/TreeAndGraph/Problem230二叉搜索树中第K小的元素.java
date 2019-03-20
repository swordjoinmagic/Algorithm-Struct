package leetcode.IntermediateAlgorithm.TreeAndGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路:
 *      1.二叉搜索树一直往左走是最小的,一直往右走是最大的
 */
public class Problem230二叉搜索树中第K小的元素 {

    private List<Integer> list = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        MiddlePrint(root);
        return list.get(k-1);
    }

    public void MiddlePrint(TreeNode node){
        if(node!=null){
            MiddlePrint(node.left);
            list.add(node.val);
            MiddlePrint(node.right);
        }
    }
}
