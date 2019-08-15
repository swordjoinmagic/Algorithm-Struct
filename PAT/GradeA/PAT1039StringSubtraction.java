package PAT.GradeA;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 题意:
 *      1. 给定两个字符串S1,S2,求S1-S2的结果
 *      这里字符串减法被定义为 S1 中所有
 *      包含在S2中的字符都会消失
 */
public class PAT1039StringSubtraction {


    public static void main(String[] args){
        PAT1039StringSubtraction pat1039 = new PAT1039StringSubtraction();
        pat1039.Input();
    }

    String s1,s2;

    public void Input(){
        Scanner in = new Scanner(System.in);
        s1 = in.nextLine();
        s2 = in.nextLine();

        Slove();
    }

    public void Slove(){
        boolean[] mark = new boolean[257];
        for(int i=0;i<s2.length();i++) mark[s2.charAt(i)] = true;
        for(int i=0;i<s1.length();i++)
            if(!mark[s1.charAt(i)])
                System.out.print(s1.charAt(i));

        System.out.println();
    }
}
