package leetcode.StructPractice.array;

/**
 * 思路：
 *      1. a/e/i/o/u是元音字母。使用对撞指针法进行反转。
 *      思路和验证回文串类似（题号125）。
 *      定义left指针指向表头，right指针指向表尾。
 *
 *      反转元音字母步骤如下：
 *
 *      a. left持续右移，直到遇到元音字母。
 *      b. right持续左移，直到遇到元音字母。
 *      c. swap(A[left],A[right])，重复a,b,c操作
 *      d. 当left==right时，反转完成
 *
 */
public class Problem345反转字符串中的元音字母 {

    public static void main(String[] args){
        String s = "leetcode";
        Problem345反转字符串中的元音字母 problem345 = new Problem345反转字符串中的元音字母();
        String result = problem345.reverseVowels(s);
        System.out.println(result);
    }

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length()-1;
        while (left<right){
            while (left<right && !Check(chars[left])) left++;
            while (left<right && !Check(chars[right])) right--;
            if(left<right){
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    private boolean Check(char ch){
        switch (ch){
            case 'a':
            case 'A':
            case 'e':
            case 'E':
            case 'i':
            case 'I':
            case 'o':
            case 'O':
            case 'u':
            case 'U':
                return true;
            default:
                return false;
        }
    }
}
