package PAT.GradeAOfficial.test1;

import java.util.Scanner;

/**
 * 题意:
 *      1. 计算s1在去掉s2所有字符时的答案
 * 思路:
 *      1. replace
 */
public class PAT1050StringSubtraction_20point {

    public static void main(String[] args){
        PAT1050StringSubtraction_20point pat1050 = new PAT1050StringSubtraction_20point();
        pat1050.Input();
    }

    String s1,s2;
    public void Input(){
        Scanner in = new Scanner(System.in);
        s1 = in.nextLine();
        s2 = in.nextLine();

        Slove();
    }

    public void Slove(){
        String result = s1.replaceAll("["+s2+"]","");
        System.out.println(result);
    }
}
