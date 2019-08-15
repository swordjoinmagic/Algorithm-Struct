package PAT.GradeAOfficial.test1;

import java.util.*;

/**
 * 题意:
 *      1. 给定N个节点,M个非叶子节点,
 *      在下面M行中,每行给出一个非叶子节点及其孩子节点,格式如下:
 *      ID K ID[1] ID[2] ... ID[K]
 *      求从根节点出发(ID="01"),树的每一层的叶子节点个数
 *
 * 思路:
 *      1. 用Map模拟.最坏情况需要遍历所有节点.
 */
public class PAT1004CountingLeaves_30point {

    public static void main(String[] args){
        PAT1004CountingLeaves_30point pat1004 = new PAT1004CountingLeaves_30point();
        pat1004.Input();
    }

    int N,M;

    Map<String, List<String>> tree;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();

        tree = new HashMap<>();

        for(int i=0;i<M;i++){
            String id = in.next();
            if(!tree.containsKey(id)) tree.put(id,new ArrayList<>());

            int k = in.nextInt();
            for(int j=0;j<k;j++){
                String child = in.next();
                tree.get(id).add(child);
            }
        }
        Slove();
    }

    /**
     * 判断某节点是否是叶子节点
     * @param id
     * @return
     */
    public boolean isLeaveNode(String id){
        return !tree.containsKey(id) || tree.get(id).size()==0;
    }

    public void Slove(){

        List<Integer> list = new ArrayList<>();

        // 层次遍历树
        Queue<String> queue = new LinkedList<>();
        queue.add("01");

        while (!queue.isEmpty()){

            // 本层节点数量
            int size = queue.size();
            // 本层叶子节点数量
            int leaveNodeCount = 0;

            // 遍历本层,并计算叶子节点数量
            for(int i=0;i<size;i++){
                String nodeID = queue.poll();

                if(isLeaveNode(nodeID)) leaveNodeCount++;
                else queue.addAll(tree.get(nodeID));
            }

//            System.out.print(leaveNodeCount+" ");
            list.add(leaveNodeCount);
        }

        for(int i=0;i<list.size();i++){
            if(i!=list.size()-1)
                System.out.print(list.get(i)+" ");
            else
                System.out.print(list.get(i));
        }
    }
}
