package szptLesson.higherStruct.LineTree;

import java.io.*;
import java.util.Timer;

/**
 * 思路:
 *      1. 线段树模板.
 */
public class ProblemD炒股王面试 {

    public String ID = "16240011";
    public String Problem = "D";

    // 优化输入
    class Scanner{

        StreamTokenizer in;

        public Scanner(InputStream inputStream){
            in = new StreamTokenizer(new BufferedReader(new InputStreamReader(inputStream)));
        }

        public int nextInt() throws IOException {
            in.nextToken();
            return (int) in.nval;
        }
    }

    public static void main(String[] args) throws IOException {
        long time = System.currentTimeMillis();
        ProblemD炒股王面试 problemD = new ProblemD炒股王面试();
        problemD.Input();
        System.out.println((System.currentTimeMillis()-time)+"ms");

    }

    class TreeNode{
        // 表示区间[l,r]
        int l,r;
        // 表示区间[l,r]的最高成交量
        int max;
        // 表示区间[l,r]的总成交量
        int sum;

        // 左孩子和右孩子
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int l,int r){
            this.l = l;
            this.r = r;
        }
    }

    // 天数
    int N;
    // 查询次数
    int M;

    int[] array;

    public void Input() throws IOException {
        System.setIn(new FileInputStream("C:\\Users\\Administrator\\Downloads\\testdata\\D\\7in.txt"));
//        System.setOut(new PrintStream("C:\\Users\\Administrator\\Desktop\\testData2.txt"));
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();

        array = new int[N];

        for(int i=0;i<N;i++){
            array[i] = in.nextInt();
        }

        Slove(in);
    }

    /**
     * 建立线段树
     * @param l
     * @param r
     * @return
     */
    public TreeNode BuildLineTree(int l,int r){
        TreeNode node = new TreeNode(l,r);
        // 叶子节点
        if(l==r){
            node.sum = array[l];
            node.max = array[l];
            return node;
        }

        int mid = (l+r)/2;

        // 建立左子树
        node.leftChild = BuildLineTree(l,mid);
        // 建立右子树
        node.rightChild = BuildLineTree(mid+1,r);

        node.sum = node.leftChild.sum+node.rightChild.sum;
        node.max = Math.max(node.leftChild.max,node.rightChild.max);
        return node;
    }

    /**
     * 查询区间[l,r]的总和
     * @param node 当前区间
     * @param l
     * @param r
     * @return
     */
    public int QuerySum(TreeNode node,int l,int r){
        int mid = (node.l+ node.r)/2;

        if(node.l>=l && node.r<=r){
            return node.sum;
        }

        if(r<=mid){
            // 要查询的区间在当前区间的左边
            return QuerySum(node.leftChild,l,r);
        }else if(l>mid){
            // 要查询的区间在当前区间的右边
            return QuerySum(node.rightChild,l,r);
        }else {
            // 要查询的区间在当前区间的中间
            int leftSum = QuerySum(node.leftChild,l,mid);
            int rightSum = QuerySum(node.rightChild,mid+1,r);

            return leftSum+rightSum;
        }
    }

    /**
     * 查询区间[l,r]的最大值
     * @param node 当前区间
     * @param l
     * @param r
     * @return
     */
    public int QueryMax(TreeNode node,int l,int r){
        int mid = (node.l+ node.r)/2;

        if(node.l>=l && node.r<=r){
            return node.max;
        }

        if(r<=mid){
            // 要查询的区间在当前区间的左边
            return QueryMax(node.leftChild,l,r);
        }else if(l>mid){
            // 要查询的区间在当前区间的右边
            return QueryMax(node.rightChild,l,r);
        }else {
            // 要查询的区间在当前区间的中间
            int leftSum = QueryMax(node.leftChild,l,mid);
            int rightSum = QueryMax(node.rightChild,mid+1,r);

            return Math.max(leftSum,rightSum);
        }
    }

    public void Slove(Scanner in) throws IOException {

        // 建立线段树
        TreeNode root = BuildLineTree(0,N-1);

        for(int i=0;i<M;i++){
            int command = in.nextInt();
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            switch (command){
                case 1:
                    // 查最大值
                    int max = QueryMax(root,a,b);
                    System.out.println(max);
                    break;
                case 2:
                    // 查和值
                    int sum = QuerySum(root,a,b);
                    System.out.println(sum);
                    break;
            }
        }
    }
}
