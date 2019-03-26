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
        return SearchPeekNumber(0,nums.length-1,nums);
    }

    /**
     * 查找从序列下标为start(包含)开始到end结束(包含)的峰值
     *
     * 找不到峰值的情况返回-1
     *
     * 返回值为峰值的下标
     * @param start
     * @param end
     * @param nums
     */
    public int SearchPeekNumber(int start,int end,int[] nums){

        if(start<0 || end>=nums.length || (start+end+1)/2>=nums.length) return 0;

        // 得到序列中值
        int middle = (start+end+1)/2;
        int middleValue = nums[middle];

        // 获得中值左边的值
        int leftValue;
        // 越界判断,越界时,默认为负无穷
        if(middle-1<start)
            leftValue = Integer.MIN_VALUE;
        else
            leftValue = nums[middle-1];

        // 获得中值右边的值
        int rightValue;
        // 越界判断,默认为负无穷
        if(middle+1>end)
            rightValue = Integer.MIN_VALUE;
        else
            rightValue = nums[middle+1];

        // 如果左值和右值均小于当前值,那么当前值是峰值
        if(leftValue<middleValue && rightValue<middleValue){
            return middle;
        }else if(leftValue<middleValue){
            // 如果左值小于中值,那么左值不可能是峰值,可跳过

            // 左边递归
            int nextLeftValue = SearchPeekNumber(0,middle-2,nums);
            // 右边递归
            int nextRightValue = SearchPeekNumber(middle+1,end,nums);

            return nextLeftValue>nextRightValue ? nextLeftValue : nextRightValue;
        }else if(rightValue<middleValue){
            // 如果右值小于中值,那么右值不可能是峰值,可跳过

            // 左边递归
            int nextLeftValue = SearchPeekNumber(0,middle-1,nums);
            // 右边递归
            int nextRightValue = SearchPeekNumber(middle+2,end,nums);

            return nextLeftValue>nextRightValue ? nextLeftValue : nextRightValue;
        }

        return 0;
    }
}
