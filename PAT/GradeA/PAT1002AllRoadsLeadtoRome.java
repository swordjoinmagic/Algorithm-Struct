package PAT.GradeA;

import review.graphics.shortestPath.Djkstra;

import java.util.*;

/**
 * 思路：
 *      1. 将题目给出的节点名称转变为编号(方便后续计算),其中设置起始节点编号为0
 *      2. 使用邻接矩阵建图
 *      3. 使用Djkstra寻找最短路径
 *      4. 设 minDistance[x]=y 表示从起始节点到x节点处最短距离是y
 *      5. 更新最短路径的同时，要更新快乐值和经过的城市数，更新方法如下:
 *          maxHappenes[i] = maxHappenees[k] + happeness[i];
 *      表示从起始节点到i节点时获得的最大快乐值是节点k的最大快乐值+节点i的最大快乐值
 *      更新经过城市数同理.
 *      6. 设 pre[i]=k, 表示 经过i节点的最短路径的上一个节点为k
 *
 */
public class PAT1002AllRoadsLeadtoRome {

    public static void main(String[] args){
        PAT1002AllRoadsLeadtoRome pat1002 = new PAT1002AllRoadsLeadtoRome();
        pat1002.Input();
    }

    // 邻接矩阵建图
    int[][] graph;
    // 记录上一个节点
    int[] pre;
    // 起始节点到各节点的最短距离
    int[] minDistance;
    // 起始节点到各节点的最大快乐值
    int[] maxHappiness;
    // 起始节点到各节点经过的城市数量
    int[] passCity;
    // 各节点快乐值
    int[] happiness;

    // 将各节点名称转为编号
    Map<String,Integer> id = new HashMap<>();

    // 节点数
    int N;
    // 边数
    int K;

    // 起始城市
    String startCity;
    // 终点城市
    String finalCity = "ROM";

    // 用于判断是否访问过某城市
    boolean[] visited;

    // 目前与终点的最短距离
    int nowMinDistance = Integer.MAX_VALUE;

    // 最短路条数
    int minPathCount;

    String[] names;


    //===========================
    // 输出结构

    // 目前最短路径
    String nowMinPath;
    // 目前最大快乐值
    int nowMaxHappiness;
    // 目前最大快乐平均值
    int nowMaxAverageHappiness;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();
        startCity = in.next();

        names = new String[N];

        // 设置开始节点编号为0
        id.put(startCity,0);
        names[0] = startCity;

        happiness = new int[N];

        for(int i=1;i<N;i++){
            String cityName = in.next();
            int happy = in.nextInt();

            // 设置名称与id对应
            id.put(cityName,i);
            names[i] = cityName;
            happiness[i] = happy;
        }

        visited = new boolean[N];
        graph = new int[N][N];

        for(int i=0;i<K;i++){
            String city1 = in.next();
            String city2 = in.next();
            int cost = in.nextInt();

            int city1ID = id.get(city1);
            int city2ID = id.get(city2);

            // 无向图
            graph[city1ID][city2ID] = cost;
            graph[city2ID][city1ID] = cost;
        }

        Slove();
    }

    public void Slove(){
        Djkstra();
        System.out.println(minPathCount+" "+nowMinDistance+" "+nowMaxHappiness+" "+nowMaxAverageHappiness);
        System.out.println(nowMinPath);

    }

    /**
     * 根据Pre数组生成路径
     * @param pre
     * @return
     */
    public String FindPath(int[] pre,int i){

        if(i==0)
            return startCity;

        String result = "";

        result += FindPath(pre, pre[i])+"->"+names[i];

        return result;
    }

    /**
     * Djkstra算法求解最短路
     */
    public void Djkstra(){

        // 初始化
        pre = new int[N+1];
        maxHappiness = new int[N];
        passCity = new int[N];
        minDistance = new int[N+1];
        Arrays.fill(minDistance,Integer.MAX_VALUE);
        visited[0] = true;
        minDistance[0] = 0;

        Stack<Integer> stack = new Stack<>();
        stack.add(0);

        while (!stack.isEmpty()){

            // 取出当前节点
            int node = stack.pop();
            visited[node] = true;

            // 目前与起点距离最近的点的距离
            int minValue = Integer.MAX_VALUE;
            // 目前与起点距离最近的点
            int minIndex = -1;

            // 更新当前节点周围相邻节点与起点的最短距离
            // 同时找到目前与起点距离最近的且没被访问过的节点
            for(int i=0;i<N;i++){
                // 要求目标节点没有被访问过
                if(graph[node][i]>0 && !visited[i]){

                    // 如果目标节点就是终点，且本次距离（指 node - i之间的距离）和当前最短距离一致
                    // 那么最短路条数+1
                    if(i==id.get(finalCity) && minDistance[i]==minDistance[node]+graph[node][i]){
                        minPathCount++;

                        // 更新经过城市数
                        passCity[i] = passCity[node] + 1;

                        // 这条最短路径的快乐值
                        int h = maxHappiness[node] + happiness[i];

                        // 判断新的这条与之前最短路径相同的路径的最大快乐值是否比原先大
                        // 如果比原先大,那么采用这一条
                        if(h>nowMaxHappiness){
                            // 更新最大快乐值
                            maxHappiness[i] = h;
                            nowMaxHappiness = h;
                            // 更新pre数组
                            pre[i] = node;
                            // 更新平均快乐值
                            nowMaxAverageHappiness = nowMaxHappiness/passCity[i];
                            // 更新路径
                            nowMinPath = FindPath(pre,i);
                        }else if(h==nowMaxHappiness){

                            // 判断平均值
                            int average = nowMaxHappiness/passCity[i];

                            if(average>nowMaxAverageHappiness){
                                // 更新最大快乐值
                                maxHappiness[i] = h;
                                // 更新pre数组
                                pre[i] = node;
                                // 更新平均快乐值
                                nowMaxAverageHappiness = average;
                                // 更新路径
                                nowMinPath = FindPath(pre,i);
                            }

                        }
                    }

                    // 更新该节点与起点的最短距离
                    if(minDistance[i]>minDistance[node]+graph[node][i]){
                        minDistance[i] = minDistance[node]+graph[node][i];
                        // 更新经过城市数
                        passCity[i] = passCity[node] + 1;
                        // 更新最大快乐值
                        maxHappiness[i] = maxHappiness[node] + happiness[i];
                        // 更新pre数组
                        pre[i] = node;


                        // 如果目标节点就是终点，更新最短路条数和最短距离
                        if(i==id.get(finalCity) && minDistance[i]<nowMinDistance){
                            // 清空最短路条数
                            minPathCount = 1;
                            // 更新最短距离
                            nowMinDistance = minDistance[i];
                            // 更新路径
                            nowMinPath = FindPath(pre,i);
                            // 更新快乐值
                            nowMaxHappiness = maxHappiness[i];
                            // 更新平均快乐值
                            nowMaxAverageHappiness = nowMaxHappiness/passCity[i];
                        }
                    }

                }

                // 找到目前与起点距离最近的点(用于下一次搜索)
                if(!visited[i] && minDistance[i]<minValue && i!=id.get(finalCity)){
                    minValue = minDistance[i];
                    minIndex = i;
                }
            }

            if(minIndex == -1) break;

            // 将于起点距离最近的点加入栈
            stack.add(minIndex);
        }
    }
}
