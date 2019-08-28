package PAT.GradeAOfficial.test1;

import java.util.Scanner;

/**
 * 题意:
 *      1. 求最长回文子串
 *
 * 思路:
 *      设P(i,j)表示子串i~j位置是否是回文子串,是则为true,不是则为false
 *      P(i,j) = P(i+1,j-1) and Si==Sj
 *
 *      P(i,i) = true
 *      P(i,i+1) = (Si==Si+1)
 */
public class PAT1040LongestSymmetricString_25point {

    public static void main(String[] args){
        PAT1040LongestSymmetricString_25point pat1040 = new PAT1040LongestSymmetricString_25point();
        pat1040.Input();
    }

    String str;

    int maxLength = 0;

    public void Input(){
        Scanner in = new Scanner(System.in);
        str = in.nextLine();

        Slove();
    }

    public void Slove(){
        int N = str.length();
        boolean[][] f = new boolean[N][N];
        // 初始化
        for(int i=0;i<N;i++) f[i][i] = true;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++)
                if(j - i == 1){
                    f[i][j] = str.charAt(i)==str.charAt(j);

                    if(f[i][j] && j-i>maxLength){
                        maxLength = j-i;
                    }
                }
        }

        for(int i=N-1;i>=0;i--){
            for(int j=i+1;j<N;j++){
                if(str.charAt(i)==str.charAt(j) && f[i+1][j-1]){
                    // 此子串是回文串
                    f[i][j] = true;
                    if(j-i>maxLength){
                        maxLength = j-i;
                    }
                }
            }
        }

        System.out.println(maxLength+1);
    }
}
