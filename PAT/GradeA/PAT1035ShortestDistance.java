package PAT.GradeA;

import java.util.Scanner;

/**
 * 题意:
 *      1. 给出N条路径,
 *      每条路径Di表示第i个节点和第i+1个节点之间的路径,
 *      特别的,DN表示第N个节点和第I个节点之间的路径,
 *      询问M次,求X和Y的最短路径是多少
 *
 * 思路:
 *      1. 和广告印刷思路类似.
 *
 *      求出每一个节点到达左端点的路径总和以及到达右端点的路径总和.
 *      这里用 leftSum[x]和rightSum[x]表示,
 *      leftSum[x]表示x节点距离左端点的总路径,
 *      rightSum[x]表示x节点距离右端点的总路径.
 *
 *      对于询问节点i和节点j之间的最短路径时,
 *      这里有个规定,即i一定是小于j的(这个很好处理,比较后交换即可)
 *      从左往右寻路的路径是:
 *          rightSum[i]-rightSum[j]
 *      从右往左寻路的路径是:
 *          leftSum[i]+rightSum[j]+distance[N-1]
 *
 */
public class PAT1035ShortestDistance {

    public static void main(String[] args){
        PAT1035ShortestDistance pat1035 = new PAT1035ShortestDistance();
        pat1035.Input();
    }

    // 节点数
    int N;
    // 询问次数
    int M;

    // 路径
    int[] distances;

    // 节点x左端点总和
    int[] leftSum;
    // 节点x右端点总和
    int[] rightSum;

    private void CalculateSum(){
        leftSum = new int[N];
        rightSum = new int[N];

        // 计算节点x距离左端点的和值
        for(int i=1;i<N;i++){
            leftSum[i] = leftSum[i-1]+distances[i-1];
        }
        // 计算节点x距离右端点的和值
        for(int i=N-2;i>=0;i--){
            rightSum[i] = rightSum[i+1] + distances[i];
        }
    }

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();

        distances = new int[N];

        for(int i=0;i<N;i++){
            distances[i] = in.nextInt();
        }

        CalculateSum();

        M = in.nextInt();

        for(int a=0;a<M;a++){
            int i = in.nextInt()-1;
            int j = in.nextInt()-1;
            if(i>j){
                int temp = i;
                i = j; j = temp;
            }

            Slove(i,j);
        }
    }

    public void Slove(int i,int j){
        // 从左往右寻路
        int leftToRight = rightSum[i]-rightSum[j];
        // 从右往左寻路
        int rightToLeft = leftSum[i]+rightSum[j]+distances[N-1];

        int result = leftToRight<rightToLeft?leftToRight:rightToLeft;

        System.out.println(result);
    }
}
