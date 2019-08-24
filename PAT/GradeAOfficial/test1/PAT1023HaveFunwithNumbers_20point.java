package PAT.GradeAOfficial.test1;

import PAT.GradeA.PAT1023AaddBandC;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定一个数N,求N*2的是不是数字N的一个排列组合
 *
 * 思路:
 *      1. 大数+Map
 */
public class PAT1023HaveFunwithNumbers_20point {

    public static void main(String[] args){
        PAT1023HaveFunwithNumbers_20point pat1023 = new PAT1023HaveFunwithNumbers_20point();
        pat1023.Input();
    }

    BigInteger N;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = new BigInteger(in.next());
        Slove();
    }

    public void Slove(){
        BigInteger doubleN = N.multiply(BigInteger.valueOf(2));

        // 记录N中数字出现的次数
        int[] count = new int[10];
        // 记录doubleN中数字出现的次数
        int[] countWithDoubleN = new int[10];

        String doubleNStr = doubleN.toString();
        String nStr = N.toString();
        for(int i=0;i<nStr.length();i++)
            count[nStr.charAt(i)-'0']++;
        for(int i=0;i<doubleNStr.length();i++)
            countWithDoubleN[doubleNStr.charAt(i)-'0']++;

        if(nStr.length()!=doubleNStr.length()){
            System.out.println("No");
        }else {
            boolean flag = true;
            for(int i=0;i<10;i++){
                if(count[i]!=countWithDoubleN[i]){
                    System.out.println("No");
                    flag = false;
                    break;
                }
            }
            if(flag)
            System.out.println("Yes");
        }

        System.out.println(doubleN.toString());
    }
}
