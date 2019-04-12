package leetcode.StructPractice.LookupTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 思路:
 *      1. 套路跟三数之和差不多
 *      先对数组进行排序,然后遍历a(即fix变量a),
 *      在这过程中注意不要fix的数.
 *      这样四数之和问题就转换成了三数之和问题,接下来三数和的方法做.
 *      (即fix变量b,找到c+d=target-a-b)
 *
 *      时间复杂度O(n3)
 */
public class Problem18四数之和 {

    public static void main(String[] args){
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        Problem18四数之和 problem18 = new Problem18四数之和();
        List<List<Integer>>result = problem18.fourSum(nums,0);
        System.out.println(result);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for(int i=0;i<nums.length;i++){
            // 不fix同样的数
            if(i>0 && nums[i]==nums[i-1]) continue;
            int a = nums[i];

            for(int j=i+1;j<nums.length;j++){
                // 不fix同样的数
                if(j>i+1 && nums[j]==nums[j-1]) continue;
                int b = nums[j];

                int T = target-a-b;

                // 对撞指针找到目标值
                int left = j+1; // 在b前面的区域找
                int right = nums.length-1;
                while (left<right){
                    if(nums[left]+nums[right]>T){
                        right--;
                    }else if(nums[left]+nums[right]<T){
                        left++;
                    }else {
                        List<Integer> list = new ArrayList<>();
                        list.add(a);
                        list.add(b);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);

                        // 去除重复
                        while (left<right && nums[left]==nums[left+1]) left++;
                        while (left<right && nums[right]==nums[right-1]) right--;
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
