package luogu.LineTree;

import java.util.Scanner;

/**
 * 思路：
 *      1. 线段树，用数组存树
 */
public class P2023维护序列 {

    public static void main(String[] args){
        P2023维护序列 p2023 = new P2023维护序列();
        p2023.Input();
    }

    class TreeNode{
        // 表示左区间右区间
        int l,r;
        // 表示区间和
        int sum;
        public TreeNode(int l,int r){this.l = l;this.r=r;}
    }

    // 一共N个数
    private int N;
    private int P;

    private int[] A;

    // 操作总数
    private int M;

    // 用数组建线段树，左子树节点为当前节点*2，右子树节点为当前节点*2+1
    // 舍弃下标0
    private TreeNode[] root;

    // 懒惰标记，lazyTag[index]=A 表示
    // 要将节点标记为index的节点表示的区间都加上A
    private int[] lazyTag;

    /**
     * 查找区间[l,r]的和值
     * @param l
     * @param r
     * @param nodeIndex
     * @return
     */
    public int Query(int l,int r,int nodeIndex){
        TreeNode node = root[nodeIndex];

        // 当前节点区间是要查找区间的子区间
        if(l<=node.l && r>=node.r){
            return node.sum;
        }

        int mid = (node.l+node.r)/2;

        // 如果当前节点有懒惰标记,向下传
        PushDown(nodeIndex);

        if(r<=mid){
            // 要查找区间在当前节点区间的左边

            return Query(l,r,nodeIndex+nodeIndex);

        }else if(l>mid){
            // 要查找区间在当前节点区间的右边

            return Query(l,r,nodeIndex+nodeIndex+1);

        }else {
            // 要查找区间在当前节点区间的中间

            return Query(l,mid,nodeIndex+nodeIndex) +
                   Query(mid+1,r,nodeIndex+nodeIndex+1);
        }


    }

    /**
     * 将节点下标为nodeIndex的节点 的 懒惰标记 向他的子节点传递
     * @param nodeIndex
     */
    public void PushDown(int nodeIndex){
        // 如果当前节点有懒惰标记,向他的子节点传递
        if(lazyTag[nodeIndex]!=0){

            int data = lazyTag[nodeIndex];
            int left = nodeIndex+nodeIndex;
            int right = nodeIndex+nodeIndex+1;

            // 懒惰标记传递
            lazyTag[left] += data;
            lazyTag[right] += data;

            // 更新值
            root[left].sum += (root[left].r-root[left].l+1)*data;
            root[right].sum += (root[right].r-root[right].l+1)*data;

            // 将当前节点的懒惰标记清空
            lazyTag[nodeIndex] = 0;
        }
    }

    /**
     * 将区间 [l,r] 内的所有值加上 data
     * @param l
     * @param r
     * @param nodeIndex
     */
    public void Update(int l, int r, int data, int nodeIndex,boolean isAdd){

        TreeNode node = root[nodeIndex];

        // 如果当前节点的区间就是要更新的区间，
        // 做懒惰标记，并返回
        if(node.l == l && node.r == r){

            // 做懒惰标记
            lazyTag[nodeIndex] += data;

            // 区间长度
            int length = r-l+1;

            // 加上 区间长度 * data
            node.sum += length*data;

            return;
        }

        // 如果更新到当前节点，并且当前节点是具有懒惰标记的
        // 将标记传给他的子节点
        PushDown(nodeIndex);

        // 当前节点区间中值
        int mid = (node.l+node.r)/2;


        if(r<=mid){
            // 要更新的区间在当前节点的左区间

            Update(l,r,data,nodeIndex+nodeIndex,isAdd);

        }else if(l>mid){
            // 要更新的区间在当前节点的右区间

            Update(l,r,data,nodeIndex+nodeIndex+1,isAdd);

        }else {
            // 要更新的区间在当前节点的中间

            // 左区间更新
            Update(l,mid,data,nodeIndex+nodeIndex,isAdd);
            // 右区间更新
            Update(mid+1,r, data,nodeIndex+nodeIndex+1,isAdd);
        }

        // 更新当前节点的和值
        node.sum = root[nodeIndex+nodeIndex].sum + root[nodeIndex+nodeIndex+1].sum;
    }

    /**
     * 创建区间为【0,N】的线段树节点
     * @param l
     * @param r
     * @param index 当前节点在数组的下标
     * @return
     */
    public void MakeLineTree(int l,int r,int index){
        TreeNode node = new TreeNode(l,r);
        root[index] = node;

        // 叶子节点
        if(l==r){
            node.sum = A[l];
            return;
        }

        int mid = (l+r)/2;

        // 左子树和右子树
        MakeLineTree(l,mid,index+index);
        MakeLineTree(mid+1,r,index+index+1);

        root[index].sum = (root[index+index].sum + root[index+index+1].sum) % P;
    }

    public void Input(){
        Scanner in = new Scanner(System.in);

        N = in.nextInt();
        P = in.nextInt();

        A = new int[N];

        for(int i=0;i<N;i++){
            A[i] = in.nextInt();
        }

        root = new TreeNode[N*4];
        lazyTag = new int[N*4];
        MakeLineTree(0,N-1,1);

        Slove(in);
    }

    public void Slove(Scanner in){
        M = in.nextInt();
        while (M-->0){

            int Q = in.nextInt();
            int t = in.nextInt();
            int g = in.nextInt();

            t--;
            g--;

            switch (Q){
                case 1:
                    // 区间更新，区间内所有数乘上一个数
                    int c = in.nextInt();

                    Update(t,g,c,1,false);

                    break;
                case 2:
                    // 区间更新，区间内所有数加上一个数
                    c = in.nextInt();

                    Update(t,g,c,1,true);

                    break;
                case 3:
                    // 区间查询，查找区间[t,g]内的数组的和值
                    System.out.println(Query(t,g,1));

                    break;
            }
        }
    }

}
