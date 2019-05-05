package szptLesson.higherStruct.LineTree;

import java.util.Scanner;

/**
 * 区间更新线段树
 *
 * 这里，简单以求和的线段树作为Demo
 */
public class IntervalUpdateLineTree {

    public static void main(String[] args){
        IntervalUpdateLineTree lineTree = new IntervalUpdateLineTree();
        lineTree.Input();
    }


    class TreeNode{

        // 该区间的下限与上限
        int l,r;

        // 该区间保存的和值
        int sum;

        // 该节点在线段树中的标号
        int index;

        // 左孩子右孩子
        TreeNode left,right;

        public TreeNode(int l,int r){
            this.l = l;
            this.r = r;
        }
    }

    // 线段树树根
    private TreeNode root;

    // 学生人数
    private int N;
    // 操作的数目
    private int M;
    // 保存学生成绩
    private int[] scores;

    // lazy标记数组，lazyTag[index]=A表示要将节点标记为index的节点表示的区间都加上A
    private int[] lazyTag;

    /**
     * 初始化线段树
     *
     * @param index 当前节点的标号
     * @param r
     * @param l
     * @return
     */
    private TreeNode MakeLineTree(int l,int r,int index){
        TreeNode node = new TreeNode(l,r);
        node.index = index;

        // 叶子节点
        if(l==r){
            node.sum = scores[l];
            return node;
        }

        int mid = (l+r)/2;

        // 递归建立左子树
        node.left = MakeLineTree(l,mid,index+index);
        // 递归建立右子树
        node.right = MakeLineTree(mid+1,r,index+index+1);

        // 更新当前节点的sum值
        node.sum = node.left.sum + node.right.sum;

        return node;
    }


    /**
     * 将LazyTag向当前节点的左右节点传递
     * @param node
     */
    private void PushDown(TreeNode node){
        if(lazyTag[node.index] > 0){
            int leftLength = 0;
            if(node.left!=null) {

                lazyTag[node.left.index] += lazyTag[node.index];

                // 左子树区间长度
                leftLength = node.left.r - node.left.l + 1;

                // 对sum进行更新
                node.left.sum += leftLength * lazyTag[node.index];
            }

            if(node.right!=null) {
                lazyTag[node.right.index] += lazyTag[node.index];

                // 当前节点区间长度
                int length = node.r - node.l + 1;

                node.right.sum += (length - leftLength) * lazyTag[node.index];
            }
            // 清空当前节点的懒惰标记
            lazyTag[node.index] = 0;
        }
    }

    /**
     * 表示要将线段树中的区间 [l,r] 的值都加上data
     * @param l
     * @param r
     * @param data
     * @param currentNode
     */
    private void Update(int l,int r,int data,TreeNode currentNode){
        if(currentNode==null) return;
        int mid = (currentNode.l+currentNode.r)/2;

        // 如果当前节点的区间符合要更新的区间，那么进行懒惰标记并且直接返回
        if(currentNode.l == l && currentNode.r==r){
            currentNode.sum += data;
            lazyTag[currentNode.index] = data;
            return;
        }

        // 如果当前节点已经被懒惰标记了，那么将LazyTag向他的左右子节点传递
        PushDown(currentNode);

        if(r<=mid){
            // 如果要更新的区间都在左边，那么前往左子树进行更新

            Update(l,r,data,currentNode.left);

        }else if(l>mid){
            // 如果要更新的区间都在右边，那么前往右子树进行更新

            Update(l,r,data,currentNode.right);

        }else{
            // 在中间的情况，往两边更新

            Update(l,mid, data,currentNode.left);
            Update(mid+1,r, data,currentNode.right);

        }

        currentNode.sum = currentNode.left.sum + currentNode.right.sum;
    }


    /**
     * 查询区间[l,r]的总和
     * @param l
     * @param r
     * @param node
     */
    public int Query(int l,int r,TreeNode node){

        if(l<=node.l && r>=node.r)
            return node.sum;

        int mid = (node.l+node.r)/2;

        if(r<=mid){
            // 如果要更新的区间都在左边，那么前往左子树进行更新
            return Query(l,r,node.left);
        }else if(l>mid){
            // 如果要更新的区间都在右边，那么前往右子树进行更新
            return Query(l,r,node.right);
        }else{
            // 在中间的情况，往两边更新
            return Query(l,mid, node.left) + Query(mid+1,r,node.right);
        }
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            N = in.nextInt();
            M = in.nextInt();
            scores = new int[N];

            for (int i = 0; i < N; i++) {
                scores[i] = in.nextInt();
            }

            // 初始化线段树
            root = MakeLineTree(0, N - 1,1);
            lazyTag = new int[N*4+1];

            Slove(in);
        }
    }

    public void Slove(Scanner in){
        while (M-->0){
            int Q = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();

            switch (Q){
                case 0:
                    int data = in.nextInt();
                    // 更新
                    Update(a,b,data,root);
                    break;
                case 1:
                    // 查询
                    System.out.println(Query(a,b,root));
                    break;
            }
        }
    }
}
