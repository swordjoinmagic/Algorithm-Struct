package PAT.GradeA;

import java.util.*;

/**
 * 题目描述:
 *      1. 给定一个家族谱系树,求人数最多的那一层
 * 思路:
 *      1. 用建图的方法建树,用宽度优先搜索进行搜索,
 *
 */
public class PAT1005TheLargestGeneration {

    public static void main(String[] args){
        PAT1005TheLargestGeneration pat1005 = new PAT1005TheLargestGeneration();
        pat1005.Input();
    }

    // 总节点数
    int N;
    // 家庭数
    int M;

    // 邻接链表建图
    List<Integer>[] graph;

    // 最多的人数
    int max = 0;
    // 人数最多的一代
    int maxGeneration = 0;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();

        // 舍弃下标0
        graph = new ArrayList[N+1];

        for(int i=0;i<M;i++){
            int id = in.nextInt();
            if(graph[id]==null) graph[id] = new ArrayList<>();
            int count = in.nextInt();
            for(int j=0;j<count;j++){
                int childID = in.nextInt();
                graph[id].add(childID);
            }
        }

        BFS();

        System.out.println(max+" "+maxGeneration);
    }

    public void BFS(){

        // 代
        int step = 0;

        // 根节点
        int root = 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){

            step++;

            // 这一代人的数量
            int size = queue.size();
            if(size>max){
                max = size;
                maxGeneration = step;
            }

            for(int i=0;i<size;i++){
                int id = queue.poll();
                if(graph[id]==null) continue;
                for(int childID:graph[id]){
                    queue.add(childID);
                }
            }

        }
    }
}
