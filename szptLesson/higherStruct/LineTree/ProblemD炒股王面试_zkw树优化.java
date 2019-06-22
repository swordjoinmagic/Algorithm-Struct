package szptLesson.higherStruct.LineTree;

import java.io.*;
import java.util.Arrays;

public class ProblemD炒股王面试_zkw树优化 {
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
        ProblemD炒股王面试_zkw树优化 problemD = new ProblemD炒股王面试_zkw树优化();
        problemD.Input();
    }

    class TreeNode{
        // 表示区间[l,r]的最高成交量
        int max;
        // 表示区间[l,r]的总成交量
        int sum;
    }

    // 天数
    int N;
    // 查询次数
    int M;

    TreeNode[] tree;

    // 叶子节点总数
    int leaveN = 1;

    public void Input() throws IOException {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();

        for(;leaveN<=N+1;leaveN <<= 1);

        tree = new TreeNode[leaveN<<1];
        Arrays.fill(tree,new TreeNode());

        for(int i=leaveN+1;i<=leaveN+N;i++){
            int number = in.nextInt();
            tree[i] = new TreeNode();
            tree[i].sum = number;
            tree[i].max = number;
        }

        // 建树
        for(int i=leaveN-1;i>=1;i--){
            tree[i] = new TreeNode();
            tree[i].sum = tree[i<<1].sum+tree[i<<1|1].sum;
            tree[i].max = Math.max(tree[i<<1].max,tree[i<<1|1].max);
        }

        Slove(in);
    }

    public long QuerySum(int s,int t){
        int ans = 0;
        for(s=leaveN+s-1,t=leaveN+t+1;(s^t^1) != 0;s>>=1,t>>=1){
            if((~s&1)!=0) ans += tree[s^1].sum;
            if((t&1)!=0) ans += tree[t^1].sum;
        }
        return ans;
    }
    public long QueryMax(int s,int t){
        int ans = 0;
        for(s=leaveN+s-1,t=leaveN+t+1;(s^t^1) != 0;s>>=1,t>>=1){
            if((~s&1)!=0) ans = Math.max(ans,tree[s^1].max);
            if((t&1)!=0) ans = Math.max(ans,tree[t^1].max);
        }
        return ans;
    }

    public void Slove(Scanner in) throws IOException {

        // 建立线段树

        for(int i=0;i<M;i++){
            int command = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            switch (command){
                case 1:
                    // 查最大值
                    System.out.println(QueryMax(a,b));
                    break;
                case 2:
                    // 查和值
                    System.out.println(QuerySum(a,b));

                    break;
            }
        }
    }
}
