package szptLesson.higherStruct.UnionAndFindSet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 思路：
 *
 *      1. 并查集得到每个动物的种类。
 *      每次遇到 x吃y 的真话，设置map[fx] = fy，表示x表示的种类吃y表示的种类
 *      当两个集合进行合并的时候，如果两个集合根节点发生变化，查找map是否有旧的根节点，
 *      换上新的根节点
 *
 */
public class P1571食物链 {

    // 父节点数组，father[x]=p表示x节点的父节点是p
    int[] father;

    int N,K;

    // 用来记录食物链关系
    // map[A] = B表示A吃B
    Map<Integer,Integer> map = new HashMap<>();

    // 查集操作，找x的根节点
    public int Find(int x){
        int root = x;
        while (root>=0){
            root = father[root];
        }

        // 路径压缩
        while (root!=x){
            int temp = x;

            // 向上遍历
            x = father[x];

            // 令当前节点指向根节点
            father[temp] = root;
        }

        return root;
    }

    // 并集操作
    public void Union(int x,int y){
        int fx = Find(x);
        int fy = Find(y);

        if(fx==fy)  return;

        // 将节点数小的合并到节点数大的树上
        if(father[fx]>father[fy]){
            // 以fy为根的树的节点数多

            // 更新fy的节点数
            father[fy] += father[fx];

            // 令fx指向fy
            father[fx] = fy;

        }else {
            // 以fx为根的树的节点数多

            // 更新fx的节点数
            father[fx] += father[fy];

            // 令fy指向fx
            father[fy] = fx;
        }
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();

        father = new int[N+1];
        Arrays.fill(father,-1);

        Slove(in);
    }

    // 检测这句话是否是真话，真话返回True，假话返回False
    public boolean Check(int x,int y,int D){
        switch (D){
            case 1:
                // 表示XY是同类

                // 如果xy是同类，那么x表示的种类和y表示的种类就不能
                // 有食物链关系
                // 因此，如果x表示的种类（即x的根节点）和y表示的种类（即y的根节点）
                // 有食物链关系的话，这句话就是假话
                int fx = Find(x);
                int fy = Find(y);

                // 被x表示的种类吃的种类
                int Dx = map.getOrDefault(fx,-1);

                // 被y表示的种类吃的种类
                int Dy = map.getOrDefault(fy,-1);

//                if(){
//
//                }

                break;
        }
        return false;
    }

    public void Slove(Scanner in){

        for(int i=0;i<K;i++){
            int D = in.nextInt();
            int X = in.nextInt();
            int Y = in.nextInt();

            switch (D){
                case 1:
                    // 表示XY是同类
                    break;
                case 2:
                    // 表示X吃Y
                    break;
            }
        }
    }

}
