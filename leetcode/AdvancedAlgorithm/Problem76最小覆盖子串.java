package leetcode.AdvancedAlgorithm;

import java.util.*;

/**
 * 思路:
 *      1. 维护一个滑动窗口,滑动窗口内始终有T的所有字符,
 *      举例:
 *          S = "ADOBECODEBANC", T = "ABC"
 *
 *      a. 得到窗口 "ADOBEC" 包含T的所有字母,记录长度
 *      b. 滑动窗口向前移动一格,则窗口变为"DOBEC",
 *      将所有不含T的前缀都去掉,则窗口变为"BEC"
 *      c. 滑动窗口继续向前移动,直到包含T的所有字母,
 *      继续步骤ABC
 */
public class Problem76最小覆盖子串 {


    public static void main(String[] args){
        Problem76最小覆盖子串 problem76 = new Problem76最小覆盖子串();
        String s = "a";
        String t = "a";
        String result = problem76.minWindow(s,t);
        System.out.print(result);
    }

    Map<Character,Integer> map = new HashMap<>();
    Map<Character,Integer> windowsMap = new HashMap<>();

    String maxLengthStr;

    String tempStr;

    public String minWindow(String s, String t) {
        // 记录t中字母出现次数
        for(int i=0;i<t.length();i++){
            char ch = t.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        Slove(s,t);

        return maxLengthStr==null?"":maxLengthStr;
    }

    public void Slove(String s,String t){
        // 滑动窗口
        int l = 0,r=0;

        maxLengthStr = null;

        while (r<=s.length()){

            tempStr = s.substring(l,r);

            // 如果当前窗口已经符合规则，那么记录当前窗口
            if(Check()){
                // 当前窗口已经包含了模式子串中的所有字符，
                // 判断当前窗口是不是"最小覆盖"的
                if(maxLengthStr==null || (r-l)<maxLengthStr.length())
                    maxLengthStr = s.substring(l,r);

                // 去掉最左边的那个字符在map中的计数
                windowsMap.put(s.charAt(l),windowsMap.get(s.charAt(l))-1);
                // 滑动窗口右移一格
                l++;

                if(l>=r) return;

                // 将所有不包含在t中的字符从滑动窗口中去掉，持续右移
                // 即，滑动窗口最左边的字符出现次数大于其在模式子串中的出现次数
                while (windowsMap.get(s.charAt(l))>map.getOrDefault(s.charAt(l),0)){
                    // 去除最左边的字符在map中的计数，滑动窗口左指针右移
                    windowsMap.put(s.charAt(l),windowsMap.get(s.charAt(l))-1);
                    l++;
                }


            }else {
                if(r<s.length())
                // 如果当前窗口尚未符合规则，那么右指针向右移动，增大窗口大小
                windowsMap.put(s.charAt(r),windowsMap.getOrDefault(s.charAt(r),0)+1);
                r++;
            }

        }
    }

    private boolean Check(){
        if(windowsMap.size()<map.size()) return false;
        for(char key : map.keySet()){
            if(map.get(key) > windowsMap.getOrDefault(key, 0))
                return false;
        }
        return true;
    }
}
