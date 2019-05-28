package PAT.GradeA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述:
 *      1. 设权重weight(R,L)表示从节点R到叶子节点L上经过的所有节点的权重之和
 *      2. 给定一棵每个节点带有权重的数,和一个目标值S
 *      3. 要求找到所有权重总和为S的路径
 * 思路:
 *      1. DFS.使用邻接链表存树.每当到达一个叶子节点,且权重这和=S时,记录路径
 */
public class PAT1042PathOfEqualWeight {

    public static void main(String[] args){
        PAT1042PathOfEqualWeight pat1042 = new PAT1042PathOfEqualWeight();
        pat1042.Input();
    }

    // 树上的节点数
    int N;
    // 非叶子节点数
    int M;

    // 目标权重
    int S;

    // 邻接链表存图
    List<Integer>[] graph ;

    // weights[i]=x表示节点i的权重为x
    int[] weights;


    List<String> result = new ArrayList<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        S = in.nextInt();

        weights = new int[N];

        // 初始化图
        graph = new ArrayList[N];

        for(int i=0;i<N;i++){
            weights[i] = in.nextInt();

            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            int id = in.nextInt();
            int childCount = in.nextInt();
            for(int j=0;j<childCount;j++){
                int child = in.nextInt();

                // 这里按有向图的方法存,
                // 因为不需要关注父亲节点
                graph[id].add(child);
            }
        }

        Slove();
    }

    public void Slove(){
        // 根节点
        int root = 0;

        DFS(root,weights[root],String.valueOf(weights[root]));

        Collections.sort(result);

        for(int i=result.size()-1;i>=0;i--){
            System.out.println(result.get(i));
        }
    }

    /**
     *
     * @param currentNode 当前要深搜的节点
     * @param currentWeight 当前权重
     * @param path  当前路径
     */
    public void DFS(int currentNode,int currentWeight,String path){

        // 判断当前节点是否是叶子节点
        if(graph[currentNode].isEmpty()){
            if(currentWeight==S){
                result.add(path);
                return;
            }
        }

        // 遍历孩子节点
        for(int child : graph[currentNode]){
            // 尝试进入孩子节点
            DFS(child,currentWeight+weights[child],path+" "+weights[child]);
        }
    }
}
