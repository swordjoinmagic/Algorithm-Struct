package leetcode.StructPractice.array;

/**
 * 思路：
 *      1. 双指针法。类似二分的思路，
 *      一个start指针指向开始位置，一个end指针指向结束位置，
 *      当A[start]+A[end] > target时，end = mid-1;
 *      当A[start]+A[end] < target时，start = mid+1;
 *
 *      上面思路关于Mid的错了。这里不能直接跳过中间的数，
 *      而是：
 *      当A[start]+A[end] > target时，end--;
 *      当A[start]+A[end] < target时，start++;
 *
 *      为什么二分法可以直接跳过中间的数，而这里不行？
 *
 *      很显然，二分法每次将目标值与中间的数比较，
 *      1. nums[mid] > target 时 right = mid-1;
 *      2. nums[mid] < target 时，left = mid+1;
 *      这里的关键点是，每次目标值比较的对象是中间值，所以
 *      一旦发现不对可以直接跳过中点。
 *
 *      而这里不行，因为并没有跟中间对象比较，而是用
 *      A[start]+A[end]与target比较。这里无法确定
 *      A[start+1]+A[end]是否和target相等，所以也就不能直接跳过中间数
 */
public class Problem167两数之和II输入有序数组 {

    public static void main(String[] args){
        Problem167两数之和II输入有序数组 problem167 = new Problem167两数之和II输入有序数组();
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = problem167.twoSum(nums,target);
        for(int data:result) System.out.print(data+" ");
    }

    public int[] twoSum(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length-1;
        while (start<end){
            int result = numbers[start]+numbers[end];
            if(result>target)
                end--;
            else if(result<target)
                start++;
            else
                return new int[]{start+1,end+1};
        }
        return null;
    }
}
