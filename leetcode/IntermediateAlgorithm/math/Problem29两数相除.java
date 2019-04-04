package leetcode.IntermediateAlgorithm.math;

/**
 * 题目要求：
 *      1. 不适用乘法、除法和mod运算符
 *
 * 思路：
 *      1. 用减法，时间复杂度O(N)
 *      2. 位运算，左移一位是*2，右移一位是/2。
 *      不断的让除数divisor左移，即让divisor不断的*2,
 *      直到被除数dividend - divisor < 0，此时商是
 *
 *      dividend - (divisor- standerard) 正好 > 0
 *
 *      时的结果
 */
public class Problem29两数相除 {
    public static void main(String[] args){
        Problem29两数相除 problem29 = new Problem29两数相除();
        int result = problem29.divide(-2147483648,-1);
        System.out.println(result);
    }

    public int divide(int dividend, int divisor) {

        if(dividend==0) return 0;

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean isDifferent = false;
        if(dividend<0 && divisor>0 || (dividend>0 && divisor<0)) {
            isDifferent = true;
        }
        if(dividend==Integer.MIN_VALUE) dividend += 1;
        dividend = Math.abs(dividend);
        if(divisor==Integer.MIN_VALUE) divisor += 1;
        divisor = Math.abs(divisor);

        int tempDivisor = divisor;

        int count = 1;

        while (dividend - tempDivisor > 0) {
            // tempDivisor*2
            tempDivisor <<= 1;
            count <<= 1;    // 乘数
        }

        int tempCount = count;
        for(int i=0;i<count;i++){
            tempDivisor -= divisor;
            if(dividend - tempDivisor >= divisor)
                if(!isDifferent)
                    return tempCount-i;
                else
                    return i-tempCount;
        }

        return 0;
    }
}
