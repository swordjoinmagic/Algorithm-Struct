package szptLesson.DP;

import java.io.*;

/**
 *
 * 思路：
 *      1. 设 f[i][j] 为拦截前i个碎片，使用j张网时的最小污染度
 *
 *      则有状态转移方程如下：
 *
 *      f[i][j] = min{ f[k][j-1] + p(k+1,i) }
 *      其中（1<= k <i）,p(k+1,i)表示区间[k+1,i]的污染度
 *
 *      该状态转移方程表示的意思是:
 *
 *      拦截前i个碎片使用j张网时的最小污染度 =
 *      min{
 *          拦截前1/2/3/4/../i-1个碎片时使用j-1张网的最小污染度 +
 *          从 放网的那个地方(也就是1/2/3/4/../i-1) 开始,到结束位置
 *          时的污染度
 *      }
 *
 *      更详细的解释是:
 *      要算 拦截前i个碎片,使用j张网时的 最小污染度, 可以利用之前已经算过的
 *      拦截前 [1,i-1]个碎片,使用j-1张网时的 最小污染度 .
 *
 *      时间复杂度O(n2)
 */
public class ProblemB争分夺秒 {

    public String ID = "16240011";
    public String Problem = "B";

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
        ProblemB争分夺秒 problemB = new ProblemB争分夺秒();
        problemB.Input();
    }

    // 碎片个数
    int n;
    // 网的数量
    int m;

    // 每个碎片的重量
    int[] weight;
    // 每个碎片的起始位移
    int[] distance;

    public void Input() throws IOException {
        Scanner in = new Scanner(System.in);
        while (true) {
            n = in.nextInt();
            m = in.nextInt();

            // 舍弃下标0
            weight = new int[n+1];
            distance = new int[n+1];

            if(n==0 && m==0) break;

            for(int i=1;i<=n;i++){
                distance[i] = in.nextInt();
                weight[i] = in.nextInt();
            }

            Slove();
        }
    }

    public void Slove(){
        // f[i][j] 表示拦截前i个碎片,使用j张网时的最小污染度
        int[][] f = new int[n+1][m+1];

        f[1][1] = 0;

        // 初始化f,即拦截前n个碎片,使用1张网时的最小污染度
        for(int i=2;i<=n;i++){
            // 初始化
            f[i][1] = 0;
            for(int k=1;k<i;k++){
                f[i][1] += (distance[i]-distance[k])*weight[k];
            }
        }

        // 根据状态转移方程f[i][j] = min{ f[k][j-1] + p(k+1,i) }
        // 其中（1<= k <i）,p(k+1,i)表示区间[k+1,i]的污染度
        // 进行递推
        for(int i=2;i<=n;i++){
            for(int j=2;j<=m;j++){

                // 初始化f[i][j]
                f[i][j] = Integer.MAX_VALUE;

                // 之前区域的污染度
                // 这里让k从后往前放网,这样的话,每次计算p(k+1,i)即区间[k+1,i]的污染度
                // 就可以利用之前算好的污染度来算
                int polluteDegreePre = 0;
                for(int k=i-1;k>=1;k--){
                    // 这里表示尝试从k处放网

                    // 如果在k处放网,那么污染度就被分为两部分,
                    // 一个是[1,k-1]处的最小污染度,一个是[k+1,i]的污染度,
                    // [1,k-1]的污染度前面已经重复计算,这里主要计算的是[k+1,i]的污染度
                    // 这里将k从后往前,即可利用之前算过的[k+1,i]的污染度,这样只要算
                    // 位置k+1的碎片产生的污染度就OK了
                    int polluteDegree = (distance[i]-distance[k+1])*weight[k+1] + polluteDegreePre;
                    polluteDegreePre = polluteDegree;
                    f[i][j] = Math.min(f[i][j],f[k][j-1]+polluteDegree);
                }

            }
        }

        System.out.println(f[n][m]);
    }
}
