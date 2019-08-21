package PAT.GradeAOfficial.test1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定一幅图,求去掉其中一个节点后,
 *      还需要多少条边可以使这个图联通.
 * 思路:
 *      1. 本质上问题就是在一副图去掉一个节点后,
 *      求联通分量的数量-1.
 *      因为可以将联通分量看成一个大节点,这样需要的边数就是
 *      大节点的数量-1,因为只需要将这些大节点连接起来就行了
 */
public class PAT1013BattleOverCities_25point {

    public static void main(String[] args){
        PAT1013BattleOverCities_25point pat1013 = new PAT1013BattleOverCities_25point();
        pat1013.Input();
    }

    // N:节点数,M:边数,
    // K:有多少城市需要检查
    // (也就是去掉他之后需要多少边将图变为联通图)
    int N,M,K;

    // 邻接矩阵存图(无权无向图,有边为1,无边为0)
    int[][] graph;

    // 已经经过的节点,用来求联通分量
    boolean[] vistied;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        K = in.nextInt();

        // 初始化邻接矩阵和visited数组
        graph = new int[N+1][N+1];
        vistied = new boolean[N+1];

        for(int i=0;i<M;i++){
            int city1 = in.nextInt();
            int city2 = in.nextInt();
            graph[city1][city2] = 1;
            graph[city2][city1] = 1;
        }

        for(int i=0;i<K;i++){

            // reset
            Arrays.fill(vistied,false);

            // 本次要check的城市
            int checkCity = in.nextInt();
            // 将要check的城市设为true,表示该点已经访问过
            vistied[checkCity] = true;

            int result = Slove()-1;

            System.out.println(result);
        }
    }

    // 返回图的联通分量数量
    public int Slove(){

        // 联通分量数量
        int result = 0;

        // 遍历所有节点,用DFS找联通分量
        for(int i=1;i<=N;i++){
            // 如果当前节点没有访问过,那么进去DFS,
            // 将访问到的所有节点标记
            if(!vistied[i]){
                DFS(i);
                result++;
            }
        }

        return result;
    }

    public void DFS(int node){
        vistied[node] = true;
        for(int i=1;i<=N;i++){
            if(!vistied[i] && graph[node][i]==1){
                DFS(i);
            }
        }
    }
}
