package luogu.Graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class LuoGuP3388模板割点 {

    public static void main(String[] args) throws FileNotFoundException {
        LuoGuP3388模板割点 luoGuP3388 = new LuoGuP3388模板割点();
        luoGuP3388.Input();
    }

    // n个点
    int n;
    // m条边
    int m;

    // low[i]表示i及其孩子节点能到达的最小DFS序号
    int[] low;

    // DFS遍历时,给每个节点加上的序号
    // 下标是节点编号,值是DFS遍历该节点时的序号
    int[] DFN;

    int[] parent;

    int DFSIndex = 1;

    // 邻接链表
    ArrayList<Integer>[] graph;

    List<Integer> result = new ArrayList<>();

    public void Input() throws FileNotFoundException {
        System.setIn(new FileInputStream("C:\\Users\\Administrator\\Downloads\\testdata (3).in"));
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        // 初始化图
        graph = new ArrayList[n+1];
        for(int i=1;i<=n;i++) graph[i] = new ArrayList<>();
        // 初始化Low数组
        low = new int[n+1];
        DFN = new int[n+1];
        Arrays.fill(low, Integer.MAX_VALUE);

        parent = new int[n+1];

        for(int i=0;i<m;i++){
            int x = in.nextInt();
            int y = in.nextInt();
            graph[x].add(y);
            graph[y].add(x);
        }

        // 找根节点
        int root = 1;

        // 从任意一个节点出发
        DFS(1);

//        for(int i=1;i<=n;i++) if(parent[i]==0) {root = i; break;}
//
//        // 特殊处理根节点
//        if(graph[root].size()>2)
//            result.add(root);

        System.out.println(result.size());
        Collections.sort(result);
        for(int i:result) System.out.print(i+" ");
    }

    /**
     * 从结点x开始向下DFS
     * @param x
     */
    public void DFS(int x){
        // DFS序号
        DFN[x] = DFSIndex++;
        // 更新low数组
        low[x] = DFN[x];

        // 遍历其所有相邻节点
        for(int neighboor : graph[x]){

            if(DFN[neighboor]==0){
                // 表示该节点没被访问过
                // 非祖先的邻居节点
                // 继续进入DFS
                parent[neighboor] = x;
                DFS(neighboor);
                // 更新low数组
                low[x] = Math.min(low[x],low[neighboor]);

                // 如果该邻居节点最多只能到达节点x,那么说明节点x是割点
                // 特殊处理根节点(根节点不能这么判断)
                if(low[neighboor]>=DFN[x] && parent[x]!=0){
                    result.add(x);
                }

            }else if(parent[x]!=neighboor){
                // 如果是祖先,更新Low数组
                // 并且不进去DFS
                // 判断是不是祖先,要看目标节点的序号是否小于当前节点的序号
                low[x] = Math.min(low[x],DFN[neighboor]);
            }

        }
    }
}
