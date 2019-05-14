package PAT.GradeA;

import java.util.*;

/**
 * 题目描述:
 *      1. 每个节点可以容纳的最大车辆数为 c(Max)
 *      2. 如果出现了一个Problem Node,那就要去回收或者发车给他,
 *      3. 选取一条最短路径到达Problem Node
 *      4. 如果有多条最短路径,选发车最少的
 * 疑惑点:
 *      1. 似乎是可以从PBMC处无限发车到其他车站,且不计算在路径内
 *      最短路径只计算从PBMC处到达问题车站处
 * 思路:
 *      1. 因为存在多条最短路径,可以考虑使用DFS遍历每种可能,
 *      找出所有最短路径,并计算沿路需要的单车数量
 */
public class PATP1001PublicBikeManagement {

    public static void main(String[] args){
        PATP1001PublicBikeManagement patp1001 = new PATP1001PublicBikeManagement();
        patp1001.Input();
    }

    // 每个站点最大单车数
    int cMax;
    // 问题站点(站点标号为1-N)
    int sp;
    // 总站点数
    int N;
    // 边数
    int M;

    // 每个节点存储的单车数
    int[] stationBikes;

    // 用矩阵存图
    int[][] graph;
    boolean[] visited;

    // 最短距离
    int minDistance = Integer.MAX_VALUE;

    // 最少派出车辆
    int minBikes = Integer.MAX_VALUE;

    // 回收车辆
    int returnBikes = 0;

    // 最短路径
    List<Integer> minPath;

    public void Input(){
        Scanner in = new Scanner(System.in);
        cMax = in.nextInt();
        sp = in.nextInt();
        N = in.nextInt();
        M = in.nextInt();

        // 舍弃下标0
        stationBikes = new int[N+1];
        graph = new int[501][501];
        visited = new boolean[N+1];

        Arrays.fill(graph[0],-1);
        for(int i=1;i<=N;i++){
            stationBikes[i] = in.nextInt();
            Arrays.fill(graph[i],-1);
        }

        for(int i=0;i<M;i++){
            int si = in.nextInt();
            int sj = in.nextInt();
            int tij = in.nextInt();

            // 无向图
            graph[si][sj] = tij;
            graph[sj][si] = tij;
        }

        Slove();
    }

    public void Slove(){
        DFS(0,0,new LinkedList<>());
        System.out.print(minBikes<0?0:minBikes);

        System.out.print(" ");

        System.out.print("0");
        for(int index : minPath){
            System.out.print("->"+index);
        }
        System.out.print(" ");

        System.out.println(returnBikes);
    }

    public void UpdateMinBike(Queue<Integer> path){

        List<Integer> p = new ArrayList<>();

        int mid = cMax/2;

        int total = 0;

        for (int stationIndex : path){
            int bikeNumber = stationBikes[stationIndex];

            if(bikeNumber>mid){
                // 回收
                total -= bikeNumber-mid;
            }else if(bikeNumber<mid){
                // 派发
                total += mid-bikeNumber;
            }

            p.add(stationIndex);
        }

        if(total<minBikes) {
            minPath = p;
            minBikes = total;
            if(total<0){
                // 此时total是需要从问题站点回收的单车数
                returnBikes = -total;
            }
        }
    }

    /**
     * 从x号站点出发,向四周能到达的站点前进
     * @param x
     * @param distance 一共走了多久
     */
    public void DFS(int x, int distance, Queue<Integer> path){

        // 到达问题站点
        if(x==sp){
            // 更新最短距离
            if(distance<=minDistance){
                minDistance = distance;

                // 更新最短剩余单车
                UpdateMinBike(path);
            }
        }

        for(int i=1;i<=N;i++){
            if(graph[x][i]!=-1 && !visited[i]){
                // 标记i号站点已访问
                visited[i] = true;

                // 路径加入节点
                path.add(i);

                // 进入i号站点
                DFS(i,distance+graph[x][i],path);

                // 恢复
                path.poll();

                // 恢复标记
                visited[i] = false;
            }
        }
    }
}
