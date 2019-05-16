package leetcode.AdvancedAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 思路:
 *      1. 参考评论区大神.
 *      使用hash表存储 键(对应数组中的值) - 值(对应当前端点值对应连续区间长度)
 *      接下来遍历数组,遇到每个端点值,只需根据其左右端点来更新它的连续区间长度就好了
 */
public class Problem128最长连续序列 {

    public static void main(String[] args){
        Problem128最长连续序列 problem128 = new Problem128最长连续序列();
        int[] data = new int[]{100, 4, 200, 1, 3, 2};
        int length = problem128.longestConsecutive(data);
        System.out.println(length);
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();

        int max = 0;

        for(int num : nums){
            if(!map.containsKey(num)){
                // 左端点连续区间长度
                int left = map.getOrDefault(num-1,0);
                // 右端点连续区间长度
                int right = map.getOrDefault(num+1,0);

                int length = left+right+1;

                if(length>max) max = length;

                // 计算当前区间的连续长度
                map.put(num,length);
                // 更新左右端点连续区间长度
                map.put(num-left,map.get(num));
                map.put(num+right,map.get(num));
            }
        }

        return max;
    }
}
