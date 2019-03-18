package szptLesson.Recursion;

import java.util.Scanner;

public class soldierRiver过河卒 {

    int n,m;
    // 马的位置
    int x,y;

    public static void main(String[] args){
        soldierRiver过河卒 soldierRiver = new soldierRiver过河卒();
        soldierRiver.Input();
        long result = soldierRiver.Slove();
        System.out.println(result);
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        x = in.nextInt();
        y = in.nextInt();
    }

    public long Slove(){
        long[][] f = new long[n + 1][m + 1];
        f[0][0] = 1;
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                if(canMove(i,j)) {
                    if (i >= 1)
                        // 上面的路径条数
                        f[i][j] += f[i - 1][j];
                    if (j >= 1)
                        // 左边的路径条数
                        f[i][j] += f[i][j - 1];
                }
            }
        }

        return f[n][m];
    }

    /**
     * 是否能到达点(a,b)
     * @param a
     * @param b
     * @return
     */
    public boolean canMove(int a,int b){
        if(a==x && b==y) return false;

        // 左上上
        if(a==x-2 && b==y-1) return false;

        // 左左上
        if(a==x-1 && b==y-2) return false;

        // 左左下
        if(a==x+1 && b==y-2) return false;

        // 左下下
        if(a==x+2 && b==y-1) return false;

        // 右上上
        if(a==x-2 && b==y+1) return false;

        // 右右上
        if(a==x-1 && b==y+2) return false;

        // 右右下
        if(a==x+1 && b==y+2) return false;

        // 右下下
        if(a==x+2 && b==y+1) return false;

        return true;
    }
}
