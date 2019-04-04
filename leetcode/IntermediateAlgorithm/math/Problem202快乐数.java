package leetcode.IntermediateAlgorithm.math;

import java.util.HashSet;
import java.util.Set;

/**
 * 思路:
 *      1. 模拟.难点在于如何判断一个数不是快乐数.
 *      这里我采用与整形最大值比较的方法,
 *      如果当前数大于整形最大值那么就停止循环
 */
public class Problem202快乐数 {

    public static void main(String[] args){
        Problem202快乐数 problem202 = new Problem202快乐数();
        boolean result = problem202.isHappy(11);
        System.out.println(result);
    }

    public boolean isHappy(int n) {
        int result = 0;
        Set<Integer> set = new HashSet<>();
        while (result>=0) {
            String str = String.valueOf(n);
            result = 0;
            for (int i = 0; i < str.length(); i++) {
                result += (str.charAt(i) - '0') * (str.charAt(i) - '0');
            }
            if (result == 1)
                return true;
            n = result;
            if(set.contains(result)) return false;
            else set.add(result);
        }
        return false;
    }

}
