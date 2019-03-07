package leetcode.PrimaryAlgorithm.mathProblem;

import java.util.List;

/**
 * 思路：
 *      1. 暴力,遍历一次时间复杂度O(N),判断一个数是不是素数时间复杂度O(sqart(N))
 *         时间复杂度大致为O(n2)
 *      2. 素数筛,欧拉筛法复杂度O(n),埃式筛法复杂度为O(Nlogn)
 */
public class Problem204计数质数 {

    public static void main(String[] args){
        Problem204计数质数 problem204 = new Problem204计数质数();
        int result = problem204.countPrimes(2);
        System.out.println(result);
    }

    // 素数表
    int[] primeList;
    // 筛子
    boolean[] u;

    public int countPrimes(int n) {
        int totalCount = 0;
        primeList = new int[n+1];
        u = new boolean[n+1];

        for(int i=2;i<=n;i++){
            // 为true时筛掉该数,false表示不筛,即为素数
            if(!u[i]){
                primeList[totalCount++] = i;
                // 如果当前数是素数,那么它的倍数不是素数
                for(int j=2,num=i*j;num<=n;j++,num=i*j){
                    u[num] = true;
                }
            }
        }

        return totalCount;
    }
}
