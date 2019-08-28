package PAT.GradeAOfficial.test1;

import javax.lang.model.util.AbstractAnnotationValueVisitor6;
import java.util.*;

/**
 * 题意:
 *      1. 给定N对关系,求团伙数量和每个团伙的首领
 * 思路:
 *      1. 一幅无向图.求连通分量,即团伙数量.
 *      用DFS求联通分量,同时可以求出团伙人数/边总权值/每个点的所有边权值
 *      2. 需要注意给每个点标上序号,因为题目中是人名
 */
public class PAT1034HeadofaGang_30point {

    public static void main(String[] args){
        PAT1034HeadofaGang_30point pat1034 = new PAT1034HeadofaGang_30point();
        pat1034.Input();
    }

    class Gang{
        String head;
        int totalPerson;

        public Gang(String head, int totalPerson) {
            this.head = head;
            this.totalPerson = totalPerson;
        }
    }

    // 用于给每个人标号
    Map<String,Integer> mapStringToInt = new HashMap<>();
    String[] mapIntToString = new String[1000];

    // 邻接矩阵建图
    int[][] graph = new int[1000][1000];

    // 记录条数
    int N;
    // 阈值
    int K;

    // 表示每个节点的所有边权值
    int[] nodeValue = new int[1000];

    // 表示每个节点是否被访问
    boolean[] visited = new boolean[1000];
    boolean[][] visitedEdge = new boolean[1000][1000];

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();

        int index = 0;

        for(int i=0;i<N;i++){
            String p1 = in.next();
            String p2 = in.next();
            int value = in.nextInt();
            if(!mapStringToInt.containsKey(p1)) mapStringToInt.put(p1,index++);
            if(!mapStringToInt.containsKey(p2)) mapStringToInt.put(p2,index++);

            int c1 = mapStringToInt.get(p1); int c2 = mapStringToInt.get(p2);
            mapIntToString[c1] = p1; mapIntToString[c2] = p2;

            graph[c1][c2] += value;
            graph[c2][c1] += value;

            nodeValue[c1] += value;
            nodeValue[c2] += value;
        }

        Slove();
    }

    int personCount = 0;
    int totalValue = 0;
    String head;

    int nowMaxEdgeValue = 0;

    List<Gang> gangList = new ArrayList<>();

    int size;

    public void DFS(int nowNode){
        personCount ++;
        if(nodeValue[nowNode]>nowMaxEdgeValue){
            nowMaxEdgeValue = nodeValue[nowNode];
            head = mapIntToString[nowNode];
        }

        for(int i=0;i<size;i++){

            if(graph[nowNode][i]>0 && !visitedEdge[nowNode][i]){
                visitedEdge[nowNode][i] = true; visitedEdge[i][nowNode] = true;
                totalValue += graph[nowNode][i];
            }

            if(!visited[i] && graph[nowNode][i]>0){
                visited[i] = true;
                DFS(i);
            }
        }
    }

    public void Slove(){

        // 总节点数量
        size = mapStringToInt.size();

        // 第一步,求联通分量,判断团伙数量
        for(int i=0;i<size;i++){
            if(!visited[i]){
                personCount = 0;
                totalValue = 0;
                head = "";
                nowMaxEdgeValue = 0;
                visited[i] = true;
                DFS(i);
                // 如果该联通分量有大于3个节点且边总的权值>K
                // 那么团伙数量+1
                if(personCount>=3 && totalValue>K){
                    Gang gang = new Gang(head,personCount);
                    gangList.add(gang);
                }
            }
        }


        System.out.println(gangList.size());
        Collections.sort(gangList,new Comparator<Gang>(){
            @Override
            public int compare(Gang o1,Gang o2){
                return o1.head.compareTo(o2.head);
            }
        });
        if(gangList.size()>0)
        for(Gang gang : gangList)
            System.out.println(gang.head+" "+gang.totalPerson);
    }
}
