package szptLesson.DP.TreeDP;

import java.util.*;

public class TreeDP1559 {


    public static void main(String[] args){
        TreeDP1559 treeDP = new TreeDP1559();
        treeDP.Input();
        treeDP.Slove();
    }

    // N:树的节点数,Q:要保留的边数量
    private int N,Q;

    // 用图存树
    private int[][] graph;

    public void Input(){

        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        Q = in.nextInt();

        graph = new int[N+1][N+1];
        for(int i=0;i<=N;i++)
            Arrays.fill(graph[i],-1);

        for(int i=0;i<N-1;i++){
            int p1 = in.nextInt();
            int p2 = in.nextInt();
            int value = in.nextInt();

            // 无向图
            graph[p1][p2] = value;
            graph[p2][p1] = value;
        }

    }

    public void Slove(){

        //===========================
        // 得到该树的层次遍历数组
        int index = 1;
        // 队列
        int[] queue = new int[N+1];
        boolean[] mark = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        // 根节点编号一定为1
        q.add(1);
        mark[1] = true;
        while (!q.isEmpty()){
            // 根节点
            int head = q.poll();
            for(int i=1;i<=N;i++){
                // 找到该根节点下的所有子节点
                if(graph[head][i]!=-1 && !mark[i]){
                    mark[i] = true;
                    queue[index++] = graph[head][i];
                    q.add(i);
                }
            }
        }

        for(int data:queue){
            System.out.println(data);
        }
    }
}
