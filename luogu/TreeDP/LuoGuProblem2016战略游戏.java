package luogu.TreeDP;

import java.util.*;

/**
 *
 * 思路:
 *
 *      类似没有上司的舞会.
 *
 *      设f[u][0]为不选取该节点时需要放置的最少士兵数
 *        f[u][1]为选取该节点时需要放置的最少士兵数
 *
 *      状态转移方程如下:
 *        f[u][0] = sum{ f[u.child][1] }
 *      表示的意思是,如果不选该节点的话,那么就要选他的所有子节点(因为必须所有路都能被瞭望到)
 *
 *      第二个转移方程如下:
 *        f[u][1] = sum{ min( f[u.child][0],f[u.child][1] )  }
 *      表示的意思是,如果选取该节点,那么它的子节点选不选都可以,
 *      因为此时已经可以瞭望到他跟它的子节点了
 */
public class LuoGuProblem2016战略游戏 {

    public static void main(String[] args){
        LuoGuProblem2016战略游戏 problem2016 = new LuoGuProblem2016战略游戏();
        problem2016.Input();
        problem2016.Slove();
    }

    // 邻接链表建图
    private List<Integer> graph[];

    // 节点数量
    private int N;

    // 树的层次遍历
    private List<List<Integer>> levelList;

    // 根节点编号
    private int parentNode;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        graph = new ArrayList[N];

        // isParent[i]表示i是否是根节点,true为是,false为不是
        boolean[] isParent = new boolean[N];
        Arrays.fill(isParent,true);

        for(int i=0;i<N;i++){
            // 当前节点编号
            int nodeIndex = in.nextInt();
            int k = in.nextInt();

            graph[nodeIndex] = new ArrayList<>();

            for(int j=0;j<k;j++){
                // 子节点编号
                int child = in.nextInt();
                graph[nodeIndex].add(child);
                isParent[child] = false;
            }
        }

        // 找到根节点
        for(int i=0;i<N;i++){
            if(isParent[i]){
                parentNode = i;
                break;
            }
        }

    }

    private void GetLevelList(){
        levelList = new ArrayList<>();

        // 利用队列进行层次遍历
        Queue<Integer> queue = new LinkedList<>();
        // 加入根节点
        queue.add(parentNode);

        while (!queue.isEmpty()){

            // 本层节点数量
            int size = queue.size();

            // 用于临时存储本层节点的List
            List<Integer> nowLevel = new ArrayList<>();

            for(int i=0;i<size;i++){
                int node = queue.poll();
                nowLevel.add(node);

                // 将它的孩子节点全部加入队列
                for(int child : graph[node]){
                    queue.add(child);
                }
            }

            levelList.add(nowLevel);
        }

    }

    /**
     * 从子节点一路向根节点进行推导.
     *
     * 注意这里要一层一层的向上推
     */
    public void Slove(){
        GetLevelList();

        // f[u][0]表示在u节点上不放士兵需要最少人数
        // f[u][1]表示在u节点上放士兵需要最少人数
        int[][] f = new int[N][2];

        // 初始化(其实只需要将叶子节点置1即可)
        for(int node : levelList.get(levelList.size()-1)){
            f[node][1] = 1;

            // 以node为根的树,不在node位置放置时需要的最少士兵数,
            // 这里node是叶子节点,理论上来说,不允许在这里不放士兵
            // 所以也初始化为1
            f[node][0] = 0;
        }

        // 从叶子节点向根节点前进
        for(int i=levelList.size()-2;i>=0;i--){

            // 遍历本层所有节点
            for(int node : levelList.get(i)){

                // 遍历该节点所有子节点
                for(int childNode : graph[node]){

                    // 不选该节点需要放置的最少士兵数
                    f[node][0] += f[childNode][1];

                    // 选择该节点需要放置的最少士兵数
                    f[node][1] += Math.min( f[childNode][0],f[childNode][1] );
                }
                // 因为f[node][1]表示在在当前节点位置放士兵
                // 所以要做+1处理
                f[node][1] += 1;
            }
        }

        int min = Math.min(f[parentNode][1],f[parentNode][0]);

        System.out.println(min);
    }
}
