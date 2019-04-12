package leetcode.StructPractice.LookupTable;

import java.util.*;

/**
 * 思路：
 *
 *      1. 一个map，记录第一个数组的元素数量。
 *      然后遍历第二个数组，遇到之前出现过的元素
 *      则map[data] -= 1，并添加该元素进result数组
 *
 */
public class Problem350两个数组的交集II {

    public static void main(String[] args){
        Problem350两个数组的交集II problem350 = new Problem350两个数组的交集II();
        int[] nums1 = new int[]{3,2,2};
        int[] nums2 = new int[]{2,2};
        int[] nums3 = problem350.intersect(nums1,nums2);
        for(int data : nums3) System.out.println(data);
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer>map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for(int data : nums1){map.put(data,map.getOrDefault(data,0)+1);}
        for(int data : nums2){
            if(map.getOrDefault(data,0)!=0){
                result.add(data);
                map.put(data,map.get(data)-1);
            }
        }
        int[] r = new int[result.size()];
        int index = 0;
        for(int data : result) r[index++] = data;
        return r;
    }
}
