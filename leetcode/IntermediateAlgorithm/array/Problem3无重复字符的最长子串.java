package leetcode.IntermediateAlgorithm.array;


import java.util.HashSet;
import java.util.Set;

/**
 * 思路:
 *      1. 暴力,遍历2次,时间复杂度O(N2)
 */
public class Problem3无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int result = 0;
        for(int i=0;i<len;i++){

            int num = 0;
            Set<Character> set = new HashSet<>();

            for(int j=i;j<len;j++){
                if(!set.contains(s.charAt(j))) {
                    num++;
                    set.add(s.charAt(j));
                }else
                    break;
            }

            if(num>result)
                result = num;
        }
        return result;
    }
}
