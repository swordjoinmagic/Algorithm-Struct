package PAT.GradeAOfficial.test1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 题意:
 *      1. 给定一幅图和一个起点和终点.
 *      求最短路径(需要输出路径).
 *
 *      其中每个节点都有一定的权值和其最大权值和半权值.
 *
 *      如果有多条最短路径
     *      问从起点到终点,并将路过的所有节点调节成半权值,
     *      需要最少权值的那条最短路径路.
 *
 * 思路:
 *      1. 迪杰斯特拉求最短路.
 *
 *      多条最短路:
 *          求得一条最短路后不停止算法,继续搜索.
 *
 *      求路径:
 *          每次更新最短距离数组(即起点到某一节点的最短距离)时,
 *          设置对应被更新的节点的父节点,最后通过一个递归就能找到路径
 *
 *      发车收车:
 *          题目要求,如果存在多条最短路径,那么选择
 *          1.带回最少车辆的那条; or 2. 发出最少车辆的那条;
 *
 *          设置两个数组.
 *
 *          分别是 带回最少车辆数组 minTakeBackCar,
 *          minTakeBackCar[i] 表示从起点到节点i 要带回的最少车辆数
 *
 *          发出最少车辆数组 minSentCar,
 *          minSentCar[i]表示从起点到节点i 要发出的最少车辆数
 *
 *          PBMC在每个节点都能获得一定数量的车辆,即 nodeCar - half-full,半权值减当前车辆
 *
 *          举个例子,如果某节点的权值是7,半权值是5,那么
 *          发出的车辆-2, 带回的车辆+2,
 *          如果某节点的权值是3,半权值是5,那么
 *          发出的车辆+2,带回的车辆-2
 */
public class PAT1018PublicBikeManagement_30point {

    public static void main(String[] args){
        PAT1018PublicBikeManagement_30point pat1018 = new PAT1018PublicBikeManagement_30point();
        pat1018.Input();
    }

    // 每个节点的权值(1-N)
    int[] nodeValue;
    // 在最短路径上每个节点的父节点
    int[] parentNode;

    // 邻接矩阵建图
    int[][] graph;

    // 最大权值
    int cMax;
    // 节点数
    int N;
    // 问题站点的编号
    int Sp;
    // 边数
    int M;


    boolean[] visited;
    int[] minLength;    // 最短距离数组,表示从起点到节点i的最短距离
    int[] minTakeBackCar;   // 带回最少车辆数组
    int[] minSentCar;   // 最短发出车辆数组

    public void Input(){
        Scanner in = new Scanner(System.in);
        cMax = in.nextInt();
        N = in.nextInt();
        Sp = in.nextInt();
        M = in.nextInt();

        nodeValue = new int[N+1];
        parentNode = new int[N+1];
        graph = new int[N+1][N+1];
        visited = new boolean[N+1];
        minLength = new int[N+1];
        minTakeBackCar = new int[N+1];
        minSentCar = new int[N+1];

        Arrays.fill(minLength,Integer.MAX_VALUE);
        Arrays.fill(minTakeBackCar,Integer.MAX_VALUE);
        Arrays.fill(minSentCar,Integer.MAX_VALUE);

        for(int i=1;i<=N;i++)
            nodeValue[i] = in.nextInt();

        for(int i=0;i<M;i++){
            int si = in.nextInt();
            int sj = in.nextInt();
            int tij = in.nextInt();

            graph[si][sj] = tij;
            graph[sj][si] = tij;
        }

        Slove();
    }

    public void Slove(){
        Djkstra();
    }

    public void Djkstra(){
        // 起点与终点
        int start = 0;
        int end = Sp;
        visited[start] = true;
        minLength[start] = 0;
        minTakeBackCar[start] = 0;
        minSentCar[start] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()){
            int nowNode = queue.poll();
            visited[nowNode] = true;

            int nextNode = -1;

            int minL = Integer.MAX_VALUE;
            // 更新当前节点与相邻节点的最短距离数组
            for(int i=1;i<=N;i++){

                if(graph[nowNode][i]!=0 && i==Sp){
                    // 如果i是问题节点,那么判断从当前点(nowNode)前往终点是不是一条最短路径,
                    // 如果是,那么判断从节点到这里是否需要发出的车辆更少,如果更少,那么设置父节点,
                    // 更新最少车辆数组
                    if(minLength[nowNode]+graph[nowNode][i] == minLength[i]){
                        int car = nodeValue[i] - cMax/2;
                        if(car>0){
                            // 说明终点的车辆多,要收回一部分
                            if(minTakeBackCar[i] > minTakeBackCar[nowNode] + car){
                                // 如果从当前节点出发,到达终点要收回的车辆小于之前的最短路径,那么重新设置
                                minTakeBackCar[i] = minTakeBackCar[nowNode] + car;

                                parentNode[i] = nowNode;
                            }
                        }else {
                            // 说明重点的车辆少,要发出一部分
                            if(minSentCar[i] > minSentCar[nowNode]+car){
                                minSentCar[i] = minSentCar[nowNode]+car;

                                parentNode[i] = nowNode;
                            }
                        }
                    }
                }

                // 两个节点之间有边,且目标节点从未访问过
                if(!visited[i] && graph[nowNode][i]!=0){
                    if(minLength[nowNode]+graph[nowNode][i] < minLength[i]){
                        // 更新最短距离数组
                        minLength[i] = minLength[nowNode]+graph[nowNode][i];

                        // 更新发车收车数组

                        // 判断当前节点的车是多了还是少了
                        int car = nodeValue[i] - cMax/2;

                        if(car>0){
                            // 当前节点的车多了,发出的最少车辆-car,带回的最少车辆+car
                            minTakeBackCar[i] = minTakeBackCar[nowNode] + car;
                            minSentCar[i] = minSentCar[nowNode] - car;
                        }else {
                            minTakeBackCar[i] = minTakeBackCar[nowNode] - car;
                            minSentCar[i] = minSentCar[nowNode] + car;
                        }

                        // 设置i节点的父节点为nowNode
                        parentNode[i] = nowNode;
                    }
                }

                // 找到离当前节点的最近的那个点作为下一个开始搜索的节点
                if(minL>minLength[i] && !visited[i]){
                    minL = minLength[i];
                    nextNode = i;
                }
            }

            if(nextNode==-1) break;
            queue.add(nextNode);
        }

        System.out.println(minSentCar[Sp]+"  "+minTakeBackCar[Sp]);
    }
}
