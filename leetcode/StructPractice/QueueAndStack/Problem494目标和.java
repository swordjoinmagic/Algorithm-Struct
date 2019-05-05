package leetcode.StructPractice.QueueAndStack;

/**
 * 思路：
 *      1. DFS深搜。
 *      对于数组中每一个数，只有两种状态，一个是加，一个是减，
 *      递归进行加减运算，每次得到目标数，结果+1
 *
 *      时间复杂度O（2的n次方）
 */
public class Problem494目标和 {

    public static void main(String[] args){
        Problem494目标和 problem494 = new Problem494目标和();
        int[] nums = new int[]{1,1,1,1,1};
        int S = 3;
        int result = problem494.findTargetSumWays(nums,S);
        System.out.print(result);
    }

    private int result = 0;

    public int findTargetSumWays(int[] nums, int S) {
        DFS(nums,0,0,S);
        return result;
    }

    /**
     *
     * @param nums
     * @param pos 当前要处理的数在数组中的位置
     * @param nowNum 当前进行计算得到的总和
     * @param target 目标值
     */
    public void DFS(int[] nums,int pos,int nowNum,int target){
        if(nowNum == target && pos==nums.length){
            result++;
            return;
        }

        // 越界判断
        if(pos >= nums.length)
            return;

        // 加法
        DFS(nums,pos+1,nowNum+nums[pos],target);
        // 减法
        DFS(nums,pos+1,nowNum-nums[pos],target);
    }
}
