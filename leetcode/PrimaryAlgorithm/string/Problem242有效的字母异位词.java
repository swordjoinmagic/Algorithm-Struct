package leetcode.PrimaryAlgorithm.string;

import java.util.Arrays;

public class Problem242有效的字母异位词 {

    public static void main(String[] args){
        Problem242有效的字母异位词 problem242 = new Problem242有效的字母异位词();
        boolean result = problem242.isAnagram("ab","a");
        System.out.print(result);
    }

    public boolean isAnagram(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();

        Arrays.sort(s1);
        Arrays.sort(s2);

        if(Compare(s1,s2)==0)
            return true;
        else
            return false;
    }

    private int Compare(char[] s1,char[] s2){
        if(s1.length<s2.length) return -1;
        if(s1.length>s2.length) return 1;
        for(int i=0;i<s1.length;i++){
            if(s1[i]>s2[i])
                return 1;
            else if(s1[i]<s2[i])
                return -1;
        }
        return 0;
    }
}
