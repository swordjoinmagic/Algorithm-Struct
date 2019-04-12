package luogu.TreeDP;

import java.util.*;

/**
 * 思路：
 *      1. 设f[u][i]为以u为根节点，保留i根树枝时能留住的最多苹果
 *      状态转移方程如下：
 *
 *      f[u][i] = max{
 *          f[u.left][j-1] + f[u.right][i-j-1] + (j-1>0?edge[ u->u.left ]) + (i-j-1>0? edge[  u->u.right ])
 *      }
 *
 *      表示的意思是,
 *      以u为根节点,保留i根树枝的最大苹果数 =
 *      Max{
 *          以左子树为根节点,保留j-1根树枝的最大苹果数(之所以-1是因为要在左子树上保留j根树枝的话,一定要保留u->u.left这一根树枝)
 *          +
 *          以右子树为根节点,保留 i-j-1(即根节点保留树枝减去左子树保留树枝,-1原因同上)时的最大苹果数
 *          +
 *          (如果在左子树上保留的树枝>0) 根节点连接左子树那条边
 *          +
 *          (如果在右子树上保留的树枝>0) 根节点连接右子树那条边
 *      }
 *      其中,设题目要求保留的树枝数为Q,
 *      则,   j<=Q
 *
 *
 *
 */
public class LuoGuProblem2015二叉苹果树 {

    public static void main(String[] args){
        LuoGuProblem2015二叉苹果树 problem2015 = new LuoGuProblem2015二叉苹果树();
        problem2015.Input();
        problem2015.Slove();
    }

    // 使用邻接矩阵存图
    private int[][] graph;
    // 节点数
    private int N;
    // 要保留的树枝的数量
    private int Q;

    private boolean[] visited;

    // 层次遍历列表
    private List<Integer> levelList;
    // childs[i]表示i节点的所有孩子节点
    private List<Integer>[] childs;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        Q = in.nextInt();

        // 舍弃下标0
        graph = new int[N+1][N+1];
        for(int i=0;i<=N;i++)
            Arrays.fill(graph[i],-1);
        for(int i=0;i<N-1;i++){
            int p1 = in.nextInt();
            int p2 = in.nextInt();
            int value = in.nextInt();
            graph[p1][p2] = value;
            graph[p2][p1] = value;
        }

        visited = new boolean[N+1];

        GetLevelList();
    }

    /**
     * 根据根节点获得该树的层次遍历
     */
    private void GetLevelList(){
        // 利用队列进行层次遍历
        Queue<Integer> queue = new LinkedList<>();
        levelList = new ArrayList<>(N+1);
        childs = new ArrayList[N+1];
        for(int i=1;i<=N;i++) childs[i] = new ArrayList<>();

        // 队列加入根节点
        queue.add(1);
        levelList.add(1);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            // 保证不会在访问到这个节点(防止在遍历当前节点子节点的时候遍历到自己)
            visited[node] = true;
            // 找到该节点的所有子节点
            for(int i=1;i<=N;i++){
                // 如果i是node子节点
                if(graph[node][i]!=-1 && !visited[i]){
                    queue.add(i);
                    levelList.add(i);
                    childs[node].add(i);
                }
            }
        }
    }

    /**
     * 从叶子节点向根节点进行计算
     * 状态转移方程为:
     *
     *  f[u][i] =
     *  max{ f[u.left][q-1],f[u.right][i-q-1]  +  (q-1>0? A[u -> u.left]) + (i-q-1>0? A[u -> u.right]) }
     *
     *  表示,以u为根节点,保留i根树枝时,得到的最大苹果数 等于
     *
     *  该树的左子树保留q个节点时最大苹果数,右子树保留i-q个节点时的最大苹果数的和的最大值
     */
    public void Slove(){

        int[][] f = new int[N+1][Q+1];

        // 从叶子节点向根节点进行计算
        for(int t=levelList.size()-1;t>=0;t--){
            int u = levelList.get(t);
            // 获得当前节点的所有孩子节点的列表
            List<Integer> childList = childs[u];

            // 如果该节点不是叶子节点,那么就可以开始计算了(简单来说就是算左子树右子树保留1~Q个树枝时的最大值)
            if(childList.size()!=0){

                // 树一定有2叉,不用考虑只有一个孩子的情况

                // 孩子节点1
                int node1 = childList.get(0);
                // 孩子节点2
                int node2 = childList.get(1);

                // 表示保留i根树枝
                for(int i=1;i<=Q;i++){

                    int max = 0;

                    // 表示孩子节点1保留q根树枝
                    for(int q=0;q<=i;q++){
                        // 表示孩子节点2保留q2根树枝
                        int q2 =  i-q;
                        int current = 0;

                        if(q==0)
                            current = f[node2][q2-1]+graph[u][node2];
                        else {
                            current = f[node1][q-1]+graph[u][node1];
                            if(q2>0)
                                current += f[node2][q2-1]+graph[u][node2];
                        }

                        if(current>max) max = current;
                    }
                    f[u][i] = max;

                }

            }
        }

        System.out.println(f[1][Q]);
    }
}
