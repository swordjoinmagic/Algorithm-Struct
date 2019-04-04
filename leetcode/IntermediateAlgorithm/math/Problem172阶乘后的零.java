package leetcode.IntermediateAlgorithm.math;

/**
 * 思路:
 *      1. 参考评论区,数因子5的个数,即为阶乘n!的结尾0的个数
 */
public class Problem172阶乘后的零 {
    public int trailingZeroes(int n) {
        int count = 0;
        while (n>0){
            n/=5;
            count += n;
        }
        return count;
    }
}
