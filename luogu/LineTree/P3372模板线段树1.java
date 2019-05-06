package luogu.LineTree;

import java.util.Scanner;

/**
 * 思路:
 *      1. 线段树模板题
 *
 *      线段树主要对应2个操作：
 *
 *          a. 区间更新
 *          b. 区间查询
 *
 *       线段树原理是将一组数据划分为N个区间，比如有
 *       1 2 3 4 5 6 7 8 9 10
 *       共10个数据，那么使用线段树，就可以组织成以下形式
 *
 *                  [ 1 - 10]
 *                  /        \
 *               [1-5]       [6-10]
 *              /   \        /    \
 *            [1-2] [3-5] [6-8]  [9-10]
 *            ........................
 *
 *       当进行区间查询操作时，判断要查询的区间所在的位置，
 *       然后一段一段的区间查询，查询的时间复杂度取决于树的高度，
 *       比正常的遍历查找求和速度更快。
 *
 *       当进行区间更新操作时，此时要注意的是，区间更新，不用进入
 *       每个独立的子区间更新，举个例子，
 *       更新区间 [2 - 5] 内的值全部加上10，
 *       这里先使用查询区间的方法找到区间 [2 - 5]
 *       （算法中是将该区间划分为[2-2]和[3-5]来计算），
 *       找到区间后，直接对该区间[2 - 5]加上10，不用继续进入其子区间更新，
 *       这里使用一个懒惰标记LazyTag来标记，标记区间[2 - 5]加上了10，
 *
 *       此时当后续操作要查询或更新区间[2 - 5]及其子区间时，
 *       将该区间的懒惰标记向其子区间传递，同时子区间更新。
 */
public class P3372模板线段树1 {

    public static void main(String[] args){
        P3372模板线段树1 p3372 = new P3372模板线段树1();
        p3372.Input();
    }

    class Node{
        // 当前区间的左边界和右边界
        int l,r;
        // 当前区间的每个数的和
        long sum;
        public Node(int l,int r){
            this.l = l;
            this.r = r;
        }
    }

    // 用数组模拟树，设n为当前节点，
    // 则n*n为左子树，n*n+1为右子树，
    // 舍弃下标0
    private Node[] lineTree;

    // 懒惰标记
    private long[] lazyTag;

    // 数字个数
    int N;
    // 操作总数
    int M;

    long[] data;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();

        data = new long[N];
        lineTree = new Node[N*10];
        lazyTag = new long[N*10];

        for(int i=0;i<N;i++){
            data[i] = in.nextLong();
        }

        MakeLineTree(0,N-1,1);

        Slove(in);
    }

    public void Slove(Scanner in){
        for(int i=0;i<M;i++){
            int command = in.nextInt();
            int x = in.nextInt()-1;
            int y = in.nextInt()-1;
            switch (command){
                case 1:
                    int k = in.nextInt();
                    Update(x,y,k,1);
                    break;
                case 2:
                    long sum = Find(x,y,1);
                    System.out.println(sum);
                    break;
            }
        }
    }

    /**
     * 区间查询操作，查询区间[l,r]的和
     * @param l
     * @param r
     * @param index 表示当前区间的下标
     * @return
     */
    public long Find(int l,int r,int index){
        Node node = lineTree[index];
        int mid = (node.l+node.r)/2;

        // 检查并传递懒惰标记
        PushDown(index);

        // 当前区间是要查询的区间的子区间时，直接返回
        if(node.l>=l && node.r<=r){
            return node.sum;
        }

        // 判断要查询的区间在当前区间的左边还是右边
        if(r<=mid){
            // 左边
            return Find(l,r,index+index);
        }else if(l>mid){
            // 右边
            return Find(l,r,index+index+1);
        }else {
            // 中间

            // 左子树
            long leftSum = Find(l,mid,index+index);

            // 右子树
            long rightSum = Find(mid+1, r, index+index+1);

            return leftSum+rightSum;
        }
    }


    /**
     * 向下传递懒惰标记
     * @param index
     */
    public void PushDown(int index){
        Node node = lineTree[index];
        long value = lazyTag[index];

        // 如果存在懒惰标记,那么向下传递,传给他的左右子树
        if(value>0){

            // 左右子树节点
            Node leftNode = lineTree[index+index];
            Node rightNode = lineTree[index+index+1];

            if(leftNode!=null) {
                // 左子树区间数据的数量
                int leftSize = leftNode.r - leftNode.l + 1;
                // 更新左子树区间的Sum值
                leftNode.sum += value * leftSize;
                // 懒惰标记传递
                lazyTag[index + index] += value;
            }

            if(rightNode!=null){
                // 右子树区间数据的数量
                int rightSize = rightNode.r-rightNode.l+1;
                // 更新右子树区间的Sum值
                rightNode.sum += rightSize*value;
                // 懒惰标记传递
                lazyTag[index+index+1] += value;
            }

            // 清除当前节点的懒惰标记
            lazyTag[index] = 0;
        }
    }

    /**
     * 区间更新,将区间 [l,r] 上每一个数都加上value
     * @param l
     * @param r
     * @param index
     */
    public void Update(int l,int r,int value,int index){
        Node node = lineTree[index];
        int mid = (node.r + node.l)/2;

        // 如果当前区间是要更新的区间时,进行懒惰标记,
        // 并直接返回(不继续进入更新)
        if(node.l>=l && node.r<=r){
            // 这个区间数据的数量
            int size = node.r-node.l+1;

            // 更新当前区间的Sum值
            node.sum += size*value;

            // 进行懒惰标记
            lazyTag[index] += value;

            return;
        }

        // 检查并传递懒惰标记
        PushDown(index);

        // 判断要更新的区间在当前区间的左边还是右边
        if(r<=mid){
            // 左边
            Update(l,r,value,index+index);
        }else if(l>mid){
            // 右边
            Update(l,r,value,index+index+1);
        }else {
            // 中间
            // 左子树
            Update(l,mid,value,index+index);
            // 右子树
            Update(mid+1,r, value,index+index+1);
        }

        // 更新当前区间的sum值
        node.sum = lineTree[index+index].sum + lineTree[index+index+1].sum;
    }

    /**
     * 根据一段数据进行建树操作
     */
    public void MakeLineTree(int l,int r,int index){
        if(l==r){
            Node node = new Node(l,r);
            node.sum = data[l];
            lineTree[index] = node;
            return;
        }

        int mid = (l+r)/2;

        Node node = new Node(l,r);

        // 左子树
        MakeLineTree(l,mid,index+index);
        // 右子树
        MakeLineTree(mid+1,r,index+index+1);

        node.sum = lineTree[index+index].sum +
                   lineTree[index+index+1].sum;

        lineTree[index] = node;
    }
}
