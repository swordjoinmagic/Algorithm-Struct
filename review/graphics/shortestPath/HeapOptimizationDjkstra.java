package review.graphics.shortestPath;


import java.util.*;

/**
 * 经过堆优化的Djkstra算法
 */
public class HeapOptimizationDjkstra {
    public static void main(String[] args){
        HeapOptimizationDjkstra djkstra = new HeapOptimizationDjkstra();
        djkstra.Input();
        djkstra.Slove();
    }

    public void Test(){
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(1,6));
        priorityQueue.add(new Node(2,5));
        priorityQueue.add(new Node(3,2));
        for(Node node : priorityQueue){
            System.out.println(node);
        }
    }

    class Edge{
        int right;
        int left;
        int value;
        public Edge(int right,int left,int value){
            this.right = right;
            this.left = left;
            this.value = value;
        }
    }
    class Node implements Comparable<Node>{
        int index;  //节点编号
        int minLength;  // 距离起点最短距离
        public Node(int index,int minLength){
            this.index = index;
            this.minLength = minLength;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.minLength, o.minLength);
        }

        @Override
        public String toString(){
            return "Node[("+this.index+")"+this.minLength+"]";
        }
    }

    // 使用邻接表建图
    private List<Edge>[] graph;
    // 顶点数
    private int vertex;
    // 边数
    private int edge;
    private Stack<Integer> set;

    // 最短距离数组，记录起点距离各点的最短距离
    private PriorityQueue<Node> minLength;

    // 起点
    private int start;
    // 终点
    private int end;

    public void Input(){
        Scanner in = new Scanner(System.in);
        vertex = in.nextInt();
        edge = in.nextInt();

        // 初始化邻接表
        graph = new ArrayList[vertex];
        for (int i=0;i<vertex;i++){
            graph[i] = new ArrayList<Edge>();
        }
        set = new Stack<>();
        minLength = new PriorityQueue<>();
        start = 0;
        end = vertex-1;

        for(int i=0;i<edge;i++){
            int p1 = in.nextInt()-1;
            int p2 = in.nextInt()-1;
            int value = in.nextInt();

            graph[p1].add(new Edge(p1,p2,value));
            graph[p2].add(new Edge(p2,p1,value));
        }
    }

    public void Slove(){

        // 将起点加入集合
        set.add(0);

        while (set.size()<vertex){

            // 获取当前点
            int point = set.peek();

            // 更新最短距离数组
            for(Edge edge : graph[point]){

                System.out.println("edge.right:"+edge.right+" isContain?:"+set.contains(edge.right));

                for(Node node : minLength){
                    System.out.println(node);
                }

                // 不更新已经到达过的顶点
                if(!set.contains(edge.left))
                    minLength.add(new Node(edge.right,edge.value));

            }

            // 找到目前距离起点最近的点
            while (set.contains(minLength.peek().index)) minLength.poll();
            // 当前点Node是距离起点最近的点,加入set
            Node node = minLength.poll();
            set.add(node.index);
        }

        System.out.println("result:"+minLength.peek());
    }
}
