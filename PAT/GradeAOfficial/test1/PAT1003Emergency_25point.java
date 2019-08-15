package PAT.GradeAOfficial.test1;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 题意:
 *      1. 给出N个节点,M条路径,N个节点每个节点都有一定数量的救援人员,
 *      现给出起点C1和终点C2,求从C1出发到C2一共有多少条最短路径,
 *      同时在这些最短路径中,可以从C1带最多多少救援人员到终点
 *
 * 思路:
 *      1. 多源最短路经典问题,用迪杰斯特拉算法.时间复杂度O(N2)(N为节点数)
 *      从起点C1开始使用迪杰斯特拉算法,当遇到终点时,不把终点加入已探索区域,
 *      继续进行算法,直到所有节点都搜索完毕(即最短路计算完毕),每次有节点可以
 *      通向终点时,判断是否是最短路径,如果是,那么最短路径条数+1
 */
public class PAT1003Emergency_25point {

    public static void main(String[] args){
        PAT1003Emergency_25point pat1003 = new PAT1003Emergency_25point();
        pat1003.Input();
        System.out.println("");
    }

    // 邻接矩阵存图
    int[][] graph;

    // 各个城市救援人员数量
    int[] cityPerson;

    // 起点与终点
    int start,end;

    // 节点数与边数
    int N,M;

    // 最短路径条数
    int minPathCount;

    // 最短路径长度
    int minPathLength;

    // 所有城市距离起点的最短距离
    int[] minPath;

    // maxPerson[i]表示从起点出发最多可以带多少人到i城市
    int[] maxPerson;

    // 目前已经过的城市
    boolean[] visited;

    final int MAXValue = 99999999;

    public void Input(){
        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        M = in.nextInt();
        start = in.nextInt();
        end = in.nextInt();

        cityPerson = new int[N];
        graph = new int[N][N];
        visited = new boolean[N];
        maxPerson = new int[N];
        for(int i=0;i<N;i++){
            cityPerson[i] = in.nextInt();
            Arrays.fill(graph[i],MAXValue);
        }

        for(int i=0;i<M;i++){
            int c1 = in.nextInt();
            int c2 = in.nextInt();
            int L = in.nextInt();
            graph[c1][c2] = L;
            graph[c2][c1] = L;
        }

        Djkstra();
    }

    public void Djkstra(){

        // 初始化
        Queue<Integer> queue = new LinkedList<>();

        // 加入起点
        queue.add(start);

        visited[start] = true;
        maxPerson[start] = cityPerson[start];

        // 初始化最短距离数组
        minPath = new int[N];
        Arrays.fill(minPath,MAXValue);
        minPath[start] = 0;

        while (!queue.isEmpty()){

            // 获取当前节点(当前出发城市)
            int nowCity = queue.poll();

            // 更新周围城市距离起点的距离
            // 同时找到还未探索的目前与起点最近的城市,作为下一次探索的起点
            int minLength = MAXValue;
            int nextCity = -1;
            for(int i=0;i<N;i++){
                // 表示如果从当前城市出发到i城市的距离比现有的距离要短,那么就更新起点距离i城市的距离
                if(graph[nowCity][i] + minPath[nowCity] < minPath[i]){
                    minPath[i] = graph[nowCity][i] + minPath[nowCity];

                    // 更新从当前城市带人到i城市
                    maxPerson[i] = maxPerson[nowCity]+cityPerson[i];

                    // 如果出现了距离终点更短的距离,那么更新路径条数=0
                    if(i==end) minPathCount = 0;
                }

                // 更新最短路径条数,表示如果从当前城市出发到终点的距离等于目前到终点的最短距离,
                // 那么最短路径条数+1
                if(graph[nowCity][i] + minPath[nowCity] == minPath[i] && i==end){
                    minPathCount ++;
                    // 更新从当前城市带人到i城市人比较多还是先有的比较多
                    maxPerson[i] = Math.max(maxPerson[i],maxPerson[nowCity]+cityPerson[i]);
                }

                // 找到距离目前城市最近的城市,选用该最近城市作为下一次的出发点
                if(minPath[i]<minLength && !visited[i]){
                    minLength = minPath[i];
                    nextCity = i;
                }
            }

            if(nextCity==-1) break;

            queue.add(nextCity);
            visited[nextCity] = true;
        }

        System.out.println(minPathCount+" "+maxPerson[end]);
    }
}
