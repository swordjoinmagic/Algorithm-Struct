package PAT.GradeAOfficial.test1;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 题意:
 *      1. 输入格式:
 *      N1 N2 tag radix,
 *      如果tag为1,那么radix是N1的进位表现形式,如果tag为2,那么radix为N2的进位表现形式,
 *      如果N1 = N2,那么求另外一个数的进位,
 *      如果N1 = N2答案不唯一,输出另一个数的进位的最小值,
 *      如果N1 != N2,输出Imposiple
 *
 * 思路:
 *      1. 先将已知的那个数转为10进制,那么问题就转化为已知一个整数x,和另外一个数y及它的进制b,
 *      求进制b等于多少时,x == y.
 *
 *      这样就将问题转化为了搜索问题
 *
 *      朴素法: 遍历i到Range[2,∞),如果x==y,那么b就等于i,否则Impossible
 *      二分法: 已知进制b的最小范围和最大范围[2,100000000],可以进行二分
 *
 */
public class PAT1010Radix_25point {

    public static void main(String[] args){
        PAT1010Radix_25point pat1010 = new PAT1010Radix_25point();
//        pat1010.Input();
        System.out.println(new BigInteger("s9jix",36));
    }

    String N1,N2;
    int tag;
    int radix;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N1 = in.next();
        N2 = in.next();
        tag = in.nextInt();
        radix = in.nextInt();

        Slove();
    }

    public void Slove(){

    }
}
