package leetcode.PrimaryAlgorithm.string;

public class Problem28实现strStr {

    public static void main(String[] args){
        String s1 = "a";
        String s2 = "a";
        System.out.println(new Problem28实现strStr().strStr(s1, s2));
    }

    // 遍历一遍haystack，O（N）复杂
    public int strStr(String haystack, String needle) {
        int len = needle.length();
        if(haystack.length()==0 && len==0) return 0;
        for(int i=0;i<haystack.length();i++){
            if(i+len>haystack.length()) return -1;
            String s = haystack.substring(i,i+len);
            if(s.equals(needle)){
                return i;
            }
        }

        return -1;
    }
}
