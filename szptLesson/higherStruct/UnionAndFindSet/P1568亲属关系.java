package szptLesson.higherStruct.UnionAndFindSet;

import java.util.Arrays;
import java.util.Scanner;

public class P1568亲属关系 {

    public static void main(String[] args){
        P1568亲属关系 p1568 = new P1568亲属关系();
        p1568.Input();
    }

    // 父节点数组,father[x] = p,表示x的父节点是p
    int[] father;

    // 高度数组,rank[x]=i表示以x为根的树的高度
    int[] rank;


    // n个人
    int n;

    public void Input(){
        Scanner in = new Scanner(System.in);

        n = in.nextInt();

        // 初始化父节点数组
        father = new int[n+1];
        for(int i=0;i<=n;i++) father[i] = i;
        // 初始化高度数组
        rank = new int[n+1];
        Arrays.fill(rank,1);

        Slove(in);
    }

    // 并集操作,返回x节点的根节点
    public int Find(int x){
        if(father[x]!=x){
            return Find(father[x]);
        }
        return x;
    }

    // 并集操作,将x节点和y节点合并到同一个集合
    public void Union(int x,int y){
        // 找到x和y的根节点
        int fx = Find(x);
        int fy = Find(y);

        // 说明x节点和y节点已经在同一棵树上了
        if(fx==fy) return;

        // 为了降低树的高度,将高度小的树合并到高度大的树上
        if(rank[fx]<rank[fy]){
            // 以fx为根的树高度较小
            father[fx] = fy;
        }else {
            if(rank[fy]==rank[fx]){
                // 以fy为根的树高度+1
                rank[fx]++;
            }
            father[fy] = fx;
        }
    }

    public void Slove(Scanner in){
        // m个亲戚关系
        int m = in.nextInt();

        // 询问p对亲戚关系
        int p = in.nextInt();

        for(int i=0;i<m;i++){
            int mi = in.nextInt();
            int mj = in.nextInt();

            Union(mi,mj);
        }

        while (p-->0){
            int ai = in.nextInt();
            int bi = in.nextInt();

            int fx = Find(ai);
            int fy = Find(bi);

            System.out.println(fx==fy);
        }
    }
}
