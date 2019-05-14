package PAT.GradeA;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述：
 *      1. 计算一个字符串中有多少个 "PAT" （不需要连续的,只需保证前后顺序就好）
 *
 * 思路:
 *      1. 从右往左,计算每个字符右边有多少个 "A" 和 "T",
 *         当每遇到一个"P"时,结合前面的所有 "AT" 计算,用当前
 *         这个"P"能组合出多少个 "PAT"
 *
 *         时间复杂度O(N)
 */
public class PAT1063CountPATs {

    public static void main(String[] args){
        PAT1063CountPATs pat1063 = new PAT1063CountPATs();
        pat1063.Input();
    }

    String str;

    final static int Mod = 1000000007;

    public void Input(){
        Scanner in = new Scanner(System.in);
        str = in.next();
        Slove();
    }

    public void Slove(){

        int result = 0;

        // Anumber[i]表示i右边有多少个A
        int[] Anumber = new int[str.length()+1];
        // Tnumber[i]表示i右边有多少个T
        int[] Tnumber = new int[str.length()+1];
        // ATnumber[i]表示i右边有多少个AT
        int[] ATnumber = new int[str.length()+1];

        // 从后往前
        for(int i=str.length()-1;i>=0;i--){
            char ch = str.charAt(i);
            switch (ch){
                case 'T':
                    Tnumber[i] = (Tnumber[i+1]+1) % Mod;

                    Anumber[i] = Anumber[i+1];
                    ATnumber[i] = ATnumber[i+1];
                    break;
                case 'A':
                    Anumber[i] = (Anumber[i+1]+1) % Mod;
                    Tnumber[i] = Tnumber[i+1];
                    ATnumber[i] += (Tnumber[i+1] + ATnumber[i+1])%Mod;
                    break;
                case 'P':
                    // 计算PAT字母数量
                    int atnumber = ATnumber[i+1];
                    result = (result+atnumber) % Mod;
                default:
                    Anumber[i] = Anumber[i+1];
                    Tnumber[i] = Tnumber[i+1];
                    ATnumber[i] = ATnumber[i+1];
            }
        }

        System.out.println(result);
    }
}
