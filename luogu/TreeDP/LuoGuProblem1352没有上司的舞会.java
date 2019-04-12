package luogu.TreeDP;

import java.util.*;

/**
 * 思路:
 *      1.
 *      设 f[i][0]为不选取i节点后得到的最大快乐值
 *      设 f[i][1]为选取i节点后得到的最大快乐值
 *
 *
 *      a. 当不选取i节点时,则i节点的所有下属都可以选,状态转移方程如下:
 *          f[i][0] = sum( f[i.child][1] );
 *       表示的意思是,i节点的最大快乐值由
 *          选择其所有下属的快乐值的总和
 *       构成
 *
 *      b. 当选取i节点时,则i节点的所有下属不能选,
 *          f[i][1] = sum( f[i.child][0] ) + i;
 *       表示的意思是i节点的最大快乐值由
 *          不选它的直接下属的最大快乐值的总和  +  自身的快乐值
 *       组成
 *
 *       根据两个状态转移方程,可以知道,
 *       要从叶子节点向根节点前进来算,
 *       其中叶子节点的f[i][0] = 0;  f[i][1] = A[i];
 *
 *   关键点:
 *      1. 如何获得叶子节点? -> 通过层次遍历
 *      2. 通过存图的方式存树(使用邻接链表存图)
 */
public class LuoGuProblem1352没有上司的舞会 {

    public static void main(String[] args){
        LuoGuProblem1352没有上司的舞会 problem1352 = new LuoGuProblem1352没有上司的舞会();
        problem1352.Input();
        problem1352.Slove();
    }

    // 使用邻接链表存图
    List<Integer>[] graph;
    // 总节点数
    int N;
    // 每个节点的价值
    int[] A;

    // 用于保存层次遍历的结果
    // lists[i]表示第i层的所有元素
    List<List<Integer>> lists = new ArrayList<>();

    // 根节点编号
    int parentNode;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        // 舍弃下标0
        A = new int[N+1];
        graph = new ArrayList[N+1];

        for(int i=1;i<=N;i++) graph[i] = new ArrayList<>();

        for(int i=1;i<=N;i++){
            A[i] = in.nextInt();
        }


        // isParent[i]表示节点是不是根节点,true是,false不是
        boolean[] isParent = new boolean[N+1];
        Arrays.fill(isParent,true);

        while (true){
            int child = in.nextInt();
            int parent = in.nextInt();
            if(child==0 && parent==0) break;
            graph[parent].add(child);
            isParent[child] = false;
        }

        parentNode = FindParent(isParent);
//        System.out.println("根节点:"+parentNode);

        // 先把根节点加入层次遍历列表
        List<Integer> tempList = new ArrayList<>();
        tempList.add(parentNode);
        lists.add(tempList);

        // 获得层次遍历
        GetLevelList();
    }

    public int FindParent(boolean[] isParent){
        for(int i=1;i<=N;i++){
            if(isParent[i])
                return i;
        }
        return -1;
    }


    /**
     * 获得树的层次遍历
     */
    public void GetLevelList(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(parentNode);
        while (!queue.isEmpty()){
            // 本轮层次遍历结果
            List<Integer> tempList = new ArrayList<>();

            int size = queue.size();
            for(int i=0;i<size;i++) {
                // 取出节点
                int node = queue.poll();

                // 将其所有子节点加入队列
                for (int data : graph[node]) {
                    queue.add(data);
                    tempList.add(data);
                }
            }
            if(tempList.size()>0)
                lists.add(tempList);
        }
//        System.out.println(lists);
    }

    public void Slove(){

        // f[i][0]表示不选择i节点获得的最大快乐值,
        // f[i][1]表示选泽该节点时获得的最大快乐值
        int[][] f = new int[N+1][2];

        // 根据层次遍历,从最底层(叶子节点)向最顶层(根节点)进行计算

        // 从最底层开始
        for(int i=lists.size()-1;i>=0;i--){

            // 本层所有节点
            List<Integer> nowLevelList = lists.get(i);

            // 遍历节点
            for(int nowNode : nowLevelList){
                // 遍历当前节点的所有孩子节点
                for(int child : graph[nowNode]){

                    // 计算不选该节点时获得的最大快乐值
                    // f[i][0] = sum( max(  f[i.child][0],f[i.child][1]  ) )
                    f[nowNode][0] += Math.max(f[child][0],f[child][1]);


                    // 计算选该节点时获得的最大快乐值
                    // f[i][1] = sum( f[i.child][1] ) + A[i];
                    f[nowNode][1] += f[child][0];
                }
                f[nowNode][1] += A[nowNode];

//                System.out.format("f[%d][0]:%d  ;  f[%d][1]:%d\n",nowNode,f[nowNode][0],nowNode,f[nowNode][1]);
            }
        }

        int max = Math.max(f[parentNode][0],f[parentNode][1]);
        System.out.println(max);
    }
}
