package PAT.GradeAOfficial.test1;


import java.util.Scanner;

/**
 * 题意:
 *      1. 给定三个数字rgb,求他们的13进制表示
 *
 * 思路:
 *      1. 模拟即可.
 *      使用java连模拟都不需要....直接转换
 */
public class PAT1027ColorsinMars_20point {
    public static void main(String[] args){
        PAT1027ColorsinMars_20point pat1027 = new PAT1027ColorsinMars_20point();
        pat1027.Input();
    }

    int r,g,b;
    public void Input(){
        Scanner in = new Scanner(System.in);
        r = in.nextInt();
        g = in.nextInt();
        b = in.nextInt();

        Slove();
    }

    public void Slove(){
        String rr = Integer.toString(r,13).toUpperCase();
        if(rr.length()==1) rr = "0"+rr;
        String gg = Integer.toString(g,13).toUpperCase();
        if(gg.length()==1) gg = "0"+gg;
        String bb = Integer.toString(b,13).toUpperCase();
        if(bb.length()==1) bb = "0"+bb;

        System.out.println("#"+rr+gg+bb);
    }
}
