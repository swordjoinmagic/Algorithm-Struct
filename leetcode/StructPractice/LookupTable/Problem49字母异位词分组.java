package leetcode.StructPractice.LookupTable;

import java.util.*;

/**
 * ○ 本题是第二次做
 *
 * × 在第二次做的时候,没有思路或思路不清晰
 *
 * 思路：
 *      1. 使用一个数组A[24]记录当前字符串每个字母出现次数，
 *      然后把这些变成一个“A0B1C2...Z0”这种字符串作为键值，
 *      扔进Map里面。
 */
public class Problem49字母异位词分组 {

    public static void main(String[] args){
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = new Problem49字母异位词分组().groupAnagrams(strs);
        System.out.println(result);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        return Slove(strs);
    }

    public List<List<String>> Slove(String[] strs){
        Map<String,List<String>> map = new HashMap<>();

        int[] A = new int[26];

        for(String str:strs){

            Arrays.fill(A,0);

            for(int i=0;i<str.length();i++){
                A[str.charAt(i)-'a'] += 1;
            }

            StringBuilder key = new StringBuilder();
            for(int i=0;i<26;i++){
                key.append((char) (i + 'a')).append(A[i]);
            }
            if(!map.containsKey(key.toString())){
                map.put(key.toString(),new ArrayList<>());
            }
            map.get(key.toString()).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
