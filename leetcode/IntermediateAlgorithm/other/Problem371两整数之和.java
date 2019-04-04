package leetcode.IntermediateAlgorithm.other;

/**
 * 思路：
 *      1. 用位运算实现加减法
 */
public class Problem371两整数之和 {

    public static void main(String[] args){
        Problem371两整数之和 problem371 = new Problem371两整数之和();
        int result = problem371.getSum(-16,1);
        System.out.println(result);
    }

    // 位运算实现加法
    public int add(int a,int b){
        // 不考虑进位的加法
        int sum = a ^ b;

        // 只考虑进位的加法
        int carry = (a & b)<<1;

        System.out.println(sum+" "+carry);

        // 迭代，直到进位为0
        while (carry!=0){
            int tempSum = sum;
            int tempCarray = carry;
            sum = sum ^ tempCarray;
            carry = (tempSum&tempCarray)<<1;
        }
        return sum;
    }

    /**
     * 位运算实现减法，
     * 实际上利用了减法等于 正数 + 负数，负数由正数的二进制补码表示
     * @return
     */
    public int substract(int a,int b){
        // 补码表示为正数取反+1
        int num2 = add(~b,1);
        return add(a,num2);
    }

    public int getSum(int a, int b) {

        return add(a,b);
    }
}
