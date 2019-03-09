package leetcode.PrimaryAlgorithm.Ohter;

/**
 * 思路:
 *      异或,数1
 */
public class Problem461汉明距离 {
    public int hammingDistance(int x, int y) {
        // 异或
        int n = x^y;

        int total = 0;
        // 数1
        while (n!=0){
            if(n%2!=0) total++;
            n/=2;
        }
        return total;
    }
}
