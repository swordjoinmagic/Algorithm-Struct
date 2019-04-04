package leetcode.StructPractice.array;

/**
 * ○ 本题是第二次做
 *
 * 思路：
 *      1. 类似计数排序的方法。
 *      一个index下标记录当前待赋值项，
 *      一个下标fastIndex在遇到0时跳过，使A[index++]=非0项
 */
public class Problem283移动零 {

    public static void main(String[] args){
        Problem283移动零 problem283 = new Problem283移动零();
        int[] nums = new int[]{0,1,0,3,12};
        problem283.moveZeroes(nums);
        for(int data : nums){
            System.out.print(data+" ");
        }
    }

    public void moveZeroes(int[] nums) {
        int index = 0;
        int fastIndex = 0;
        while (fastIndex<nums.length){
            if(nums[fastIndex]==0) {
                fastIndex ++;
            }else{
                // 非0项
                nums[index++] = nums[fastIndex++];
            }
        }
        for(int i=index;i<nums.length;i++){
            nums[i] = 0;
        }
    }
}
