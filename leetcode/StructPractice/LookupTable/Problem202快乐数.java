package leetcode.StructPractice.LookupTable;

import java.util.HashSet;
import java.util.Set;

/**
 * ○ 本题是第二次做
 *
 * 思路：
 *      1. 模拟。
 *      特殊情况是无限循环但始终变不到1的情况，
 *      对于这种情况，对每一次出现的正整数加入Set中，
 *      当再次遇到该数，就表示这个数不是快乐数
 */
public class Problem202快乐数 {

    public static void main(String[] args){
        Problem202快乐数 problem202 = new Problem202快乐数();
        boolean result = problem202.isHappy(20);
        System.out.println(result);
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        int result = n;
        while (result!=1){
            String str = String.valueOf(result);
            result = 0;
            for(int i=0;i<str.length();i++){
                result += (str.charAt(i)-'0')*(str.charAt(i)-'0');
            }
            if(!set.contains(result))
                set.add(result);
            else
                return false;
        }
        return true;
    }
}
