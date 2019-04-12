package leetcode.StructPractice.array;

/**
 * 思路：
 *
 *      1. 两层遍历。时间复杂度O(n2)
 *      2. 双索引法。
 *      定义两个指针left，right，进行如下步骤：
 *
 *      a. right向右移，知道子数组和大于等于给定值或right到达数组末尾
 *      b. 更新最短连续子序列长度，left向右移一位，重复步骤a
 *
 *      时间复杂度O(N)
 */
public class Problem209长度最小的子数组 {

    public static void main(String[] args){
        Problem209长度最小的子数组 problem209 = new Problem209长度最小的子数组();
        int[] nums = new int[]{2,3,1,2,4,3};
        int s = 7;
        int result = problem209.minSubArrayLen(s,nums);
        System.out.println(result);
    }

    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length==0) return 0;
        int left = 0;
        int right = 1;

        int sum = nums[left];
        int minLength = nums.length+1;

        while (left<nums.length){
            for(;right<nums.length && sum<s;right++){
                sum += nums[right];
            }

            System.out.println("left:"+left+" right:"+right+" sum:"+sum);

            if(sum>=s){
                // 更新最短距离数组长度
                minLength = Math.min(minLength,right-left);

                // left右移
                sum -= nums[left];
                left++;
            }else
                break;
        }

        return minLength==nums.length+1 ? 0 : minLength;
    }
}
