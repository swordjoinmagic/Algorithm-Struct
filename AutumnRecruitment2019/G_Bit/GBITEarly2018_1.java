package AutumnRecruitment2019.G_Bit;

import java.util.Scanner;

/**
 * 思路:
 *      1. 埃式筛法找素数.
 */
public class GBITEarly2018_1 {

    public static void main(String[] args){
        GBITEarly2018_1 gbitEarly2018_1 = new GBITEarly2018_1();
        gbitEarly2018_1.Input();
    }

    int M,N;

    // 筛子,true表示筛去
    boolean[] u = new boolean[10000000];

    public void Input(){
        Scanner in = new Scanner(System.in);
        M = in.nextInt();
        N = in.nextInt();

        Slove();
    }

    public void Slove(){
        int count = 0;
        // 埃式筛法找素数,
        // 基本思路是,如果x是素数,那么x*2,x*3,x*4...,x*N都不是素数
        for(int i=2;i<=N;i++){
            if(!u[i]){
                // 表示i是素数,筛去它的倍数
                for(int j=2;i*j<=N;j++){
                    // 筛去
                    u[i*j] = true;
                }
                if(i>=M) count++;
            }
        }

        System.out.println(count);
    }
}
