package leetcode.PrimaryAlgorithm.DP;

public class Problem70爬楼梯 {
    public int climbStairs(int n) {
        int[] array = new int[n+5];
        array[1] = 1;
        array[2] = 2;
        for(int i=3;i<=n;i++){
            array[i] = array[i-1] + array[i-2];
        }
        return array[n];
    }
}
