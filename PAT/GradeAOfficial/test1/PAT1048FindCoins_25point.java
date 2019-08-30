package PAT.GradeAOfficial.test1;

import java.util.Map;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定N个数字,求N个数字中有数字a,b使得 a+b = K;
 *      如果有多对(a,b),输出a最小的那一对
 * 思路:
 *      1. map.首先用map保存所有数,然后遍历一遍,
 *      当遍历到a时,判断是否存在map[K-a],如果存在,
 *      那么更新最小的(a,b)
 */
public class PAT1048FindCoins_25point {

    public static void main(String[] args){
        PAT1048FindCoins_25point pat1048 = new PAT1048FindCoins_25point();
        pat1048.Input();
    }

    // 最小a,b
    int minA = Integer.MAX_VALUE,minB = -1;

    int N,M;

    int[] nums = new int[1000];
    int[] array;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();

        array = new int[N];

        for(int i=0;i<N;i++){
            array[i] = in.nextInt();
            nums[array[i]]++;
        }

        Slove();
    }

    public void Slove(){
        for(int a:array){
            int b = M-a;
            if(b<0) continue;
            if (a != b) {
                if(nums[b]>0){
                    if(a<minA){
                        minA = a;
                        minB = b;
                    }
                }
            }else {
                if(nums[b]>1){
                    if(a<minA){
                        minA = a;
                        minB = b;
                    }
                }
            }
        }

        if(minB!=-1)
            System.out.println(minA+" "+minB);
        else
            System.out.println("No Solution");
    }
}
