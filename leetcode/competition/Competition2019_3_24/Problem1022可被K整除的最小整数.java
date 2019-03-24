package leetcode.competition.Competition2019_3_24;

/**
 * 思路：
 *      1. 所有位上均为1的数只可能是2的n次方-1,所以让n自增,
 *      直到k可以整除就OK了————答案错误
 *
 */
public class Problem1022可被K整除的最小整数 {

    public static void main(String[] args){
        Problem1022可被K整除的最小整数 problem1022 = new Problem1022可被K整除的最小整数();
        int result = problem1022.smallestRepunitDivByK(6);
        System.out.println(result);
    }

    public int smallestRepunitDivByK(int K) {
        if (K==1)   return 1;
        for(int i=2;i<=16;i++){
            int result = pow(i)-1;
            System.out.println(result);
            if(K%result==0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回2的n次方
     * @param n
     * @return
     */
    int pow(int n){
        int result = 1;
        for(int i=0;i<n;i++){
            result *= 2;
        }
        return result;
    }
}
