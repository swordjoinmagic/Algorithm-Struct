package leetcode.PrimaryAlgorithm.Ohter;

import java.util.Arrays;

/**
 * 算法要求:时间复杂度O(N),空间复杂度(1)
 * 思路:
 *      没有出现在序列中的数 q = (0+1+2+3+...+n) - 序列nums的总和
 */
public class Problem268缺失数字 {
    public int missingNumber(int[] nums) {
        if(nums.length==1)
            return 1-nums[0];

        boolean isFindZero = false;

        // 找到最大数n
        int n = 0;
        int sum = 0;
        for(int data:nums) {
            if (n < data) n = data;
            sum += data;
            if(data==0)
                isFindZero = true;
        }

        int total = 0;
        for(int i=1;i<=n;i++) total += i;

        if(total-sum==0 && isFindZero)
            return n+1;

        return total - sum;
    }
}
