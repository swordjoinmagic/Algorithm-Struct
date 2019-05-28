package PAT.GradeA.error;

import java.util.*;

/**
 * 题目描述:
 *      1. 条条大路通罗马.给定起始地点,目标地点,各顶点的权值,各边的权值,
 *      求从起始地点到目标地点的最短路径
 *      2. 存在多条最短路径的情况下,输出快乐值最大的那一条,快乐值最大的情况,
 *      求平均快乐值最大的那一条
 *
 * 思路:
 *      1. DFS.用邻接链表存图.
 *      每当用DFS找到一条去罗马的路,
 *      就查看当前费用是否小于等于目前最小费用,如果小于,
 *      那么就进队(维护一个费用全是最小的最短路径队列)
 */
public class PAT1002AllRoadsLeadtoRomeError {

    public static void main(String[] args){
        PAT1002AllRoadsLeadtoRomeError pat1002 = new PAT1002AllRoadsLeadtoRomeError();
        pat1002.Input();
    }

    class Edge{
        String fromName;
        String toName;
        int cost;

        public Edge(String fromName, String toName, int cost) {
            this.fromName = fromName;
            this.toName = toName;
            this.cost = cost;
        }
    }

    class ResultNode implements Comparable<ResultNode>{
        int cost;
        int happiness;
        int averageHappiness;
        String path;

        public ResultNode(int cost, int happiness, int averageHappiness,String path) {
            this.cost = cost;
            this.happiness = happiness;
            this.averageHappiness = averageHappiness;
            this.path = path;
        }

        @Override
        public int compareTo(ResultNode o) {
            if(this.happiness>o.happiness){
                return -1;
            }else if(this.happiness<o.happiness){
                return 1;
            }else {
                return Integer.compare(o.averageHappiness, this.averageHappiness);
            }
        }
    }

    Deque<ResultNode> result = new LinkedList<>();

    Map<String,List<Edge>> graph = new HashMap<>();

    // 节点数量
    int N;
    // 边数
    int K;

    // 存储每个城市的快乐值
    Map<String,Integer> happinessMap = new HashMap<>();

    Map<String,Boolean> visiited = new HashMap<>();

    String startCity;

    int cityNumber = 0;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();
        startCity = in.next();

        for(int i=0;i<N-1;i++) {
            String name = in.next();
            int happiness = in.nextInt();
            happinessMap.put(name,happiness);
        }

        for(int i=0;i<K;i++){
            String fromCity = in.next();
            String toCity = in.next();
            int cost = in.nextInt();

            Edge edge1 = new Edge(fromCity,toCity,cost);
            Edge edge2 = new Edge(toCity,fromCity,cost);

            if(!graph.containsKey(fromCity)) graph.put(fromCity,new ArrayList<>());
            if(!graph.containsKey(toCity)) graph.put(toCity,new ArrayList<>());

            graph.get(fromCity).add(edge1);
            graph.get(toCity).add(edge2);
        }

        Slove();
    }

    public void Slove(){
        visiited.put(startCity,true);

        DFS(startCity,0,startCity,0,0);

        ResultNode[] resultList;
        resultList = result.toArray(new ResultNode[]{});
        Arrays.sort(resultList);

        ResultNode node = resultList[0];

        System.out.println(result.size()+" "+node.cost+" "+node.happiness+" "+node.averageHappiness);
        String resultStr = node.path;
        resultStr = resultStr.trim().replace(" ","->");
        System.out.println(resultStr);
    }

    public void Add(int cost,String path,int happiness,int passCityCount){

        // 如果该单调队列维护的最小值比要加进来的小,那就无视要加进来的值
        if(!result.isEmpty() && result.peekLast().cost<cost) return;

        // 将队尾所有cost大于该cost的出队
        while (!result.isEmpty() && result.peekLast().cost>cost) result.pollLast();

        // 进队
        ResultNode resultNode = new ResultNode(cost,happiness,happiness/passCityCount,path);
        result.add(resultNode);
    }

    /**
     *
     * @param currentCity 当前访问的城市
     * @param currentCost 当前使用的费用
     * @param path 当前路径
     * @param happiness 当前快乐值
     * @param passCityCount 经过的城市数
     */
    public void DFS(String currentCity,int currentCost,String path,int happiness,int passCityCount){

        // 判断是否到达罗马
        if(currentCity.equals("ROM")){
            Add(currentCost,path, happiness,passCityCount);

            return;
        }

        // 遍历当前城市所有邻居节点
        for(Edge neighboor : graph.get(currentCity)){
            if(!visiited.getOrDefault(neighboor.toName,false)){
                visiited.put(neighboor.toName,true);
                DFS(neighboor.toName,currentCost+neighboor.cost,path+" "+neighboor.toName,happiness+happinessMap.get(neighboor.toName),passCityCount+1);
                visiited.put(neighboor.toName,false);
            }
        }

    }
}
