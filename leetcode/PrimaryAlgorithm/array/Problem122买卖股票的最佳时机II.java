package leetcode.PrimaryAlgorithm.array;


/**
 * 思路：
 *      1. DP，设f[i]为前i天能获得的最大利润
 *      f[i] = max(f[i-1],f[i-1]+(A[i]-A[i-1]));
 *      表示含义是,前i的最大利润为
 *      Max(前i-1天的最大利润,前i-1天的最大利润+在第i-1天买入,第i天卖出的利润)
 *
 */
public class Problem122买卖股票的最佳时机II {

    public static void main(String[] args){
        Problem122买卖股票的最佳时机II problem122 = new Problem122买卖股票的最佳时机II();
        int result = problem122.maxProfit(new int[]{7,1,5,3,6,4});
        System.out.println(result);
    }

    public int maxProfit(int[] prices) {
        if(prices.length == 0 || prices.length==1) return 0;
        int[] f = new int[prices.length];
        f[0] = 0;
        f[1] = prices[1]-prices[0] > 0 ? prices[1]-prices[0] : 0;

        for(int i=2;i<prices.length;i++){
            f[i] = Math.max(f[i-1],f[i-1]+(prices[i]-prices[i-1]));
        }

        return f[prices.length-1];
    }

}
