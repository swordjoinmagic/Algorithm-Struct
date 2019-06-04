package PAT.GradeA;

import java.util.Scanner;

/**
 * 题意：
 *      1. 找出最长的回文子串的长度
 *
 * 思路：
 *      1. DP.
 *      设 f[i][j]表示(i,j)(i<j)处的子串是否是回文串，则状态转移方程如下：
 *
 *      f[i][j] = f[i+1][j-1] and str[i]==str[j]
 *
 *      特别的 f[i][i] = true
 *             f[i][i+1] = Si==Si+1
 */
public class PAT1051LongestSymmetricString {

    public static void main(String[] args){
        PAT1051LongestSymmetricString pat1051 = new PAT1051LongestSymmetricString();
        pat1051.Input();
    }

    String str;

    public void Input(){
        Scanner in = new Scanner(System.in);
        str = in.nextLine();
        Slove();
    }

    public void Slove(){

        int longest = 0;

        boolean[][] f = new boolean[str.length()+1][str.length()+1];

        // 初始化
        for(int i=0;i<str.length();i++){
            f[i][i] = true;
        }

        for(int i=0;i<str.length();i++){
            for(int j=0;j<str.length();j++){
                if(j-i==1)
                    f[i][j] = str.charAt(i) == str.charAt(j);
            }
        }

        for(int i=str.length()-1;i>=0;i--){
            for(int j=i+1;j<str.length();j++){
                if(f[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] && str.charAt(i) == str.charAt(j);
                    if (f[i][j]) {
                        longest = Math.max(j - i + 1, longest);
                    }
                }
            }
        }

        System.out.print(longest);
    }
}
