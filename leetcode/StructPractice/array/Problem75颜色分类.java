package leetcode.StructPractice.array;

import javax.rmi.PortableRemoteObject;

/**
 * ○ 本题是第二次做
 *
 * 思路：
 *      1. 稳定排序，时间复杂度O(nLongN)
 *      2. 计数排序。Hash表记录红白蓝三色球的数量，遍历一次后，
 *      根据数量设置数组，时间复杂度O(2N) ~= O(N)
 *      3. 双指针法。一个start指针指向开头，一个end指针指向表尾，
 *      一个iterator指针用于遍历，iterator遇到1时跳过，
 *      遇到0时，交换A[iterator++]和A[start++]两个元素
 *      遇到2时，交换A[iterator]和A[end--]两个元素
 *      这里要注意的是，在遇到2时，iterator指针不继续向下遍历是因为，
 *      可能出现下面这种情况：
 *
 *      0 2 1 2
 *
 *      此时遇到2如果要交换到表尾，换回来的数也是2，要再判断一次。
 *
 *      可能有点疑惑为什么0不用这样判断，因为iterator指针是从0开始遍历，
 *      所以0并不会产生这样的情况，考虑下面这种情况：
 *
 *      0 2 0 1
 *
 *      在到第2个0时，start已经为1，所以不需要特殊判断
 */
public class Problem75颜色分类 {

    public static void main(String[] args){
        int[] nums = new int[]{0,2,0,2};
        Problem75颜色分类 problem75 = new Problem75颜色分类();
        problem75.sortColors(nums);
        for(int data:nums) System.out.print(data+" ");
    }

    private void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        int iterator = 0;

        while (iterator<=end && start<end){
            if(nums[iterator]==0){
                swap(nums,start++,iterator);
            }else if(nums[iterator]==2){
                swap(nums,end--, iterator);
                continue;
            }
            iterator++;
        }
    }
}
