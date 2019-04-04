package leetcode.IntermediateAlgorithm.math;

/**
 * 思路:
 *      1. 快速幂
 */
public class Problem50快速幂 {

    public static void main(String[] args){
        Problem50快速幂 problem50 = new Problem50快速幂();
        double result = problem50.myPowWithNoRecursion(34.00515,-3);
        System.out.println(result);
    }

    /**
     * 五位有效数字
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if(n==0)return 1;
        else if(n==1) return x;

        if(n%2==0){
            double pow2 = myPow(x,n/2);
            return pow2*pow2;
        }else {
            double pow2 = myPow(x,n/2);
            return pow2*pow2*x;
        }
    }

    /**
     * 五位有效数字
     * @param x
     * @param n
     * @return
     */
    public double myPowWithNoRecursion(double x, int n) {
        double res = 1.0;
        for(int i = n; i != 0; i /= 2){
            if(i % 2 != 0){
                res *= x;
            }
            x *= x;
        }
        return  n < 0 ? 1 / res : res;
    }
}
