package leetcode.IntermediateAlgorithm.other;

import leetcode.IntermediateAlgorithm.DP.Problem62不同路径;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 思路：
 *      1. 贪心。使用数组A[i]（i取值为'A'~'Z'）来表示种类为i的任务
 *      的冷却时间，冷却时间为0的任务可以随意完成，每完成一个任务，
 *      将该任务冷却时间置为2，同时其他任务冷却时间-1
 */
public class Problem621任务调度器 {

    public static void main(String[] args){
        char[] chars = new char[]{'A','A','A','B','B','B','B','B'};
        Problem621任务调度器 problem621 = new Problem621任务调度器();
        int result = problem621.leastInterval(chars,2);
        System.out.println(result);
    }

    public int leastInterval(char[] tasks, int n) {
        int[] A = new int[27];
        List<Character> list = new ArrayList<>();
        for(char data : tasks) list.add(data);

        int time = 0;

        while (list.size()!=0){

            System.out.println(list);
            for(int i=0;i<=26;i++){System.out.print("i:"+A[i]+"; ");}
            System.out.println();
            System.out.println(time);

            for(int i=0;i<=26;i++){
                if(A[i]>0) A[i] -= 1;
            }

            for(int i=0;i<list.size();i++){
                char ch = list.get(i);
                if(A[ch-'A']==0){
                    A[ch-'A'] = n+1;
                    list.remove(i);
                    break;
                };
            }

            time += 1;
        }
        return time;
    }
}
