package leetcode.AdvancedAlgorithm;

/**
 * 思路：
 *      1. 参考评论区大神，将数字放到对应下标处（利用交换）
 */
public class Problem41第一个缺失的正数 {

    public static void main(String[] args){
        Problem41第一个缺失的正数 problem41 = new Problem41第一个缺失的正数();
        int[] nums = new int[]{0,1,2};
        int result = problem41.firstMissingPositive(nums);
        System.out.println(result);
    }

    public int firstMissingPositive(int[] nums) {
        if(nums.length==0) return 1;
        // 将数组中对应值放到对应下标(这里是值-1)处,
        // 举个例子,值为1的元素要置换到下标0处
        // 换言之,(下标+1)就是这个下标所对应的值的数值
        for(int i=0;i<nums.length;i++){
            while (nums[i]>0 && nums[i]<=nums.length && nums[i]!=nums[nums[i]-1]){
                // 将当前值交换到对应下标处
                int data = nums[i];

                // nums[i] 和 nums[ data-1 ] 交换
                int temp = nums[i];
                nums[i] = nums[data-1];
                nums[data-1] = temp;

            }
        }

        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1){
                return i+1;
            }
        }
        return nums.length+1;
    }
}
