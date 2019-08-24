package PAT.GradeAOfficial.test1;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定一个数字N,和最大操作步骤K,
 *      求N对应的回文数,和得到该回文数需要的操作步数
 *
 *      如果在K步之后都得不到回文数,那么输出在K步时得到的数和K
 *
 * 思路:
 *      1. 模拟
 */
public class PAT1024PalindromicNumber_25point {

    public static void main(String[] args){
        PAT1024PalindromicNumber_25point pat1024 = new PAT1024PalindromicNumber_25point();
        pat1024.Input();
    }

    BigInteger N;
    int K;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = new BigInteger(in.next());
        K = in.nextInt();
        Slove();
    }

    public boolean isPalindromic(BigInteger n){
        String str = n.toString();
        for(int i=0;i<str.length()/2;i++){
            if(str.charAt(i)!=str.charAt(str.length()-1-i))
                return false;
        }
        return true;
    }

    public BigInteger getReversedN(BigInteger n){
        String value = n.toString();
        String reversedN = "";
        for(int i=value.length()-1;i>=0;i--)
            reversedN += value.charAt(i);
        return new BigInteger(reversedN);
    }

    public void Slove(){

        BigInteger temp = N;
        if(isPalindromic(N)){
            System.out.println(N);
            System.out.println(0);
            return;
        }

        for(int i=1;i<=K;i++){
            BigInteger reverseN = getReversedN(temp);
            temp = temp.add(reverseN);
            if(isPalindromic(temp)){
                System.out.println(temp);
                System.out.println(i);
                return;
            }
        }
        System.out.println(temp);
        System.out.println(K);
    }
}
