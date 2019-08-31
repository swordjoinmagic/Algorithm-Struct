package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 给定一棵树,每个节点上都有一个权值.
 *      给定一个权值S,求哪些路径[L,R]上的总权值==S
 *      PS:
 *          1. 按照题目描述,路径一定是一条从根节点到达叶子节点的路径
 *          2. 最后按降序输出路径
 * 思路:
 *      1. DFS.从某个节点开始DFS,如果在DFS过程中得到的权值==S,那么就记录该路径.
 *      总共就100个节点,可以考虑用数组存树.
 */
public class PAT1053PathofEqualWeight_30point {

    public static void main(String[] args){
        PAT1053PathofEqualWeight_30point pat1053 = new PAT1053PathofEqualWeight_30point();
        pat1053.Input();
    }

    class TreeNode{
        int id;
        int weight;
        int[] childs;

        public TreeNode(int id,int weight) {
            this.id  = id;
            this.weight = weight;
        }

        public String toString(){
            return String.format("{%d:%d}",id,weight);
        }
    }

    int[] parent;
    TreeNode[] nodes;
    int N,M,S;

    List<String> list = new ArrayList<>();

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        S = in.nextInt();

        nodes = new TreeNode[N];
        parent = new int[N];
        parent[0] = -1;

        for(int i=0;i<N;i++) nodes[i] = new TreeNode(i,in.nextInt());

        for(int i=0;i<M;i++){
            int id = in.nextInt();
            int k = in.nextInt();

            TreeNode node = nodes[id];
            node.childs = new int[k];

            for(int j=0;j<k;j++){
                node.childs[j] = in.nextInt();
            }
        }
        Slove();
    }

    public void Slove(){
        DFS(0,nodes[0].weight);

        Collections.sort(list,new Comparator<String>(){
            @Override
            public int compare(String o1,String o2){
                String[] o1s = o1.split(" ");
                String[] o2s = o2.split(" ");
                int minLength = Math.min(o1s.length,o2s.length);
                for(int i=0;i<minLength;i++){
                    int a = Integer.parseInt(o1s[i]);
                    int b = Integer.parseInt(o2s[i]);
                    if(a>b)
                        return -1;
                    else if(a<b)
                        return 1;
                }
                if(o1s.length > minLength)
                    return -1;
                else if(o1s.length < minLength)
                    return 1;
                else
                    return 0;
            }
        });

        for(String str : list) System.out.println(str);
    }

    public String getPath(int nowNodeID){
        String result = String.valueOf(nodes[nowNodeID].weight);

        int temp = nowNodeID;
        while (parent[temp]!=-1){
            int id = parent[temp];
            result = nodes[id].weight +" "+ result;
            temp = parent[temp];
        }

        return result;
    }

    public void DFS(int nowNodeID,int nowWeight){
        // 当前权重大于需要找的S,直接返回
        if(nowWeight>S) return;

        TreeNode node = nodes[nowNodeID];

        if(nowWeight==S && node.childs==null){
            // 记录路径

            String path = getPath(nowNodeID);
            list.add(path);

            return;
        }

        // 遍历子节点
        if(node.childs!=null)
        for(int child : node.childs){
            parent[child] = nowNodeID;
            DFS(child,nowWeight+nodes[child].weight);
            parent[child] = -1;
        }
    }
}
