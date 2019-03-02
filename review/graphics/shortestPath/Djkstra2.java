package review.graphics.shortestPath;

import java.util.*;

// 基于邻接表的Djkstra
public class Djkstra2 {

    public static void main(String[] args){
        Djkstra2 djkstra2 = new Djkstra2();
        djkstra2.Input();
        djkstra2.Slove();
    }

    class Edge{
        int p;  // 边所连接的节点的编号
        int value;  // 边的权值

        public Edge(int p,int value){
            this.p = p;
            this.value = value;
        }
    }

    // 邻接表
    List<List<Edge>> graph;
    // 顶点数量
    int vertexCount;
    // 边的数量
    int edgeCount;

    public void Input(){
        Scanner in = new Scanner(System.in);
        vertexCount = in.nextInt();
        edgeCount = in.nextInt();

        // 初始化邻接表
        graph = new ArrayList<>();
        for(int i=0;i<vertexCount;i++)  graph.add(new ArrayList<>());

        // 输入点对关系
        for(int i=0;i<edgeCount;i++){
            int p1 = in.nextInt()-1;
            int p2 = in.nextInt()-1;
            int value = in.nextInt();

            Edge edge1 = new Edge(p1,value);
            Edge edge2 = new Edge(p2,value);

            graph.get(p1).add(edge2);
            graph.get(p2).add(edge1);
        }
    }

    class Node implements Comparable<Node>{
        int p;      // 该点编号
        int value;  // 从原点到改点的最短距离
        public Node(int p,int value){
            this.p = p;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public void Slove(){

        // 用于堆优化的优先队列
        PriorityQueue<Node> heapOptimizationQueue = new PriorityQueue<>();

        // 初始化最短距离数组,用于记录源点到各个点的最短距离
        int[] minLength = new int[vertexCount];
        for(int i=0;i<minLength.length;i++){
            minLength[i] = 99999999;
        }
        minLength[0] = 0;

        // 用于标记哪些点已经访问过
        Stack<Integer> set = new Stack<>();

        // 首先将起点加入集合
        set.add(0);

        while (set.size() < vertexCount){

            // 取出离原点最近的一个结点
            int index = set.peek();

            System.out.println("popIndex:"+index);

            // 更新最短距离数组
            for(Edge edge : graph.get(index)){
                System.out.println("edge.p:"+edge.p+" edgeValue:"+edge.value);
                // 只更新没被访问过的节点
                if(!set.contains(edge.p)){
                    minLength[edge.p] = Math.min(minLength[edge.p],minLength[index]+edge.value);
                    heapOptimizationQueue.add(new Node(edge.p,minLength[edge.p]));
                }
            }

            // 堆优化
            int minIndex = -1;
            while (set.contains(heapOptimizationQueue.peek().p)) heapOptimizationQueue.poll();
            minIndex = heapOptimizationQueue.poll().p;

            // 此处可进行堆优化
            //=================================================
            /*
            int minValue = 99999999;
            int minIndex = -1;
            // 找到目前没被访问过且离原点最近的节点
            for(int i=0;i<minLength.length;i++){
                if(!set.contains(i) && minLength[i]<minValue){
                    minValue = minLength[i];
                    minIndex = i;
                }
            }
            */
            //===================================================
            set.add(minIndex);
        }

        for(int i=0;i<minLength.length;i++){
            System.out.println(i+" : "+minLength[i]);
        }
    }
}
