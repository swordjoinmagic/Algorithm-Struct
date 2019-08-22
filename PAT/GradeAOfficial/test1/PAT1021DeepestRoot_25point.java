package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 给定一幅图,求其中深度最大的节点
 *      如果深度最大的节点不止一个,那么就按他们的序号升序输出
 *      如果给定的图不是树,那么输出该图有几个联通分量
 *
 * 思路:
 *      1. 对每一个节点来一次BFS算深度
 *
 * 坑:
 *      1. 首先要判断图是否是一棵树,满足以下条件的图才能算是一棵树:
 *          a. 边数 = 节点数-1
 *          b. 是联通图
 *          c. 无环
 *      其中条件b == c
 *      可以使用DFS判断该图是否是树,只要用DFS找到全部联通分量就可以了,如果联通分量>1,那么说明
 *      该图不是树,反之亦然.
 *
 */
public class PAT1021DeepestRoot_25point {

    public static void main(String[] args){
        PAT1021DeepestRoot_25point pat1021 = new PAT1021DeepestRoot_25point();
        pat1021.Input();
    }

    // 邻接矩阵建图
    boolean[][] graph;

    // 节点数
    int N;

    // 图的联通分量
    int connectedComponents;

    boolean[] visited;

    // 目前的最大深度
    int nowMaxDepth = 0;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        // 初始化图
        graph = new boolean[N][N];
        visited = new boolean[N];

        for(int i=0;i<N-1;i++){
            // 约束节点范围从0 ~ N-1
            int p1 = in.nextInt()-1;
            int p2 = in.nextInt()-1;
            graph[p1][p2] = true;
            graph[p2][p1] = true;
        }

        Slove();
    }

    public void Slove(){
        // 第一步先判断图是否是树
        if(!isTree()){
            // 不是树的情况,输出联通分量数量
            System.out.format("Error: %d components\n",connectedComponents);
            return;
        }

        List<Integer> list = new ArrayList<>();

        for(int i=0;i<N;i++){
            Arrays.fill(visited,false);
            int depth = BFS(i);

            if(depth>nowMaxDepth) {
                nowMaxDepth = depth;
                list.clear();
                list.add(i+1);
            }else if(depth==nowMaxDepth){
                list.add(i+1);
            }
        }

        Collections.sort(list);
        for(int node : list) System.out.println(node);
    }

    // 用BFS算节点x的深度,返回该节点的深度
    public int BFS(int node){
        int depth = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int nowNode = queue.poll();
                visited[nowNode] = true;
                for(int j=0;j<N;j++){
                    if(graph[nowNode][j] && !visited[j]) {
                        queue.add(j);
                        visited[j] = true;
                    }
                }
            }
            depth++;
        }
        return depth;
    }

    // 判断该图是否是树,如果是树,就返回false
    public boolean isTree(){
        for(int i=0;i<N;i++){
            if(!visited[i]){
                DFS(i);
                connectedComponents++;
            }
        }
        return connectedComponents==1;
    }

    public void DFS(int node){
        visited[node] = true;
        for(int i=0;i<N;i++){
            if(!visited[i] && graph[node][i])
                DFS(i);
        }
    }
}
