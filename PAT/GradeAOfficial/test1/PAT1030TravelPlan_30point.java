package PAT.GradeAOfficial.test1;

import java.util.Scanner;

/**
 * 题意:
 *      1. 给定N个节点,M条边,每条边都有一定的距离与花费,求最短路径.
 *      如果有多条最短路径,找到最小花费那一条
 *      结果要输出路径,最短路长度,最小花费
 *
 * 思路:
 *      1. DFS
 *
 */
public class PAT1030TravelPlan_30point {

    public static void main(String[] args){
        PAT1030TravelPlan_30point pat1030 = new PAT1030TravelPlan_30point();
        pat1030.Input();
    }

    int N,M,S,D;

    int[][] graph;
    int[][] cost;

    boolean[] visited;
    int minDistance = Integer.MAX_VALUE;
    int minCost = Integer.MAX_VALUE;
    String minPath;

    // parent[x]表示x节点的上一个节点,路径开始点为-1
    int[] parent;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        S = in.nextInt();
        D = in.nextInt();

        graph = new int[N][N];
        cost = new int[N][N];
        visited = new boolean[N];
        parent = new int[N];
        parent[S] = -1;

        for(int i=0;i<M;i++){
            int c1 = in.nextInt();
            int c2 = in.nextInt();
            int d = in.nextInt();
            int c = in.nextInt();
            graph[c1][c2] = d;
            graph[c2][c1] = d;

            cost[c1][c2] = c;
            cost[c2][c1] = c;
        }

        Slove();
    }

    public String getPath(){
        int temp = D;
        String result = String.valueOf(temp);
        temp = parent[temp];
        while (temp!=-1){
            result = temp +" "+ result;
            temp = parent[temp];
        }
        return result;
    }

    /**
     *
     * @param city 目前城市
     * @param cosume 目前花费
     * @param distance 目前距离
     */
    public void DFS(int city,int cosume,int distance){
        // 到达终点的情况
        if(city==D){
            // 判断是否比之前的最小路更小
            if(distance<minDistance){
                // 比之前最小的最短路要小,更新最小花费和最短路
                minDistance = distance;
                minCost = cosume;
                minPath = getPath();
            }else if(distance==minDistance){
                // 是另外一条最短路,此时更新最小花费
                if(cosume<minCost) {
                    minCost = cosume;
                    minPath = getPath();
                }
            }
            return;
        }

        // 遍历所有能走的城市
        for(int i=0;i<N;i++){
            if(graph[city][i]!=0 && !visited[i]){
                visited[city] = true;
                parent[i] = city;
                DFS(i,cosume+cost[city][i],distance+graph[city][i]);
                visited[city] = false;
            }
        }
    }

    public void Slove(){
        visited[S] = true;
        DFS(S,0,0);

        System.out.println(minPath+" "+minDistance+" "+minCost);
    }
}
