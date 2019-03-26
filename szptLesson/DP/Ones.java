package szptLesson.DP;

import java.util.Scanner;

public class Ones {

    public static void main(String[] args){
        Ones ones = new Ones();
        ones.Input();
        int result = ones.Slove();
        System.out.println(result);
    }

    int n;
    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
    }

    public int Slove(){
        // 设f[i]为组成数字i需要用到的最少的数字1
        int[] f = new int[n+1];

        // 初始化
        f[1] = 1;
        f[2] = 2;

        // 从3向上递推,每次算组成i所需的最少的1的个数
        for(int i=3;i<=n;i++){
            // 组成i所需的最少的1的个数
            int min = i;
            f[i] = i;
            for(int j=1;j<i;j++){
                // 加法
                min = Math.min(min,f[j]+f[(i-j)]);
                // 乘法
                if(i%j==0){
                    min = Math.min(min,f[j]+f[i/j]);
                }
            }
            f[i] = min;
        }

        return f[n];
    }
}
