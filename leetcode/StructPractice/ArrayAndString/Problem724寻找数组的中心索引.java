package leetcode.StructPractice.ArrayAndString;

/**
 * 思路：
 *      1. 先得到整个数组的总和RightSum。
 *      从0号元素开始，记录当前从左到右的和值LeftSum，
 *      每次向右移动一步，RightSum减去该元素，和值加上该元素，
 *      直到 LeftSum == RightSum 或者 到达最右边
 *
 *      时间复杂O(N)
 *
 */
public class Problem724寻找数组的中心索引 {

    public static void main(String[] args){
        Problem724寻找数组的中心索引 problem724 = new Problem724寻找数组的中心索引();
        int[] nums = {-1,-1,-1,0,1,1};
        int result = problem724.pivotIndex(nums);

        System.out.println(result);
    }

    public int pivotIndex(int[] nums) {
        if(nums.length == 0) return -1;
        else if(nums.length==1) return 0;
        else if(nums.length==2) return -1;

        int rightSum = 0;
        int leftSum = 0;
        for(int data : nums) rightSum += data;
        rightSum -= nums[0];

        if(rightSum==0) return 0;

        for(int i=1;i<nums.length;i++){
            leftSum += nums[i-1];
            rightSum -= nums[i];
            if(leftSum==rightSum)
                return i;
        }

        return -1;
    }
}
