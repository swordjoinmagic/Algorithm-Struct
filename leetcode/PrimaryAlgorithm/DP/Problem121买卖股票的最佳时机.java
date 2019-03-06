package leetcode.PrimaryAlgorithm.DP;

/**
 * 思路:
 *      1. 暴力,时间复杂度O(n2)
 *      2.DP,设f[n]为前n天能得到的最大利润
 *
 *      f[n] = max(f[n-1],A[n]-min(A[i],i∈[1,n-1]))
 */
public class Problem121买卖股票的最佳时机 {

    public static void main(String[] args){
        int[] array = new int[]{7,6,5,4,3,2};
        Problem121买卖股票的最佳时机 problem121 = new Problem121买卖股票的最佳时机();
        int result = problem121.maxProfit(array);
        System.out.println(result);
    }

    public int maxProfit(int[] prices) {
        int[] f = new int[prices.length+2];
        f[1] = 0;
        if(prices.length<=1) return 0;
        f[2] = prices[1]-prices[0];

        // 前n-1天中出现的最小值
        int minNumber = Math.min(prices[0],prices[1]);

        for(int i=3;i<=prices.length;i++){

            // 获得前i-1天中的最小值
            minNumber = Math.min(minNumber,prices[i-1]);

            // 前i天的最大利润等于
            // max(前i-1天的最大利润,第i天的股票价格 - 前i-1天最低的股票价格)
            // 总结一下,就是,要么就是第i天卖掉股票利润最大,要么就是前i天的时候
            // 的利润最大
            f[i] = Math.max(f[i-1],prices[i-1]-minNumber);
        }

        return f[prices.length]>=0? f[prices.length] : 0;
    }
}
