package leetcode.IntermediateAlgorithm.array;

import java.util.*;

/**
 * 思路：
 *      [思路1错误,因为ascii码总和相同不代表字符相同,举例:ad和bc]
 *      1. 将字母的ascii码相加，ascii码相等的字符串就是字母异位词
 *      2. 将一个字符串定义为如下格式：
 *          "a的出现次数"+"b的出现次数"+...+"z的出现次数"
 *         用一个map，以上面的字符串为键，该组字母异位词在数组的下标为值
 */
public class Problem49字母异位词分组 {

    public static void main(String[] args){
        String[] array = new String[]{"cab","tin","pew","duh","may","ill","buy","bar","max","doc"};
        Problem49字母异位词分组 problem49 = new Problem49字母异位词分组();
        List<List<String>> result = problem49.Slove(array);
        for(List<String> l : result){
            for(String str : l){
                System.out.print(str+" ");
            }
            System.out.println();
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        // 键是字母异位词的ascii码,值是同一组字母异位词所在数组下标
        Map<Integer,Integer> map = new HashMap<>();

        int num = 0;
        List<List<String>> result = new ArrayList<>();

        for(String str : strs){
            int asciiSum = 0;
            for(int i=0;i<str.length();i++){
                char ch = str.charAt(i);
                asciiSum += ch/10;
                asciiSum += ch%10;
                asciiSum += ch;
            }
            if(!map.containsKey(asciiSum)){
                map.put(asciiSum,num++);
            }

            // 获得当前同组字母异位词所在数组下标
            int index = map.get(asciiSum);

            if(result.size()<=index)
                result.add(new ArrayList<>());

            result.get(index).add(str);

        }
        return result;
    }

    public List<List<String>> Slove(String[] strs){
        // 下标
        int num = 0;

        Map<String,Integer> map = new HashMap<>();
        List<List<String>> rs = new ArrayList<>();

        for(String str : strs){

            // 临时记录字母出现次数的数组
            int[] sum = new int[26];

            for(int i=0;i<str.length();i++){
                char ch = str.charAt(i);

                sum[ch-'a'] += 1;
            }

            StringBuilder result = new StringBuilder();
            for(int data : sum) result.append(data);

            if(!map.containsKey(result.toString())){
                map.put(result.toString(),num++);
            }

            int index = map.get(result.toString());
            if(rs.size()<=index) rs.add(new ArrayList<>());
            rs.get(index).add(str);
        }

        return rs;
    }
}
