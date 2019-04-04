package leetcode.StructPractice.array;

import java.util.HashSet;
import java.util.Set;

/**
 * ○ 本题是第二次做
 *
 * 思路：
 *      1. 思路类似移动零（题号283）和移除元素(题号27)
 *      用一个set记录所有出现过的元素。
 *      设置一个fast指针，每当遇到相同的元素时就跳过，
 *      设置另一个指针指向当前元素，当遇到没有出现过的元素时，
 *      就令 A[index++] = A[fast++]，表示这个位置可以放置这个未出现的元素
 *
 */
public class Problem26删除排序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        int fast = 0;
        int index = 0;
        Set<Integer>set = new HashSet<>();
        while (fast<nums.length){
            if(set.contains(nums[fast])){
                // 出现过的元素，跳过
                fast++;
            }else {
                // 没出现过的元素，令元素取代位置在index的元素
                set.add(nums[fast]);
                nums[index++] = nums[fast++];
            }
        }
        return index;
    }
}
