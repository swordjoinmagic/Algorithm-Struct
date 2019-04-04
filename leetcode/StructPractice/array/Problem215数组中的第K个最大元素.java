package leetcode.StructPractice.array;
/**
 * ○ 本题是第二次做
 *
 * 思路：
 *      1. 快排思路。
 *      快排每次将一个数作为基准，将比它小的扔到它前面，比它大的扔到它后面，
 *      这样这个数的位置就确定了，
 *      每次比较这个数的位置P，这个P就是第K小数，显然Len-P就是第K大数，
 *      要找到的第K大数，每次将K与Len-P比较，
 *      当Len-P大时，说明当前基准更小，向*基准*(这很关键,不要想当然的往mid右边找)右边（更大的方向）找，左边的一半就可以抛弃了
 *      当Len-P小时，说明当前基准更大，向*基准*(不要想当然..)左边找（更小的方向）
 */
public class Problem215数组中的第K个最大元素 {

    public static void main(String[] args){
        int[] nums = new int[]{-1,2,0};
        Problem215数组中的第K个最大元素 problem215 = new Problem215数组中的第K个最大元素();
        int result = problem215.findKthLargest(nums,1);
        System.out.println(result);
    }

    /**
     * 快排中的一次遍历
     * (包含end)
     *
     * 返回基准下标
     * @param nums
     */
    public int quickSortOnce(int[] nums,int start,int end){
        // 基准
        int standard = nums[start];

        while (start<end){
            // 从后往前找,找到第一个比基准小的数
            while (start<end && nums[end]>standard) end--;
            if(start<end)
            nums[start++] = nums[end];

            // 从前往后找,找到第一个比基准大的数
            while (start<end && nums[start]<standard) start++;
            if(start<end)
            nums[end--] = nums[start];
        }
        // 放置基准
        nums[start] = standard;

        return start;
    }

    public int findKthLargest(int[] nums, int k) {

        int start = 0;
        int end = nums.length-1;

        int result = -1;

        while (result!=k){
            int minK = quickSortOnce(nums,start,end);

            // Len-K小数 = 第K大数
            result = nums.length-minK;
            if(result>k){
                // 当前基准比K要小
                start = minK+1;
            }else if(result<k){
                // 当前基准比K要大
                end = minK-1;
            }else {
                return nums[minK];
            }
        }
        return 0;
    }
}
