package PAT.GradeA;

import java.util.*;

/**
 * 题意：
 *      1. 给定N个商户,根供应商价格P,每次价格上涨数量R%,
 *      求最后售出的总价
 *      2. 输入格式为:
 *          ki a1,a2,a3...a4
 *      表示第i个供应商将它的商品卖给商户a1,a2,a3,a4
 *
 * 思路:
 *      1. BFS.
 */
public class PAT1014TotalSalesofSupplyChain {

    public static void main(String[] args){
        PAT1014TotalSalesofSupplyChain pat1014 = new PAT1014TotalSalesofSupplyChain();
        pat1014.Input();
    }

    // suplliers[i] = {1,2,3,4}
    // 表示第i个供应商会将它的商品出售给第1/2/3/4个商户
    Map<Integer, List<Integer>> suplliers;

    // 共N个商户
    int N;
    // 初始价格P
    double P;
    // 价格上涨率
    double R;

    double[] prices;
    int[] commdies;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        P = in.nextDouble();
        R = in.nextDouble()/100+1.0f;

        suplliers = new HashMap<>();
        prices = new double[N];
        prices[0] = P;
        commdies = new int[N];

        for(int i=0;i<N;i++){
            suplliers.put(i,new ArrayList<>());
            int K = in.nextInt();
            if(K==0){
                int number = in.nextInt();
                commdies[i] = number;
            }else {
                for (int j = 0; j < K; j++) {
                    int custom = in.nextInt();
                    suplliers.get(i).add(custom);
                }
            }
        }

        Slove();
    }

    public void Slove(){

        double total = 0;

        Queue<Integer> queue = new LinkedList<>();
        // 加入根节点
        queue.add(0);
        while (!queue.isEmpty()){
            int seller = queue.poll();
            if(commdies[seller]!=0){
                // 这是一个出售者
                total += commdies[seller]*prices[seller];
            }
            for(int custom : suplliers.get(seller)){
                prices[custom] = prices[seller]*R;
                queue.add(custom);
            }
        }

        System.out.format("%.1f",total);
    }
}
