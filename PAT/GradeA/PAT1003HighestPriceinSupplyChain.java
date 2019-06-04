package PAT.GradeA;

import java.util.*;

/**
 * 题意：
 *      1. 给定商户数目N，价格P，价格增长率R%,
 *      每个商户会将当前商品价格上涨价格 P * R%后卖出
 *      求卖出的最高价格以及卖出最高价格的商户数目
 *
 * 思路：
 *      1. 以类似BFS的方式一层层迭代，每层商户卖出价格都会上涨P*R%,最后一层就是最高价格
 *      使用map记录供应商供应的每个商户
 */
public class PAT1003HighestPriceinSupplyChain {

    public static void main(String[] args){
        PAT1003HighestPriceinSupplyChain pat1003 = new PAT1003HighestPriceinSupplyChain();
        pat1003.Input();
    }

    // 用于记录每个供应商供应的商户
    Map<Integer, List<Integer>> map = new HashMap<>();

    // 商户数目与价格
    int N;
    double P;
    // 价格增长率
    double R;

    int[] supplier;
    double[] prices;

    public void Input(){
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        P = in.nextDouble();
        R = in.nextDouble();

        supplier = new int[N];
        prices = new double[N];

        for(int i=0;i<N;i++){
            supplier[i] = in.nextInt();
            if(!map.containsKey(supplier[i]))
                map.put(supplier[i],new ArrayList<>());
            map.get(supplier[i]).add(i);
        }
        Slove();
    }

    public void Slove(){

        int size = 0;
        double maxPrice = 0;
        int step = 0;

        Queue<Integer> queue = new LinkedList<>();
        // 第0层
        queue.add(-1);

        while (!queue.isEmpty()){
            size = queue.size();

            for(int i=0;i<size;i++){
                int sup = queue.poll();
                if(map.containsKey(sup)) {
                    for(int seller : map.get(sup)){
                        if(sup!=-1)
                            prices[seller] = prices[sup]*(R/100+1.0f);
                        else
                            prices[seller] = P;
                        queue.add(seller);
                        maxPrice = Math.max(prices[seller],maxPrice);
                    }
                }
            }

            step++;
        }

        System.out.format("%.2f %d",maxPrice,size);
    }

}
