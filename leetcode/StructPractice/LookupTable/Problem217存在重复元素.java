package leetcode.StructPractice.LookupTable;

import java.util.HashMap;
import java.util.Map;

/**
 * ○ 本题是第二次做
 *
 * 思路：
 *      1. 用map记录数出现次数。遍历一遍即可，时间复杂度O(N)
 */
public class Problem217存在重复元素 {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int data : nums){
            map.put(data,map.getOrDefault(data,0)+1);
        }

        for(int data : nums){
            if(map.get(data)>=2){
                return true;
            }
        }
        return false;
    }
}
