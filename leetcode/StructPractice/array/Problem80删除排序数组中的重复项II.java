package leetcode.StructPractice.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路：
 *      1. 思路依旧与283、27、26类似。
 *      与他们不同的是，要用map维护每个元素最多出现两次这个条件
 */
public class Problem80删除排序数组中的重复项II {

    public int removeDuplicates(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();

        int fast = 0;
        int index = 0;

        while (fast<nums.length){
            if(map.getOrDefault(nums[fast],0)>=2){
                // 如果当前数出现次数大于两次，fast指针跳过该数
                fast++;
            }else{
                map.put(nums[fast],map.getOrDefault(nums[fast],0)+1);
                nums[index++] = nums[fast++];
            }
        }
        return index;
    }

}
