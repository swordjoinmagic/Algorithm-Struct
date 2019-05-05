package leetcode.Other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 思路：
 *
 *      1. 并查集。将所有朋友关系合并到一个集合中，最后
 *      遍历N，查看有多少个不同的根节点，就是朋友圈总数。
 */
public class Problem547朋友圈 {

    public static void main(String[] args){
        Problem547朋友圈 problem547 = new Problem547朋友圈();
        int[][] m = new int[][]{
                {1,1,0},
                {1,1,1},
                {0,1,1}
        };
        int result = problem547.findCircleNum(m);
        System.out.println(result);
    }

    // 父节点数组，father[x]=p表示x的父节点为p
    private int[] father;

    // 查集操作，找到x的根节点，
    // 并进行路径压缩
    public int Find(int x){
        // 根节点
        int root = x;
        while (father[root]>=0){
            root = father[root];
        }

        // 路径压缩，从x往上，一直到根节点
        // 将途中每一个节点的父节点设置为根节点
        while (x!=root){

            int temp = x;

            // 向上遍历
            x = father[x];

            // 将当前节点指向根节点（路径压缩）
            father[temp] = root;
        }

        return root;
    }

    public void Union(int x,int y){
        int fx = Find(x);
        int fy = Find(y);
        if(fx==fy) return;

        // 根据树的节点进行合并
        // 将节点小的树合并到节点大的树上
        // 当fx为根节点的情况，
        // father[fx]<0时，它的绝对值就是该树的节点数
        if(father[fx]>father[fy]){
            // 以fy为根的树节点多

            // 更新以fy为根的树的节点数
            father[fy] += father[fx];

            // 令节点fx指向根节点fy
            father[fx] = fy;
        }else {
            // 以fx为根的树节点多

            // 更新以fx为根的树的节点数
            father[fx] += father[fy];

            // 令节点fy指向fx
            father[fy] = fx;
        }
    }

    public int findCircleNum(int[][] M) {

        int N = M.length;

        // 初始化父节点数组
        father = new int[N+1];
        Arrays.fill(father,-1);

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(i==j) continue;

                if(M[i][j]==1){
                    Union(i,j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();

        for(int i=0;i<N;i++){
            int fi = Find(i);
            set.add(fi);
        }

        return set.size();
    }
}
