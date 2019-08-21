package PAT.GradeAOfficial.test1;

import java.util.Scanner;

/**
 * 题意:
 *      1. 给定一个正整数N和它的进制表示D,
 *      求N的倒数是否是质数
 *
 * 思路:
 *      1. 用朴素法判断质数,第一步将正整数解析为进制表示的字符串,
 *      第二步翻转字符串,
 *      第三步用十进制解析字符串,判断翻转后的数是否是质数
 */
public class PAT1015ReversiblePrimes_20point {

    public static void main(String[] args){
        PAT1015ReversiblePrimes_20point pat1015 = new PAT1015ReversiblePrimes_20point();
        pat1015.Input();

    }

    // 整数
    int N;
    // 进制表示
    int D;

    public void Input(){
        Scanner in = new Scanner(System.in);

        while (true) {
            N = in.nextInt();
            if (N < 0) return;
            D = in.nextInt();

            Slove();
        }
    }

    // 朴素法判断质数
    public boolean isPrime(int n){
        if(n==2) return true;
        if(n==1) return false;
        int sqrtN = (int)Math.sqrt(n);
        for(int i=2;i<=sqrtN;i++){
            if(n%i==0) return false;
        }
        return true;
    }

    public void Slove(){
        // 第一步,将整数解析为进制表示字符串
        String nWithD = Integer.toString(N,D);
        // 第二步,翻转字符串
        char[] nWithDChars = nWithD.toCharArray();
        for(int i=0;i<nWithDChars.length/2;i++){
            char ch = nWithDChars[i];
            nWithDChars[i] = nWithDChars[nWithDChars.length-i-1];
            nWithDChars[nWithDChars.length-i-1] = ch;
        }
        // 第三步,将反转字符串解析为正整数
        int result = Integer.parseInt(String.valueOf(nWithDChars),D);

        System.out.println(isPrime(result) && isPrime(N)? "Yes" : "No");
    }
}
