package leetcode.IntermediateAlgorithm.array;

/**
 * 题目要求时间复杂度为O(N),空间复杂度为O(1)
 * 思路:
 *      1. 暴力,定义三个指针i,j,k,i从0开始,j从i+1开始,k从j+1开始,
 *      时间复杂度O(n3)
 *      2. DP,最长不降子序列,复杂度O(n2)~O(nLogN)
 *      3. 来自评论区的思路,用两个变量min,middle记录最小值和次小值
 *      当遍历到比次小值大的数时,可以直接返回true
 *
 */
public class Problem334递增的三元子序列 {

    public static void main(String[] args){
        int[] array = new int[]{5,4,3,2,1};
        Problem334递增的三元子序列 problem334 = new Problem334递增的三元子序列();
        boolean result = problem334.increasingTriplet(array);
        System.out.println(result);
    }

    public boolean increasingTriplet(int[] nums) {
        // 最小值和次小值
        int min = Integer.MAX_VALUE;
        int middle = Integer.MAX_VALUE;


        for(int i=0;i<nums.length;i++){
            if(min>=nums[i]){
                // 如果min大于等于当前值,那么将当前值赋值给min
                min = nums[i];
            }else if(min<nums[i] && middle>=nums[i]){
                // 如果min小于当前值且middle大于当前值
                middle = nums[i];
            }else if(nums[i]>middle){
                return true;
            }
        }

        return  false;
    }
}
