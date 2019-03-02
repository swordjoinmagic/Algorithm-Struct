package review.graphics;

import review.otherStruct.FindAndUnionSet;

import java.util.*;

public class MinimumSpanningTree {
    public static void main(String[] args){
        MinimumSpanningTree tree = new MinimumSpanningTree();
        tree.Slove();
    }

    class Edge{
        int point1;     // 边的端点1
        int point2;     // 边的端点2
        int value;      // 边的权值
        public Edge(int point1,int point2,int value){
            this.point1 = point1;
            this.point2=point2;
            this.value=value;
        }
        @Override
        public String toString(){
            return String.format("Edge[p1=%d,p2=%d,value=%d]",point1,point2,value);
        }
    }

    private int edgeCount;      // 边的数量
    private int vertexCount;    // 点的数量
    private int[][] graph;
    private List<Edge> edgeList = new ArrayList<>();

    public void  Slove(){
        Input();
        Kruskal();
        Prims();
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        vertexCount = in.nextInt();
        edgeCount = in.nextInt();

        // 初始化图,使用邻接矩阵存图
        graph = new int[vertexCount][vertexCount];

        // 下面是N条边的输入
        for(int i=0;i<edgeCount;i++){
            // 点1
            int p1 = in.nextInt()-1;
            // 点2
            int p2 = in.nextInt()-1;
            // 边的权值
            int value = in.nextInt();

            // 无向图，双向赋值
            graph[p1][p2] = value;
            graph[p2][p1] = value;

            Edge edge = new Edge(p1,p2,value);
            edgeList.add(edge);
        }
    }

    /**
     * 使用Prims算法解决最小生成树问题
     */
    public void Prims(){
        // 初始化生成树集合
        Stack<Integer> SpanningTreeSet = new Stack<>();

        // 最小生成树权值
        int minimumSpanningTreeValue = 0;
        // 初始化最短距离数组,
        // minLength[0] = value表示目前生成树集合距离点0的最短距离是value
        int[] minLength = new int[vertexCount];
        // 初始化该数组
        for(int i=0;i<vertexCount;i++)
            minLength[i] = Integer.MAX_VALUE;

        // 将起点加入生成树集合
        SpanningTreeSet.add(0);
        minLength[0] = 0;

        // 当生成树集合包含所有点时，结束循环
        while (SpanningTreeSet.size() < vertexCount){

            // 取出最近加入生成树集合的顶点
            int vertex = SpanningTreeSet.peek();

            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;
            // 遍历该点所有邻边，更新距离
            for(int i=0;i<vertexCount;i++){
                // 当minLength[i]=0表示，点i已在生成树内部
                if(minLength[i]!=0 && i!=vertex){
                    minLength[i] = Math.min(minLength[i],graph[vertex][i]);

                     // 查找最小生成树最近的点
                    if(minLength[i]<minValue){
                        minValue = minLength[i];
                        minIndex = i;
                    }
                }

            }

            // 将此距离最近的点加入最小生成树集合，并更新minLength数组
            if(minIndex!=-1){
                SpanningTreeSet.push(minIndex);

                minimumSpanningTreeValue += minLength[minIndex];

                // 点置于生成树集合内部，距离设为0
                minLength[minIndex] = 0;
            }
        }

        System.out.println("最小生成树权值："+minimumSpanningTreeValue);

    }

    /**
     * 使用Kruskal算法解决最小生成树问题
     */
    public void Kruskal(){

        // 最小权值
        int result = 0;
        // 初始化并查集
        FindAndUnionSet set = new FindAndUnionSet(vertexCount);

        // 对边列表进行从小到大快排
        edgeList.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.value>o2.value)
                    return 1;
                else if(o1.value<o2.value)
                    return -1;
                else
                    return 0;
            }
        });

        for(Edge edge : edgeList){
            // 如果该边的两个顶点不在一个集合内,那就合并他们,同时,为最小生成树集合加上这条边
            if(set.Find(edge.point1) != set.Find(edge.point2)){
                result += edge.value;
                set.Union(edge.point1,edge.point2);
            }
        }

        System.out.println("最小生成树权值为:"+result);
    }
}
