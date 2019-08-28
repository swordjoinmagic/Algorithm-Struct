package PAT.GradeAOfficial.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定N个数字,输出第一个出现次数为1
 * 思路:
 *      1. map
 */
public class PAT1041BeUnique_20point {

    public static void main(String[] args){
        PAT1041BeUnique_20point pat1041 = new PAT1041BeUnique_20point();
        pat1041.Input();
    }

    int[] nums;
    int N;

    Map<Integer,Integer> map = new HashMap<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        nums = new int[N];
        for(int i=0;i<N;i++){
            nums[i] = in.nextInt();
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }

        Slove();
    }

    public void Slove(){
        for(int i=0;i<N;i++)
            if(map.get(nums[i])==1) {
                System.out.println(nums[i]);
                return;
            }

        System.out.println("None");
    }
}
