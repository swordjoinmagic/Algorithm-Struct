package leetcode.PrimaryAlgorithm.string;

import java.util.HashSet;
import java.util.Set;

public class Problem387字符串中的第一个唯一字符 {

    public static void main(String[] args){
        String s = "lleettccoodde";
        Problem387字符串中的第一个唯一字符 problem387 = new Problem387字符串中的第一个唯一字符();
        int result = problem387.firstUniqChar(s);
        System.out.print(result);
    }

    public int firstUniqChar(String s) {
        int[] visited = new int[30];

        for(int i=0;i<s.length();i++){
            visited[s.charAt(i)-'a'] += 1;
        }

        for(int i=0;i<s.length();i++){
            if(visited[s.charAt(i)-'a']==1)
                return i;
        }

        return -1;
    }

}
