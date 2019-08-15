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
 *      1. 先将已知的那个数转为10进制,
 *      在对另外一个数进行进制转换尝试,
 *      尝试将另外一个数转化为2-36进制,如果都不行,那么impossible,
 *      如果可以,那么输出进制
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
        BigInteger a = null,b = null;
        if(tag==1) a = new BigInteger(N1,radix);
        if(tag==2) {a = new BigInteger(N2,radix); N2 = N1;}

        for(int i=2;i<=36;i++){
            try {
                b = new BigInteger(N2,i);
                if(b .compareTo(a)==0 ){
                    System.out.println(i);
                    return;
                }
            }catch (NumberFormatException e){}
        }

        System.out.println("Impossible");
    }
}
