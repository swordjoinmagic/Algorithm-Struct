package PAT.GradeA;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 思路：
 *      1. 使用Long存储A、B、C，
 *      除了通常情况外（即A+B在整数范围内），特殊情况如下：
 *
 *      1. A<2^-62 && B<2^-62 ，则A+B < 2^63
 *      2. A>2^62 && B>2^62， 则A+B > 2^63
 */
public class PAT1023AaddBandC {

    public static void main(String[] args){
        PAT1023AaddBandC pat1023 = new PAT1023AaddBandC();
        pat1023.Input();
    }

    int N;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        for(int i=1;i<=N;i++){
            BigInteger A = new BigInteger(in.next());
            BigInteger B = new BigInteger(in.next());
            BigInteger C = new BigInteger(in.next());

            System.out.println("Case #"+i+": "+(A.add(B).compareTo(C) > 0));
        }
    }
}
