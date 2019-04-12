package leetcode.StructPractice.LookupTable;

/**
 *
 * Tips:
 *      s和t具有相同长度
 *
 * 思路：
 *      1. 模拟。因为两个字符串长度相等。
 *      所以在遍历一遍两个字符串时建立映射，映射关系为：
 *      s[i]->t[i]
 *      根据这个映射关系，替换s字符串所有字母，然后和t字符串比较，
 *      相等则同构，不相等则不同构。
 *      时间复杂度O(N)
 */
public class Porblem205同构字符串 {
    public boolean isIsomorphic(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = s.toCharArray();

        // 字母映射
        char[] map = new char[300];

        for(int i=0;i<sChars.length;i++){
            char sCh = sChars[i];
            char tCh = tChars[i];

            // 建立 sCh -> tCh 的映射关系
            map[sCh] = tCh;
        }

        // 遍历sChars，根据映射关系替换所有字母
        for(int i=0;i<sChars.length;i++){
            sChars[i] = map[sChars[i]];
        }
        
        String newS = new String(sChars);
        return newS.equals(t);
    }
}
