package AutumnRecruitment2019.G_Bit;

import java.util.Scanner;

/**
 * 思路：
 *      1. DFS深搜.
 *      难点在于,不同的格子可能会增加/消耗行动力.
 *      这里在DFS中设置一个参数extraPower,表示在行动过程中额外获得的行动力,
 *      只有当extraPower==0时,才会实际消耗行动力.
 *
 */
public class GBITA2018_2 {

    public static void main(String[] args){
        GBITA2018_2 gbita2018_2 = new GBITA2018_2();
        gbita2018_2.Input();
    }

    // M：行,N:列
    int M,N;

    int[][] array;
    boolean[][] visited;

    // 目前消耗的最下行动力
    int minPower = Integer.MAX_VALUE;

    public void Input(){
        Scanner in = new Scanner(System.in);
        M = in.nextInt();
        N = in.nextInt();

        array = new int[M][N];
        visited = new boolean[M][N];

        for(int i=0;i<M;i++)
            for(int j=0;j<N;j++)
                array[i][j] = in.nextInt();

        Slove();
    }

    public void Slove(){
        if(array[0][0]<0)
            DFS(0,0,0,-array[0][0]);
        else
            DFS(0,0,array[0][0],0);
        System.out.println(minPower+1);
    }

    public void Move(int x,int y,int extraPower,int nowConsumePower){
        // 判断格子是否需要消耗行动力
        if(array[x][y]<0){
            int needPower = -array[x][y];
            // 需要消耗行动力
            // 此时查看extraPower是否有剩余,有就用
            if(extraPower-needPower>=0)
                DFS(x,y,extraPower-needPower, nowConsumePower);
            else {
                // 不够用
                int consumePower = needPower - extraPower;
                extraPower = 0;
                DFS(x,y,extraPower,nowConsumePower+consumePower);
            }
        }else {
            // 该格子不需要消耗行动力
            DFS(x,y,extraPower+array[x][y],nowConsumePower);
        }
    }

    /**
     *
     * @param x
     * @param y
     * @param extraPower 额外获得的行动力
     * @param nowConsumePower 目前消耗的行动力
     */
    public void DFS(int x,int y,int extraPower,int nowConsumePower){

        visited[x][y] = true;

        if(x==M-1 && y==N-1){
            // 终点
            minPower = Math.min(nowConsumePower,minPower);
            return;
        }

        // 下
        if(x+1<M){
            Move(x+1,y,extraPower,nowConsumePower);
        }

        // 右
        if(y+1<N){
            Move(x,y+1,extraPower,nowConsumePower);
        }
    }
}
