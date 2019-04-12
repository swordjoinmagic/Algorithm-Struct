package leetcode.StructPractice.LookupTable;

import java.util.*;

/**
 * 思路：
 *      1. 用set或dict。时间复杂度O(N)
 */
public class Problem349两个数组的交集 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for(int data : nums1){
            set1.add(data);
        }

        Set<Integer> set2 = new HashSet<>();
        for(int data : nums2)
            set2.add(data);

        set1.retainAll(set2);

        return set1.stream().mapToInt(Integer::valueOf).toArray();
    }
}
