package PAT.GradeAOfficial.test1;

import java.util.Scanner;

/**
 * 题意:
 *      1. 给定N个字符,将他们按U型打印.
 *
 *      其中左边一列的长度为n1,右边一列的长度为n3,
 *      底下一行的长度为n2.
 *
 *      要求 n1==n3 == max{ k| k<=n2 },即n1,n3是小于等于n2的最大数,
 *      有公式 n1+n2+n3-2 = N,根据n1==n3,可得
 *             2*n1+n2 = N+2
 *
 * 思路:
 *      1. 模拟.
 *      由公式 n1+n2+n3=N+2,可以得到n1 = n2 = (N+2) / 3
 */
public class PAT1031HelloWorldforU_20point {

    public static void main(String[] args){
        PAT1031HelloWorldforU_20point pat = new PAT1031HelloWorldforU_20point();
        pat.Input();
    }

    String str;
    int n1,n2;
    int N;
    public void Input(){
        Scanner in = new Scanner(System.in);
        str = in.next();
        N = str.length();

        n1 = (N+2)/3;
        n2 = N+2  - 2*n1;

        Slove();
    }

    public void Slove(){
        // 左侧列打印 str[0]~str[n1]的内容,长度为n1
        // 底行打印长度为n2-2,
        // 右侧列打印长度为n3

        String space = "";
        for(int i=0;i<n2-2;i++) space+=" ";

        // 先打印缺最后一行的左列和右列(长度为n1-1)
        for(int i=0;i<n1-1;i++){
            System.out.println(str.charAt(i)+space+str.charAt(str.length()-1-i));
        }
        // 打印最后一行
        for(int i=0;i<n2;i++){
            System.out.print(str.charAt(i+n1-1));
        }

    }
}
