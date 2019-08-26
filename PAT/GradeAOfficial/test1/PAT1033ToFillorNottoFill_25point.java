package PAT.GradeAOfficial.test1;

/**
 * 题意:
 *      1. 同 桐爷开车
 *
 * 思路:
 *      1. DP.设f[x][y]为到达第x个加油站,还剩y升油的最小花费.
 *
 *      则有两种情况:
 *          a. 在第x-1个加油站加好油,到达第x个加油站时还剩y升油的花费
 *          b. 从第x-1个加油站到第x个加油站,在第x个加油站加油加到y升的花费
 *
 *      动态转移方程如下:
 *
 *      f[x][y] = min(
 *          // k>=C,C为路程消耗,且 k-C = y
 *          f[x-1][k],
 *          // k>=C,C为路程消耗,且 k-C <= y
 *          f[x-1][k] + (y-(k-C))*price[x+1]
 *      );
 */
public class PAT1033ToFillorNottoFill_25point {

}
