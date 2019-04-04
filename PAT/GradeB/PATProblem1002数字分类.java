package PAT.GradeB;

import java.util.Arrays;
import java.util.Scanner;

public class PATProblem1002数字分类 {
    public static void main(String[] args){
        PATProblem1002数字分类 problem1002 = new PATProblem1002数字分类();
        problem1002.Slove();
    }

    public void Slove(){
        int[] a = new int[6];
        Arrays.fill(a,-1);
        int N;
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        boolean isAdd = true;
        int a4Count = 0;
        float a4 = -1;

        for(int i=0;i<N;i++){
            int number = in.nextInt();

            if(number%5==0){
                // A1
                if(a[1]==-1 && number%2==0) a[1] = 0;   // 初始化A1
                if(number%2==0) a[1] += number;

            }else if(number%5==1){
                // A2

                // 初始化A2
                if(a[2]==-1) a[2] = 0;

                if(isAdd){ a[2] += number; isAdd=false;}
                else {a[2]-=number; isAdd = true;}

            }else if(number%5==2){
                // A3
                // 初始化A3
                if(a[3]==-1) a[3] = 0;

                a[3]++;
            }else if(number%5==3){
                // A4
                // 初始化A4
                if(a[4]==-1) a[4] = 0;

                a[4] += number;
                a4Count++;
            }else if(number%5==4){
                // A5
                // 初始化A5
                if(a[5]==-1) a[5] = 0;

                a[5] = Math.max(a[5],number);
            }

            if(a4Count!=0){
                a4 = (float) a[4]/a4Count;
            }
        }

        System.out.print(a[1]==-1?"N":a[1]);
        System.out.print(" ");
        System.out.print(a[2]==-1?"N":a[2]);
        System.out.print(" ");
        System.out.print(a[3]==-1?"N":a[3]);
        System.out.print(" ");
        System.out.print(a[4]==-1?"N":String.format("%.1f",a4));
        System.out.print(" ");
        System.out.print(a[5]==-1?"N":a[5]);
    }
}
