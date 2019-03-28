package leetcode.IntermediateAlgorithm.SortAndFind;


/**
 * 思路:
 *      1. 类似二分查找,从序列中间开始,
 *      判断左边是否小于中值,如果小于,那么其左边的值就不可能是峰值,
 *      查找的话可以跳过左边的值
 *      同理,如果右边小于中指,那么右边就不可能是中值,查找时可跳过
 *
 *      判断完当前序列中值后,判断序列右边的中值,序列左边的中值,以此类推
 */
public class Problem162寻找峰值 {

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3};
        Problem162寻找峰值 problem162 = new Problem162寻找峰值();
        int result = problem162.findPeakElement(nums);
        System.out.println(result);
    }

    public int findPeakElement(int[] nums) {
        if(nums.length==0) return 0;
        return SearchPeekNumber(nums);
    }

    /**
     * 二分法
     *
     * 如果 A[mid+1] > A[mid]说明峰值在右边
     * 否则峰值在左边
     *
     * 返回值为峰值的下标
     *
     * @param nums
     */
    public int SearchPeekNumber(int[] nums){
        int start = 0;
        int end = nums.length-1;
        while (start<=end){
            int mid = start + (end-start)/2;
            int midValue;
            if(mid<0 || mid>=nums.length) midValue = Integer.MIN_VALUE;
            else midValue = nums[mid];
            int rightValue;
            if(mid+1<0 || mid+1>=nums.length) rightValue = Integer.MIN_VALUE;
            else rightValue = nums[mid+1];
            if(midValue<rightValue){
                start = mid+1;
            }else {
                end = mid-1;
            }
        }
        return start;
    }
}
