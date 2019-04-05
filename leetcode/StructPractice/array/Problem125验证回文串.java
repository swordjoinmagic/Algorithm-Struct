package leetcode.StructPractice.array;

/**
 * ○ 本题是第二次做
 *
 * 思路：
 *      1. 对撞指针法（即双指针）。
 *      一个left指针指向开头，一个right指针指向结尾。
 *      检验回文串的步骤如下：
 *
 *      a. left持续向右移，直到遇到第一个字母或数字字符。
 *      b. right持续向左移，直到遇到第一个字母或数字字符
 *      c. 判断A[left]==A[right]，相等则重复a,b,c，不相等这不是回文串
 *      d. 当left==right时，该字符串是回文串
 *
 */
public class Problem125验证回文串 {
    public boolean isPalindrome(String s) {

        s = s.toLowerCase();

        int left = 0;
        int right = s.length()-1;

        while (left<right){
            while (left<right && !isNumOrAlphet(s.charAt(left))) left++;
            while (left<right && !isNumOrAlphet(s.charAt(right))) right--;
            if(left<right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                } else {
                    left++;
                    right--;
                }
            }
        }
        return true;
    }

    private boolean isNumOrAlphet(char ch){
        if(ch<='9' && ch>='0') return true;
        if(ch<='z' && ch>='a') return true;
        if(ch<='Z' && ch>='A') return true;
        return false;
    }
}
