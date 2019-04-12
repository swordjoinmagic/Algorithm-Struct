package leetcode.StructPractice.LookupTable;

/**
 * ○ 本题是第二次做
 *
 * Tips:
 *     字符串只包含小写字母
 *
 * 思路：
 *      1. 小写字母做键，字母出现次数做值，映射。
 */
public class Problem242有效的字母异位词 {
    public boolean isAnagram(String s, String t) {
        int[] A = new int[27];
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            A[ch-'a'] += 1;
        }
        for(int i=0;i<t.length();i++){
            char ch = t.charAt(i);
            A[ch-'a'] -= 1;
        }
        for(int data : A)
            if(data!=0)
                return false;
        return true;
    }
}
