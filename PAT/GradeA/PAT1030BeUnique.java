package PAT.GradeA;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给出N个数,找出第一个没有重复出现的数
 *
 * 思路:
 *      1. map.
 */
public class PAT1030BeUnique {

    public static void main(String[] args){
        PAT1030BeUnique pat1030 = new PAT1030BeUnique();
        pat1030.Input();
    }

    int N;

    int[] array;

    Map<Integer,Integer> map = new HashMap<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        array = new int[N];
        for(int i=0;i<N;i++){
            int number = in.nextInt();
            array[i] = number;
            map.put(number,map.getOrDefault(number,0)+1);
        }

        Slove();
    }

    public void Slove(){
        for(int i=0;i<N;i++){
            if(map.get(array[i])<2){
                System.out.println(array[i]);
                return;
            }
        }
        System.out.println("None");
    }
}
