package AutumnRecruitment2019.G_Bit;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class GBITEarly2018_2 {

    public static void main(String[] args){
        GBITEarly2018_2 gbitEarly2018_2 = new GBITEarly2018_2();
        gbitEarly2018_2.Input();
    }

    TreeSet<Integer> set = new TreeSet<>();

    int N;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        for(int i=0;i<N;i++){
            int num = in.nextInt();
            set.add(num);
        }

        Slove();
    }

    public void Slove(){
        if(set.size()<2){
            System.out.println(0);
            return;
        }
        int maxValue = Integer.MIN_VALUE;
        int lastNum = 0;
        for(int num : set){
            maxValue = Math.max(maxValue,num-lastNum);
            lastNum = num;
        }

        System.out.println(maxValue);
    }
}
