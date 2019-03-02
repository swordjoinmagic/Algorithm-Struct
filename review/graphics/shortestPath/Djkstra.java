package review.graphics.shortestPath;


import java.util.Scanner;
import java.util.Stack;

/**
 * 最短路径---Djkstra算法
 */
public class Djkstra {

    public static void main(String[] args){

        Djkstra djkstra = new Djkstra();
        djkstra.Input();
        djkstra.Slove();
        djkstra.Print();
    }

    // 使用邻接矩阵建图
    private int[][] graph;
    // 顶点数
    private int vertex;
    // 边数
    private int edge;
    // 起点,默认0号点为起点
    private int start = 0;
    // 终点
    private int end;
    // 用于标记某个点是否访问过
    private Stack<Integer> stack;
    // 最短路径数组,标记了从起点到各个点的最短路径
    private int[] path;

    public void Input(){
        Scanner in = new Scanner(System.in);
        vertex = in.nextInt();
        edge = in.nextInt();

        // 初始化邻接矩阵
        graph = new int[vertex][vertex];
        for(int i=0;i<vertex;i++)for(int j=0;j<vertex;j++) graph[i][j] = 99999999;
        stack = new Stack<>();
        path = new int[vertex];

        for(int i=0;i<edge;i++){
            int p1 = in.nextInt()-1;
            int p2 = in.nextInt()-1;
            int value = in.nextInt();

            // 无向图
            graph[p1][p2] = value;
            graph[p2][p1] = value;
        }

        // 暂定终点为最后一个点
        end = vertex-1;
    }

    public void Slove(){
        DjkstraAlgorithm();
    }

    // 使用Djkstra算法解决最短路径问题
    private void DjkstraAlgorithm(){

        // 初始化最短路径数组,标记起点已访问
        for(int i=0;i<vertex;i++) path[i] = Integer.MAX_VALUE;
        path[start] = 0;
        stack.add(start);

        while (stack.size()<vertex){

            // 取出当前起点
            int tempStartPoint = stack.peek();

            // 找到与起点距离最短的点,加入栈,使其成为下一个临时起点
            int minIndex = Integer.MAX_VALUE;
            int minValue = Integer.MAX_VALUE;

            // 遍历当前起点周围顶点,更新最短距离数组
            for(int i=0;i<vertex;i++){
                // 必须为未标记顶点
                if(!stack.contains(i)){
                    // 更新最短距离
                    path[i] = Math.min(path[i],path[tempStartPoint]+graph[tempStartPoint][i]);
                }

                // 必须为未标记的顶点
                if(path[i]<minValue && !stack.contains(i)){
                    minValue = path[i];
                    minIndex = i;
                }
            }

            // 将该点加入栈
            stack.push(minIndex);
        }

    }

    public void Print(){
        for(int i=0;i<vertex;i++){
            System.out.println(i+":"+path[i]);
        }
    }

    public void PrintGraph(){
        for(int i=0;i<vertex;i++){
            for(int j=0;j<vertex;j++){
                System.out.print(graph[i][j]+" ");
            }
            System.out.println();
        }
    }
}
