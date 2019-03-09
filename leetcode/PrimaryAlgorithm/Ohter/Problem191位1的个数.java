package leetcode.PrimaryAlgorithm.Ohter;

/**
 * 思路：
 *      将整数转二进制，然后数1
 *
 *      将整数转二进制的思路如下：
 *
 *      假设算整数6的二进制表示，步骤如下：
 *
 *          1. 6%2 == 0，6/2 = 3，此时二进制数为0
 *          2. 3%2 == 1, 3/2 = 1，此时二进制数为10
 *          3. 1%2 == 1, 1/2 = 0，此时二进制数为110
 *          4. 因为最后数为0，所以不进行计算
 *
 */
public class Problem191位1的个数 {
    public static void main(String[] args){
        Problem191位1的个数 problem191 = new Problem191位1的个数();
//        int result = problem191.hammingWeight(-1);
        System.out.println(Integer.toBinaryString(-5));

    }

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int total= 0;

        if(n<0){
            n += 1;
            n += 2147483647;
            total ++;
        }

        while (n!=0){
            if(n%2!=0) total++;
            n /= 2;
        }
        return total;
    }
}
