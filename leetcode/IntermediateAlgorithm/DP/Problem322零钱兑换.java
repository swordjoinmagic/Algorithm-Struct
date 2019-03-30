package leetcode.IntermediateAlgorithm.DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 思路：
 *      1. 递归。每次递归遍历coins数组，每个硬币都选一遍，
 *      当前硬币数量等于amount时，记录最小值，时间复杂度
 *      O(n的n次方)
 *      思路错误，时间超限
 *
 *      2. DP。设coins数组长度为len，
 *      设f[i]为组成金额i所需的最少硬币，状态转移方程如下：
 *
 *      f[i] = min｛f[ i - conis[j]] + 1｝(0 < j < len)
 *
 *      表达的意思是，
 *      金额i可能是由金额i-1加上一个面值为1的硬币构成，
 *      也可能是由金额i-2加上一个面值为2的硬币构成，
 *      依次类推
 *
 *      时间复杂度O(n2)
 *      战胜69.62%的Java
 *
 *      3. 设f[i][j]为使用前i个硬币组成金额j的所需最小硬币数，状态转移方程如下：
 *
 *      f[i][j] = min(f[i-1][j-coins[i]*M]+M,f[i][j])  (其中M表示可以使用第i个硬币的最大次数，表示使用本次硬币数量越多越好)
 *      状态转移方程错误
 *
 */
public class Problem322零钱兑换 {

    public static void main(String[] args){
        int[] coins = new int[]{1,Integer.MAX_VALUE};
        int amount = 100;
        Problem322零钱兑换 problem322 = new Problem322零钱兑换();
        int result = problem322.coinChangeWithDP2(coins,amount);
        System.out.println(result);
    }

    //========================================
    // 方法2

    private final int MAX_VALUE = 999999999;

    public int coinChange(int[] coins, int amount) {

        if(amount==0) return 0;

        if(coins.length==1) {
            if(amount%coins[0]==0) {
                return amount / coins[0];
            }else if(amount==coins[0])
                return 1;
            else
                return -1;
        }

        Arrays.sort(coins);

        // 设f[i]为组成金额i所需的最少硬币
        int[] f = new int[amount+1];

        f[0] = 1;
        // 初始化
        for(int data : coins) if(data<=amount) f[data] = 1;

        for(int i=2;i<=amount;i++){

            if(f[i]!=1) f[i] = MAX_VALUE;

            for(int j=0;j<coins.length;j++){

                if(i-coins[j]>=0 && f[i-coins[j]]>=1)
                    f[i] = Math.min(f[i],f[i-coins[j]]+1);
                else
                    break;
            }

        }

        return f[amount]==MAX_VALUE? -1 : f[amount];
    }

    public int coinChangeWithMap(int[] coins, int amount) {

        if(amount==0) return 0;

        if(coins.length==1) {
            if(amount%coins[0]==0) {
                return amount / coins[0];
            }else if(amount==coins[0])
                return 1;
            else
                return -1;
        }

        Arrays.sort(coins);

        // 设f[i]为组成金额i所需的最少硬币
        Map<Integer,Integer> f = new HashMap<>(amount);

        f.put(0,1);
        // 初始化
        for(int data : coins) f.put(data,1);

        for(int i=2;i<=amount;i++){

            if(f.getOrDefault(i,0)!=1) f.put(i,MAX_VALUE);

            for(int j=0;j<coins.length;j++){

                if(i-coins[j]>=0 && f.getOrDefault(i-coins[j],0)>=1)
                    f.put(i,Math.min(f.getOrDefault(i,0),f.getOrDefault(i-coins[j],0) +1));
                else
                    break;
            }

        }

        return f.getOrDefault(amount,0)==MAX_VALUE? -1 : f.getOrDefault(amount,0);
    }


    //===============================================
    // 方法1
    private int minValue = Integer.MAX_VALUE;
    public void coinChangeWithDFS(int[] coins,int amount,int now,int useCoins){
        if(now>amount) return;
        if(now==amount){
            minValue = Math.min(minValue,useCoins);
            return;
        }

        for(int data : coins){
            //防止溢出
            if(now+data>0)
                coinChangeWithDFS(coins,amount,now+data, useCoins+1);
        }
    }
    public int slove(int[] coins,int amount){
        coinChangeWithDFS(coins,amount,0,0);
        return minValue==Integer.MAX_VALUE? -1 : minValue;
    }



    //================================================
    // 方法3，状态转移方程错误
    public int coinChangeWithDP2(int[] coins,int amount){

        if(amount==0) return -1;

        if(coins.length==1) {
            if(amount%coins[0]==0) {
                return amount / coins[0];
            }else if(amount==coins[0])
                return 1;
            else
                return -1;
        }

        Arrays.sort(coins);

        int[][] f = new int[coins.length][amount+1];

        // 初始化
        for(int j=1;j<=amount;j++){
            if(j%coins[0]==0)
                f[0][j] = j/coins[0];
        }

        for(int i=1;i<coins.length;i++){
            for(int j=1;j<=amount;j++){
                f[i][j] = MAX_VALUE;
                int M = j/coins[i];
                if(j-coins[i]*M>=0)
                    f[i][j] = Math.min(f[i-1][j-coins[i]*M]+M,f[i][j]);
            }
        }

        return f[coins.length-1][amount]==MAX_VALUE? -1 : f[coins.length-1][amount];
    }
}
