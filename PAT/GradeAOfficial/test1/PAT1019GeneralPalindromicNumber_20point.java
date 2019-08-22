package PAT.GradeAOfficial.test1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定一个数字N和它的进制表示b,
 *      问该数在该进制表示下是否是回文数
 *
 * 思路:
 *      1. 模拟.
 */
public class PAT1019GeneralPalindromicNumber_20point {

    public static void main(String[] args){
        PAT1019GeneralPalindromicNumber_20point pat1019 = new PAT1019GeneralPalindromicNumber_20point();
        pat1019.Input();
    }

    // 输入的正整数
    int N;

    // 进制
    int b;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        b = in.nextInt();

        Slove();
    }

    /**
     * 10进制转x进制的通用方法
     * @param N
     * @param b
     * @return
     */
    public List<String> decimalToXRadix(int N,int b){
        List<String> result = new LinkedList<>();
        int temp = N;
        while (temp>0){
            int m = temp%b;
            ((LinkedList<String>) result).addFirst(String.valueOf(m));
            temp /= b;
        }
        return result;
    }

    public void Slove(){
        List<String> strings = decimalToXRadix(N,b);
        if(isPalindromic(strings))
            System.out.println("Yes");
        else
            System.out.println("No");
        System.out.print(strings.get(0));
        for(int i=1;i<strings.size();i++){
            System.out.print(" "+strings.get(i));
        }
    }

    public boolean isPalindromic(List<String> strs){
        if(strs.size()==1 && strs.get(0).equals("0")) return true;
        for(int i=0;i<strs.size()/2;i++){
            if(!strs.get(i).equals(strs.get(strs.size()-i-1)))
                return false;
        }

        return true;
    }
}
