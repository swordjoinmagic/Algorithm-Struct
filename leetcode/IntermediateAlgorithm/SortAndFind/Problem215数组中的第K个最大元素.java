package leetcode.IntermediateAlgorithm.SortAndFind;

import java.util.Arrays;

/**
 * 思路:
 *      1. 排序,最低复杂度O(nLogn)
 *      2. 类似快排的方法,每趟快排定义一个基准,将大于基准的放到
 *      基准右边,小于基准的放到基准左边,这时,判断基准所在下标
 *
 *      设x=(len-基准下标)(表示当前基准是第x个最大元素),
 *      如果 k > x,说明要找的元素比基准小,去左边找,如果k < x,
 *      说明要找的元素比基准大,去右边找.
 *      如果k == x,说明找到了这第K个最大元素
 */
public class Problem215数组中的第K个最大元素 {

    public static void main(String[] args){
        int[] array = new int[]{3,2,1,5,6,4};
        int k = 2;
        Problem215数组中的第K个最大元素 problem215 = new Problem215数组中的第K个最大元素();
        int result = problem215.findKthLargest(array,k);
        System.out.println(result);
//        problem215.Test(array);
    }

    private void Test(int[] array){
        OnceQuickSort(array,0,array.length-1);
        for(int i : array){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    /**
     *
     * 表示一趟快排,
     * 将比基准大的元素交换到基准右边,
     * 比基准小的元素交换到基准左边
     *
     * 返回值是基准当前所在下标
     *
     * @param array 要快排的数组
     * @param start 当前要快排的数组的开始位置
     * @param end   当前要快排的数组的结束位置
     */
    private int OnceQuickSort(int[] array,int start,int end){

        // 基准,取第一个元素作为基准
        int standard = array[start];

        int i = start;
        int j = end;
        while (i<j){

            // 找到第一个比基准小的元素
            while (i<j && array[j] >=standard)    j--;
            if(i<j)
                // 将这个比基准小的元素交换到左边去
                array[i++] = array[j];

            // 找到第一个比基准大的元素
            while (i<j && array[i]<=standard)  i++;
            if(i<j)
                // 将这个比基准大的元素交换到右边去
                array[j--] = array[i];

        }
        array[i] = standard;
        return i;
    }

    private void Swap(int[] array,int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int findKthLargest(int[] nums, int k) {
        int result = OnceQuickSort(nums,0,nums.length-1);
        while ((nums.length-result)!=k) {

            if ((nums.length - result) > k) {
                // 说明要找的元素比基准大,去右边找
                result = OnceQuickSort(nums, result + 1, nums.length - 1);
            }else if((nums.length - result) < k) {
                // 说明要找的元素比基准小,去左边找
                result = OnceQuickSort(nums, 0, result - 1);
            }
        }
        return nums[result];
    }
}
