package szptLesson.higherStruct.LineTree;

import java.util.Scanner;

public class P1382 {

    public static void main(String[] args){
        P1382 p1382 = new P1382();
        p1382.Input();
    }

    class TreeNode{

        // 该区间的下限与上限
        int l,r;

        // 该区间保存的最大值
        int max;;

        // 该区间保存的权值和
        int sum;

        // 左孩子右孩子
        TreeNode left,right;

        public TreeNode(int l,int r){
            this.l = l;
            this.r = r;
        }
    }

    // 线段树树根
    private TreeNode root;

    // 格子数
    private int N;
    // 操作的数目M次
    private int M;
    // 保存格子权值
    private int[] scores;

    /**
     * 初始化线段树,区间[l,r]，包含r
     */
    public TreeNode MakeLineTree(int l,int r){
        TreeNode node = new TreeNode(l,r);
        if(l==r){
            node.max = scores[l];
            node.sum = scores[l];
            return node;
        }

        int mid = (l+r)/2;

        node.left = MakeLineTree(l,mid);
        node.right = MakeLineTree(mid+1,r);

        // 更新最大值
        node.max = Math.max(node.left.max,node.right.max);

        // 更新权值和
        node.sum = node.left.sum + node.right.sum;

        return node;
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        scores = new int[N];

        for (int i = 0; i < N; i++) {
            scores[i] = in.nextInt();
        }

        // 初始化线段树
        root = MakeLineTree(0, N - 1);

        Slove(in);
    }


    /**
     * 更新区间内下标为index的数为Value
     * @param index
     * @param value
     * @param currentNode
     */
    public void Update(int index,int value,TreeNode currentNode){

        if(index==currentNode.l && index==currentNode.r){
            currentNode.max = value;
            currentNode.sum = value;
            return;
        }

        int mid = (currentNode.l+currentNode.r)/2;

        // 判断该数在左区间还是右区间
        if(index<=mid){
            // 进入左区间继续更新
            Update(index,value,currentNode.left);

        }else if(index>mid){

            // 进入右区间进行更新
            Update(index,value,currentNode.right);
        }

        currentNode.max = Math.max(currentNode.left.max,currentNode.right.max);
        currentNode.sum = currentNode.left.sum + currentNode.right.sum;
    }

    /**
     * 查询区间 [l,r] 内的最大值
     * @param l
     * @param r
     * @return
     */
    public int QueryMax(int l,int r,TreeNode currentNode){

        // 当前区间是要查询的区间的子区间
        if(l<=currentNode.l && r>=currentNode.r)
            return currentNode.max;

        int mid = (currentNode.l+currentNode.r)/2;

        if(r<=mid){
            // 查询区间在当前区段的左区间

            // 进入左区间进行查找
            return QueryMax(l,r,currentNode.left);

        }else if(l>mid){
            // 查询区间在当前区段的右区间

            // 进入右区间查找
            return QueryMax(l,r, currentNode.right);

        }else {
            // 查询区间在当前区段的中间
            return Math.max(QueryMax(l,mid,currentNode.left),QueryMax(mid+1,r,currentNode.right));
        }
    }

    public int QuerySum(int l,int r,TreeNode currentNode){
        // 当前区间是要查询的区间的子区间
        if(l<=currentNode.l && r>=currentNode.r)
            return currentNode.sum;

        int mid = (currentNode.l+currentNode.r)/2;

        if(r<=mid){
            // 查询区间在当前区段的左区间

            // 进入左区间进行查找
            return QuerySum(l,r,currentNode.left);

        }else if(l>mid){
            // 查询区间在当前区段的右区间

            // 进入右区间查找
            return QuerySum(l,r, currentNode.right);

        }else {
            // 查询区间在当前区段的中间
            return QuerySum(l,mid,currentNode.left)+QuerySum(mid+1,r,currentNode.right);
        }
    }

    public void Slove(Scanner in){


        for(int i=0;i<M;i++){

            int p = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();

            switch (p){
                case 1:
                    // 更新操作
                    x--;
                    Update(x,y,root);
                    break;
                case 2:
                    // 求权值和
                    x--;y--;
                    System.out.println(QuerySum(x,y,root));
                    break;
                case 3:
                    // 求最大值
                    x--;y--;
                    System.out.println(QueryMax(x,y,root));
                    break;

            }
        }
    }
}
